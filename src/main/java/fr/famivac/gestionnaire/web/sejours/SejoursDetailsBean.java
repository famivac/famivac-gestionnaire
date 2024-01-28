package fr.famivac.gestionnaire.web.sejours;

import fr.famivac.gestionnaire.domains.sejours.control.SejourService;
import fr.famivac.gestionnaire.domains.sejours.entity.Accompagnateur;
import fr.famivac.gestionnaire.domains.sejours.entity.AccompagnateurRepository;
import fr.famivac.gestionnaire.domains.sejours.entity.Sejour;
import fr.famivac.gestionnaire.domains.sejours.entity.SejourRepository;
import fr.famivac.gestionnaire.domains.sejours.entity.StatutSejour;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class SejoursDetailsBean implements Serializable {

  private static final long serialVersionUID = -3713134313163227048L;

  @Inject
  private SejourRepository sejourRepository;

  @Inject
  private SejourService sejourService;

  @Inject
  private AccompagnateurRepository accompagnateurRepository;

  private Accompagnateur ajoutAccompagnateurAller;
  private Accompagnateur ajoutAccompagnateurRetour;

  private Long id;

  private Sejour sejour;
  private BilanSejour bilan;

  /**
   * Initialisation du bean.
   */
  public void init() {
    this.sejour = sejourRepository.get(id);
    this.bilan = new BilanSejour(sejour);
    this.ajoutAccompagnateurAller = new Accompagnateur();
    this.ajoutAccompagnateurRetour = new Accompagnateur();
  }

  public void update() {
    sejourService.update(sejour);
    FacesContext.getCurrentInstance()
        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sauvées", ""));
  }

  public void ajouterAccompagnateurAller() {
    sejour.getAller().getAccompagnateurs().add(ajoutAccompagnateurAller);
    sejourService.update(sejour);
    ajoutAccompagnateurAller = new Accompagnateur();
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO, "Accompagnateur ajouté", ""));
  }

  public void retirerAccompagnateurAller(Accompagnateur accompagnateur) {
    sejour.getAller().getAccompagnateurs().remove(accompagnateur);
    sejourService.update(sejour);
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO, "L'accompagnateur a été retiré", ""));
  }

  public void ajouterAccompagnateurRetour() {
    sejour.getRetour().getAccompagnateurs().add(ajoutAccompagnateurRetour);
    sejourService.update(sejour);
    ajoutAccompagnateurRetour = null;
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO, "Accompagnateur ajouté", ""));
  }

  public void retirerAccompagnateurRetour(Accompagnateur accompagnateur) {
    sejour.getRetour().getAccompagnateurs().remove(accompagnateur);
    sejourService.update(sejour);
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO, "L'accompagnateur a été retiré", ""));
  }

  public List<Accompagnateur> completeAccompagnateur(String query) {
    if (query == null || query.isEmpty()) {
      return accompagnateurRepository.get();
    }
    return accompagnateurRepository.rechercher(query, query);
  }

  public void terminerSejour() {
    sejourService.update(sejour);
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO, "Le séjour a été terminé.", ""));
  }

  public void annulerSejour() {
    sejour.setDateAnnulation(new Date());
    sejourService.update(sejour);
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO, "Le séjour a été annulé.", ""));
  }

  public void reactiverSejour() {
    sejour.setDateAnnulation(null);
    sejour.setMotifAnnulation(null);
    sejour.setMotifFinSejour(null);
    sejourService.update(sejour);
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO, "Le séjour a été réactivé.", ""));
  }

  public Sejour getSejour() {
    return sejour;
  }

  public void setSejour(Sejour sejour) {
    this.sejour = sejour;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStatut() {
    Optional<StatutSejour> ostatut = sejour.statut(new Date());
    if (!ostatut.isPresent()) {
      return "";
    }
    return ostatut.get().name();
  }

  public Accompagnateur getAjoutAccompagnateurAller() {
    return ajoutAccompagnateurAller;
  }

  public void setAjoutAccompagnateurAller(Accompagnateur ajoutAccompagnateurAller) {
    this.ajoutAccompagnateurAller = ajoutAccompagnateurAller;
  }

  public Accompagnateur getAjoutAccompagnateurRetour() {
    return ajoutAccompagnateurRetour;
  }

  public void setAjoutAccompagnateurRetour(Accompagnateur ajoutAccompagnateurRetour) {
    this.ajoutAccompagnateurRetour = ajoutAccompagnateurRetour;
  }

  public BilanSejour getBilan() {
    return bilan;
  }

}
