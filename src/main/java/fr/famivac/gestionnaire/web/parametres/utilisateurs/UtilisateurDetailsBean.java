package fr.famivac.gestionnaire.web.parametres.utilisateurs;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class UtilisateurDetailsBean implements Serializable {

  private String login;

  private DetailsUtilisateurForm form;

//    @Inject
//    private UtilisateurService utilisateurService;

  private List<String> groupes;

  public void init() {
//        form = utilisateurService.retrieve(login);
//        List<GroupeResponseDTO> groupesDTO = utilisateurService.getGroupes();
//        groupes = groupesDTO.stream().map(groupe -> groupe.getNom()).collect(Collectors.toList());
  }

  public List<String> autocomplete(String query) {
    if (query == null || query.isEmpty()) {
      return groupes;
    }
    return groupes.stream().filter(nom -> query.equals(nom)).collect(Collectors.toList());
  }

  public void sauver() {
    if (!form.getPassword().isEmpty() || !form.getConfirmPassword().isEmpty()) {
      if (!form.getPassword().equals(form.getConfirmPassword())) {
        FacesContext.getCurrentInstance().addMessage("form:form_password",
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Les mots de passe ne correspondent pas",
                ""));
        ((UIInput) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent("form:form_password")).setValid(false);
        ((UIInput) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent("form:form_confirm_password")).setValid(false);
        return;
      }
    }
//        utilisateurService.update(login, form);
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Les informations ont été enregistrées", ""));
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public DetailsUtilisateurForm getForm() {
    return form;
  }

  public void setForm(DetailsUtilisateurForm form) {
    this.form = form;
  }

}
