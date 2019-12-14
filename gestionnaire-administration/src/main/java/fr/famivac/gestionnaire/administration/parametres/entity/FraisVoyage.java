package fr.famivac.gestionnaire.administration.parametres.entity;

import fr.famivac.gestionnaire.commons.utils.DateUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author paoesco
 */
@Entity
@Table(name = "FRAIS_VOYAGE")
@NamedQueries({
    @NamedQuery(name = FraisVoyage.QUERY_LISTE_ALL, query = "select f from FraisVoyage f")
    ,
    @NamedQuery(name = FraisVoyage.QUERY_GET_CURRENT, query = "select f from FraisVoyage f where f.dateDebutValidite <= :date and :date <= f.dateFinValidite")
})
public class FraisVoyage {

    public static final String QUERY_LISTE_ALL = "getFraisVoyage";

    public static final String QUERY_GET_CURRENT = "getFraisVoyageActif";

    public static final FraisVoyage _DEFAULT = new FraisVoyage();

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "MONTANT", precision = 10, scale = 2, nullable = false)
    private BigDecimal montant;

    @Column(name = "DATE_DEBUT_VALIDITE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDebutValidite;

    @Column(name = "DATE_FIN_VALIDITE")
    @Temporal(TemporalType.DATE)
    private Date dateFinValidite;

    protected FraisVoyage() {
        montant = new BigDecimal(BigInteger.ZERO);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Date getDateDebutValidite() {
        return dateDebutValidite;
    }

    public void setDateDebutValidite(Date dateDebutValidite) {
        this.dateDebutValidite = dateDebutValidite;
    }

    public Date getDateFinValidite() {
        return dateFinValidite;
    }

    public void setDateFinValidite(Date dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    public Boolean getEnCours(Date date) {
        return Objects.isNull(dateFinValidite) || DateUtils.after(dateFinValidite, date);
    }

}
