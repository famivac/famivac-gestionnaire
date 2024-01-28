package fr.famivac.gestionnaire.domains.parametres.entity;

import fr.famivac.gestionnaire.commons.utils.DateUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
