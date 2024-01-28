package fr.famivac.gestionnaire.web.parametres.utilisateurs;

import fr.famivac.gestionnaire.domains.utilisateurs.control.AjouterUtilisateurDTO;
import fr.famivac.gestionnaire.domains.utilisateurs.control.GroupeService;
import fr.famivac.gestionnaire.domains.utilisateurs.control.RetrieveUtilisateursDTO;
import fr.famivac.gestionnaire.domains.utilisateurs.control.UtilisateurService;
import fr.famivac.gestionnaire.domains.utilisateurs.entity.Groupe;
import fr.famivac.gestionnaire.domains.utilisateurs.entity.Utilisateur;
import fr.famivac.gestionnaire.domains.utilisateurs.boundary.UtilisateurServiceFacade;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.CellEditEvent;

/**
 * Backing bean des enfants.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class UtilisateursBean implements Serializable {

  private static final long serialVersionUID = 2658935844250281457L;

  private AjouterUtilisateurDTO form;

  /**
   * Liste des utilisateurs.
   */
  private LazyUtilisateurDataModel lazyModel;

  @Inject
  private UtilisateurService utilisateurService;
  @Inject
  private UtilisateurServiceFacade utilisateurServiceFacade;
  @Inject
  private GroupeService groupeService;

  private List<Groupe> groupes;

  /**
   * Initialisation du bean par la vue.
   */
  public void init() {
    lazyModel = new LazyUtilisateurDataModel(utilisateurService.retrieve());
    groupes = groupeService.retrieve();
    form = new AjouterUtilisateurDTO();
    Groupe groupeGestionnaire = groupes
        .stream()
        .filter(g -> Groupe.ROLE_GESTIONNAIRE.equals(g.getNom()))
        .findFirst()
        .orElse(null);
    List<Groupe> initGroupes = new ArrayList<>();
    initGroupes.add(groupeGestionnaire);
    form.setGroupes(initGroupes);
  }

  public List<Groupe> completeGroupe(String query) {
    if (query == null || query.isEmpty()) {
      return groupes;
    }
    return groupes
        .stream()
        .filter(
            g -> g.getLibelle().toLowerCase().trim().contains(query.toLowerCase().trim())
        ).collect(Collectors.toList());
  }

  public void add() {
    String password = null;
    password = utilisateurServiceFacade.create(form);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
        "L'utilisateur a été ajouté", null);
    FacesContext.getCurrentInstance().addMessage(null, message);
    FacesMessage displayPass = new FacesMessage(FacesMessage.SEVERITY_INFO,
        MessageFormat.format("Le mot de passe généré est : {0}", password), null);
    FacesContext.getCurrentInstance().addMessage(null, displayPass);
    FacesMessage messageEmail = new FacesMessage(FacesMessage.SEVERITY_INFO,
        "Un e-mail a été envoyé à l'utilisateur", null);
    FacesContext.getCurrentInstance().addMessage(null, messageEmail);
    init();
  }

  public void delete(RetrieveUtilisateursDTO user) {
    utilisateurService.delete(user.getLogin());
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
        "L'utilisateur a été supprimé", null);
    FacesContext.getCurrentInstance().addMessage(null, message);
    init();
  }

  public void reinitialiserMotDePasse(RetrieveUtilisateursDTO user) {
    String password = utilisateurServiceFacade.reinitPassword(user.getLogin(), user.getEmail());
    FacesMessage displayPass = new FacesMessage(FacesMessage.SEVERITY_INFO,
        MessageFormat.format("Le mot de passe a été réinitialisé : {0}", password), null);
    FacesContext.getCurrentInstance().addMessage(null, displayPass);
    FacesMessage messageEmail = new FacesMessage(FacesMessage.SEVERITY_INFO,
        "Un e-mail a été envoyé à l'utilisateur", null);
    FacesContext.getCurrentInstance().addMessage(null, messageEmail);
  }

  public void lock(RetrieveUtilisateursDTO user) {
    utilisateurServiceFacade.lock(user.getLogin(), user.getEmail());
    user.setEnabled(false);
    FacesMessage lockMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, MessageFormat.format(
        "L''utilisateur {0} {1} a été verrouillé et ne pourra plus se connecter", user.getPrenom(),
        user.getNom()), null);
    FacesContext.getCurrentInstance().addMessage(null, lockMessage);
  }

  public void unlock(RetrieveUtilisateursDTO user) {
    utilisateurServiceFacade.unlock(user.getLogin(), user.getEmail());
    user.setEnabled(true);
    FacesMessage unlockMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, MessageFormat.format(
        "L''utilisateur {0} {1} a été déverrouillé et peut à nouveau se connecter",
        user.getPrenom(), user.getNom()), null);
    FacesContext.getCurrentInstance().addMessage(null, unlockMessage);
  }

  public void onCellEdit(CellEditEvent event) {
    RetrieveUtilisateursDTO dto = lazyModel.getRowData(event.getRowIndex());
    List<Groupe> oldValue = (List<Groupe>) event.getOldValue();
    List<Groupe> newValue = (List<Groupe>) event.getNewValue();

    if (newValue != null && !newValue.equals(oldValue)) {
      Utilisateur utilisateur = utilisateurService.retrieve(dto.getLogin());
      utilisateur.setGroupes(new HashSet<>(newValue));
      utilisateurService.update(utilisateur);
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "L'utilisateur a été modifié",
          null);
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }

  }

  public LazyUtilisateurDataModel getLazyModel() {
    return lazyModel;
  }

  public AjouterUtilisateurDTO getForm() {
    return form;
  }

  public void setForm(AjouterUtilisateurDTO form) {
    this.form = form;
  }

  public List<Groupe> getGroupes() {
    return groupes;
  }

  public void setGroupes(List<Groupe> groupes) {
    this.groupes = groupes;
  }

}
