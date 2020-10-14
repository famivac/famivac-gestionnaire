package fr.famivac.gestionnaire.familles.boundary;

import fr.famivac.gestionnaire.familles.entity.FamilleRepository;
import fr.famivac.gestionnaire.commons.entity.Adresse;
import fr.famivac.gestionnaire.familles.entity.Chambre;
import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.familles.entity.Famille;
import fr.famivac.gestionnaire.familles.entity.MembreFamille;
import fr.famivac.gestionnaire.commons.entity.Sexe;
import fr.famivac.gestionnaire.familles.entity.InformationsHabitation;
import fr.famivac.gestionnaire.familles.entity.InformationsVehicule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import kong.unirest.*;
import kong.unirest.json.JSONObject;
import net.bull.javamelody.MonitoringInterceptor;

/**
 * @author paoesco
 */
@Stateless
@Interceptors({MonitoringInterceptor.class})
public class FamilleService {

    @Inject
    private EntityManager entityManager;

    @Inject
    private FamilleRepository repository;

    public Long create(CreateFamilleRequestDTO request) {
        Commune communeFamille = null;
        if (request.getAdresse().getCommune() != null) {
            communeFamille = new Commune(request.getAdresse().getCommune().getCode(), request.getAdresse().getCommune().getVille());
        }
        Adresse adresse = new Adresse(request.getAdresse().getLigneAdresseUne(), request.getAdresse().getLigneAdresseDeux(), communeFamille);
        Famille entity = new Famille(adresse, request.getProjet(), request.getCandidature());
        Commune communeMembre = request.getMembrePrincipal().getCommuneDeNaissance();
        MembreFamille membre = new MembreFamille(
                request.getMembrePrincipal().getNom(),
                request.getMembrePrincipal().getNomDeNaissance(),
                request.getMembrePrincipal().getPrenom(),
                Sexe.valueOf(request.getMembrePrincipal().getSexe()),
                request.getMembrePrincipal().getDateNaissance(),
                request.getMembrePrincipal().getProfession(),
                true,
                communeMembre,
                request.getMembrePrincipal().getCoordonnees());
        entity.ajouterMembre(membre);
        entityManager.persist(entity);
        return entity.getId();
    }

    public Famille get(Long id) {
        Famille famille = entityManager.find(Famille.class, id);
        // Migration
        if (famille.getInformationsHabitation().getId() == null) {
            InformationsHabitation informationsHabitation = new InformationsHabitation(famille);
            entityManager.persist(informationsHabitation);
            famille.setInformationsHabitation(informationsHabitation);
        }
        if (famille.getInformationsVehicule().getId() == null) {
            InformationsVehicule informationsVehicule = new InformationsVehicule(famille);
            entityManager.persist(informationsVehicule);
            famille.setInformationsVehicule(informationsVehicule);
        }
        return famille;
    }

