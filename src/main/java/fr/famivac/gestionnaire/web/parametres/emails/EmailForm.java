package fr.famivac.gestionnaire.web.parametres.emails;

import fr.famivac.gestionnaire.commons.utils.Email;
import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class EmailForm implements Serializable {

  @Email
  private String recipient;

  private String subject;

  private String body;

  public void init() {
    this.recipient = null;
    this.subject = null;
    this.body = null;
  }

  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

}
