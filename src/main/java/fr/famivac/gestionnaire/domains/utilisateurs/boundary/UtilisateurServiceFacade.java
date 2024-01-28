package fr.famivac.gestionnaire.domains.utilisateurs.boundary;

import fr.famivac.gestionnaire.domains.utilisateurs.control.AjouterUtilisateurDTO;
import fr.famivac.gestionnaire.domains.utilisateurs.control.UtilisateurService;
import fr.famivac.gestionnaire.infrastructure.mail.Mail;
import fr.famivac.gestionnaire.infrastructure.mail.MailService;
import java.text.MessageFormat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * @author paoesco
 */
@ApplicationScoped
public class UtilisateurServiceFacade {

  @Inject
  private UtilisateurService utilisateurService;

  @Inject
  private MailService mailService;

  public String create(AjouterUtilisateurDTO dto) {
    String password = utilisateurService.create(dto);
    Mail mail = new Mail(
        dto.getEmail(),
        "[FAMIVAC] Création d'un compte",
        MessageFormat.format(
            "Un administrateur vous a créé un compte sur l''interface https://gestionnaire.famivac.fr<br/> Login : {0} \n Mot de passe généré : {1} \n Veuillez le changer à la première connexion.",
            dto.getLogin(), password));
    mailService.send(mail);
    return password;
  }

  public String reinitPassword(String login, String email) {
    String password = utilisateurService.reinitPassword(login);
    Mail mail = new Mail(
        email,
        "[FAMIVAC] Réinitialisation mot de passe",
        MessageFormat.format(
            "Un administrateur a réinitialisé votre mot de passe sur le site https://gestionnaire.famivac.fr<br/> Nouveau mot de passe : {0}",
            password));
    mailService.send(mail);
    return password;
  }

  public void lock(String login, String email) {
    utilisateurService.lock(login);
  }

  public void unlock(String login, String email) {
    utilisateurService.unlock(login);
  }

}
