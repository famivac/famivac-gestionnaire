package fr.famivac.gestionnaire.web.parametres.emails;

import fr.famivac.gestionnaire.infrastructure.mail.Mail;
import fr.famivac.gestionnaire.infrastructure.mail.MailService;
import java.io.Serializable;
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
public class EmailsBean implements Serializable {

  @Inject
  private MailService mailService;

  @Inject
  private EmailForm emailForm;

  public void send() {
    Mail mail = new Mail(emailForm.getRecipient(), emailForm.getSubject(), emailForm.getBody());
    mailService.send(mail);
    FacesContext.getCurrentInstance()
        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail envoyé !", ""));
    emailForm.init();
  }

}
