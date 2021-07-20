package fr.famivac.gestionnaire.domains.familles.boundary;

import fr.famivac.gestionnaire.commons.entity.Adresse;
import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.commons.entity.Sexe;
import fr.famivac.gestionnaire.domains.familles.entity.Chambre;
import fr.famivac.gestionnaire.domains.familles.entity.Famille;
import fr.famivac.gestionnaire.domains.familles.entity.FamilleRepository;
import fr.famivac.gestionnaire.domains.familles.entity.InformationsHabitation;
import fr.famivac.gestionnaire.domains.familles.entity.InformationsVehicule;
import fr.famivac.gestionnaire.domains.familles.entity.MembreFamille;
import fr.famivac.gestionnaire.domains.familles.entity.PeriodeAccueil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import net.bull.javamelody.MonitoringInterceptor;

@Stateless
@Interceptors({MonitoringInterceptor.class})
public class FamilleService {

  @Inject private EntityManager entityManager;

  @Inject private FamilleRepository repository;

  public Long create(CreateFamilleRequestDTO payload) {
    Commune communeFamille = null;
    if (payload.getAdresse().getCommune() != null) {
      communeFamille =
          new Commune(
              payload.getAdresse().getCommune().getCode(),
              payload.getAdresse().getCommune().getVille());
    }
    Adresse adresse =
        new Adresse(
            payload.getAdresse().getLigneAdresseUne(),
            payload.getAdresse().getLigneAdresseDeux(),
            communeFamille);
    Famille entity = new Famille();
    entity.setAdresse(adresse);
    entity.setProjet(payload.getProjet());
    entity.setCandidature(payload.getCandidature());
    Commune communeMembre = payload.getMembrePrincipal().getCommuneDeNaissance();
    MembreFamille membre =
        new MembreFamille(
            payload.getMembrePrincipal().getNom(),
            payload.getMembrePrincipal().getNomDeNaissance(),
            payload.getMembrePrincipal().getPrenom(),
            Sexe.valueOf(payload.getMembrePrincipal().getSexe()),
            payload.getMembrePrincipal().getDateNaissance(),
            payload.getMembrePrincipal().getProfession(),
            true,
            communeMembre,
            payload.getMembrePrincipal().getCoordonnees());
    entity.ajouterMembre(membre);
    entityManager.persist(entity);
    return entity.getId();
  }

  public Famille get(Long id) {
    EntityGraph entityGraph = entityManager.createEntityGraph(Famille.class);
    entityGraph.addAttributeNodes("chambres", "membres");
    Map<String, Object> properties = new HashMap<>();
    properties.put("javax.persistence.fetchgraph", entityGraph);
    Famille famille = entityManager.find(Famille.class, id, properties);

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

  public List<FamilleDTO> search(
      String nomReferent, String prenomReferent, List<String> periodesAccueil, boolean archivee) {
    Set<PeriodeAccueil> periodes = null;
    if (periodesAccueil != null) {
      periodes =
          periodesAccueil.stream()
              .map(periode -> PeriodeAccueil.valueOf(periode))
              .collect(Collectors.toSet());
    }
    List<Famille> familles = repository.retrieve(nomReferent, prenomReferent, periodes, archivee);
    return familles.stream().map((Famille f) -> new FamilleDTO(f)).collect(Collectors.toList());
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

  public Long addMembre(Long familleId, MembreDTO payload) {
    Famille famille = entityManager.find(Famille.class, familleId);
    if (famille == null) {
      throw new IllegalArgumentException("La famille n'existe pas");
    }
    Commune commune =
        new Commune(
            payload.getCommuneDeNaissance().getCode(), payload.getCommuneDeNaissance().getVille());
    MembreFamille membre =
        new MembreFamille(
            payload.getNom(),
            payload.getNomDeNaissance(),
            payload.getPrenom(),
            Sexe.valueOf(payload.getSexe()),
            payload.getDateNaissance(),
            payload.getProfession(),
            commune,
            payload.getCoordonnees());
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
