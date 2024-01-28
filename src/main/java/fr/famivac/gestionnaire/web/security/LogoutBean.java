package fr.famivac.gestionnaire.web.security;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class LogoutBean {

  @Inject
  private ExternalContext externalContext;

  public String submit() {
    externalContext.invalidateSession();
    return "/login.xhtml?faces-redirect=true";
  }

}