    public List<FamilleDTO> rechercher(String nomReferent, String prenomReferent, boolean archivee)  {
        String clientId = System.getProperty("auth0.client.id");
        String clientSecret = System.getProperty("auth0.client.secret");
        String apiAudience = System.getProperty("auth0.api.audience");
        String auth0Domain = System.getProperty("auth0.domain");
        try {
            HttpResponse<JsonNode> responseAuth = Unirest
                    .post(auth0Domain)
                    .header("content-type", "application/json")
                    .body("{\"client_id\":\"" + clientId + "\",\"client_secret\":\"" + clientSecret + "\",\"audience\":\"" + apiAudience + "\",\"grant_type\":\"client_credentials\"}")
                    .asJson();

        String accessToken = responseAuth.getBody().getObject().getString("access_token");

        HttpRequest familleRequest = Unirest
                .get(apiAudience + "/familles/search")
                .header("authorization", "Bearer " + accessToken)
                .queryString("archivee", archivee);
        if (nomReferent != null && !nomReferent.isBlank()) {
            familleRequest.queryString("nomReferent", nomReferent);
        }
        if (prenomReferent != null && !prenomReferent.isBlank()) {
            familleRequest.queryString("prenomReferent", prenomReferent);
        }

        List<FamilleDTO> familles = new ArrayList<>();
        HttpResponse<JsonNode> response = familleRequest.asJson();
        response
                .getBody()
                .getObject()
                .getJSONObject("_embedded")
                .getJSONArray("familles")
                .forEach(node -> {
            JSONObject jsonNode = (JSONObject) node;
            FamilleDTO famille = new FamilleDTO();
            // Map famille
            famille.setId(jsonNode.getLong("id"));
            famille.setArchivee(jsonNode.getBoolean("archivee"));
            famille.setCandidature(jsonNode.getBoolean("candidature"));
            famille.setRadiee(jsonNode.isNull("dateRadiation") ? false : true);
            // Map membre
            JSONObject membreReferent = null;
            for (JSONObject membre : (List<JSONObject>)jsonNode.getJSONArray("membres").toList()) {
                if (membre.getBoolean("referent")) {
                    membreReferent = membre;
                }
            }
            famille.setNomReferent(membreReferent.getString("nom"));
            famille.setPrenomReferent(membreReferent.getString("prenom"));
            // Map coordonnees
            JSONObject coordonnees = membreReferent.getJSONObject("coordonnees");
            famille.setEmailReferent(coordonnees.optString("email"));
            famille.setTelephoneReferent(coordonnees.optString("telephone1"));
            familles.add(famille);
        });

            return familles;
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void update(Famille entity) {
        entityManager.merge(entity);

    }

    public void delete(Long id) {
        Famille famille = entityManager.find(Famille.class, id);
        if (famille == null) {
            throw new IllegalArgumentException("La famille n'existe pas");
        }
        entityManager.remove(famille);
    }

    public void archiver(Long id) {
        Famille famille = entityManager.find(Famille.class, id);
        if (famille == null) {
            throw new IllegalArgumentException("La famille n'existe pas");
        }
        famille.setArchivee(true);
        entityManager.merge(famille);
    }

    public void desarchiver(Long id) {
        Famille famille = entityManager.find(Famille.class, id);
        if (famille == null) {
            throw new IllegalArgumentException("La famille n'existe pas");
        }
        famille.setArchivee(false);
        entityManager.merge(famille);
    }

    public void deleteChambre(Long id) {
        Chambre entity = entityManager.find(Chambre.class, id);
        entity.getFamille().retirerChambre(entity);
        entityManager.remove(entity);
    }

    public Long addMembre(Long familleId, MembreDTO request) {
        Famille famille = entityManager.find(Famille.class, familleId);
        if (famille == null) {
            throw new IllegalArgumentException("La famille n'existe pas");
        }
        Commune commune = new Commune(request.getCommuneDeNaissance().getCode(), request.getCommuneDeNaissance().getVille());
        MembreFamille membre = new MembreFamille(
                request.getNom(),
                request.getNomDeNaissance(),
                request.getPrenom(),
                Sexe.valueOf(request.getSexe()),
                request.getDateNaissance(),
                request.getProfession(),
                commune,
                request.getCoordonnees());
        famille.ajouterMembre(membre);
        return membre.getId();

    }

    public void removeMembre(Long familleId, Long membreId) {
        Famille famille = entityManager.find(Famille.class, familleId);
        MembreFamille membre = entityManager.find(MembreFamille.class, membreId);
        famille.retirerMembre(membre);
    }

    public void definirReferent(Long familleId, Long membreId) {
        Famille famille = entityManager.find(Famille.class, familleId);
        famille.definirReferent(membreId);
    }

    public Chambre addChambre(Long familleId, Chambre entity) {
        Famille famille = entityManager.find(Famille.class, familleId);
        if (famille == null) {
            throw new IllegalArgumentException("La famille n'existe pas");
        }
        entityManager.persist(entity);
        famille.ajouterChambre(entity);
        return entity;
    }

}
