package fr.famivac.gestionnaire.familles.boundary;

import fr.famivac.gestionnaire.familles.entity.FamilleRepository;
import fr.famivac.gestionnaire.commons.entity.Adresse;
import fr.famivac.gestionnaire.familles.entity.Chambre;
import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.familles.entity.Famille;
import fr.famivac.gestionnaire.familles.entity.MembreFamille;
import fr.famivac.gestionnaire.commons.entity.Sexe;
import fr.famivac.gestionnaire.commons.utils.AlphanumComparator;
import fr.famivac.gestionnaire.familles.entity.InformationsHabitation;
import fr.famivac.gestionnaire.familles.entity.InformationsVehicule;
import fr.famivac.gestionnaire.familles.entity.PeriodeAccueil;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
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

    public List<FamilleDTO> rechercher(String nomReferent, String prenomReferent, List<String> periodesAccueil, boolean archivee) {
        Set<PeriodeAccueil> periodes = null;
        if (periodesAccueil != null) {
            periodes = periodesAccueil
                    .stream()
                    .map(periode -> {
                        return PeriodeAccueil.valueOf(periode);
                    })
                    .collect(Collectors.toSet());
        }
        AlphanumComparator comparator = new AlphanumComparator();
        List<Famille> beans = repository.retrieve(nomReferent, prenomReferent, periodes, archivee);
        List<FamilleDTO> dtos = beans
                .stream()
                .map((Famille f) -> {
                    return new FamilleDTO(f);
                }).sorted((f1, f2) -> {
            int resultNomReferent = comparator.compare(f1.getNomReferent(), f2.getNomReferent());
            if (resultNomReferent != 0) {
                return resultNomReferent;
            }
            return comparator.compare(f1.getPrenomReferent(), f2.getPrenomReferent());
        }).collect(Collectors.toList());
        return dtos;
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

    public void removeMembre(Long familleId, long membreId) {
        Famille famille = entityManager.find(Famille.class, familleId);
        MembreFamille membre = entityManager.find(MembreFamille.class, membreId);
        famille.retirerMembre(membre);
    }

    public void definirReferent(Long familleId, long membreId) {
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
