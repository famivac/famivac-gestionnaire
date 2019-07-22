package fr.famivac.gestionnaire.sejours.entity;

import fr.famivac.gestionnaire.commons.entity.Entity;
import fr.famivac.gestionnaire.commons.utils.Email;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author paoesco
 */
@javax.persistence.Entity
@Table(name = "ACCOMPAGNATEUR")
@NamedQueries({
    @NamedQuery(name = Accompagnateur.QUERY_GET_ALL, query = "select acc from Accompagnateur acc order by acc.nom, acc.prenom where acc.deletedAt is null"),
    @NamedQuery(name = Accompagnateur.QUERY_RECHERCHER, query = "select acc from Accompagnateur acc where acc.deletedAt is null and lower(acc.nom) like :nom or lower(acc.prenom) like :prenom order by acc.nom, acc.prenom")
})
public class Accompagnateur extends Entity implements Serializable {

    public static final String QUERY_GET_ALL = "queryAccompagnateurGetAll";
    public static final String QUERY_RECHERCHER = "queryAccompagnateurRechercher";

    @Column(name = "NOM")
    private String nom;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "EMAIL")
    @Email
    private String email;
    @Column(name = "DELETED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDeletedAt() {
        return deleted_at;
    }

    public void setDeletedAt(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
    
    

}
