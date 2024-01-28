package fr.famivac.gestionnaire.web.security;

import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import fr.famivac.gestionnaire.domains.utilisateurs.control.UtilisateurService;
import fr.famivac.gestionnaire.domains.utilisateurs.entity.Utilisateur;
import fr.famivac.gestionnaire.commons.exceptions.WrongCredentialsException;

@ApplicationScoped
public class CustomDatabaseIdentityStore implements IdentityStore {

  @Inject
  private UtilisateurService utilisateurService;

  @Override
  public CredentialValidationResult validate(Credential credential) {
    UsernamePasswordCredential credentials = (UsernamePasswordCredential) credential;
    String login = credentials.getCaller();
    String password = credentials.getPasswordAsString();
    try {
      Utilisateur logged = utilisateurService.login(login, password);
      return new CredentialValidationResult(logged.getLogin(),
          logged.getGroupes().stream().map(groupe -> groupe.getNom()).collect(Collectors.toSet()));
    } catch (WrongCredentialsException ex) {
      return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }
  }

}
