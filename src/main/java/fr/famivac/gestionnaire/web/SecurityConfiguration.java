package fr.famivac.gestionnaire.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;

@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/faces/login.xhtml", useForwardToLogin = false, errorPage = "/faces/login.xhtml?error=true"))
@FacesConfig
@ApplicationScoped
public class SecurityConfiguration {

}
