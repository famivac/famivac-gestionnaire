package fr.famivac.gestionnaire.domains.familles.entity;

import fr.famivac.gestionnaire.commons.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/** @author Paolo */
@Entity
@Getter
@Setter
public class Chambre extends BaseEntity {

  @Id @GeneratedValue private Long id;

  private Integer nombreLits;

  @ManyToOne private Famille famille;

  protected Chambre() {
    nombreLits = 0;
  }

  public Chambre(Integer nombreLits, Famille famille) {
    if (famille == null) {
      throw new IllegalArgumentException("Préciser la famille");
    }
    this.nombreLits = nombreLits;
    this.famille = famille;
  }
}
