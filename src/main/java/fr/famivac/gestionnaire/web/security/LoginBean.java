package fr.famivac.gestionnaire.web.security;

import java.io.IOException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;

@Named
@RequestScoped
public class LoginBean {

  @NotEmpty
  private String password;

  @NotEmpty
  private String username;

  @Inject
  private SecurityContext securityContext;

  @Inject
  private ExternalContext externalContext;

  @Inject
  private FacesContext facesContext;

  public void submit() throws IOException {
    switch (continueAuthentication()) {
      case SEND_CONTINUE:
        facesContext.responseComplete();
        break;
      case SEND_FAILURE:
        facesContext.addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
        break;
      case SUCCESS:
        facesContext.addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
        externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
        break;
      case NOT_DONE:
    }
  }

  private AuthenticationStatus continueAuthentication() {
    return securityContext.authenticate((HttpServletRequest) externalContext.getRequest(),
        (HttpServletResponse) externalContext.getResponse(),
        AuthenticationParameters.withParams()
            .credential(new UsernamePasswordCredential(getUsername(), getPassword())));
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}