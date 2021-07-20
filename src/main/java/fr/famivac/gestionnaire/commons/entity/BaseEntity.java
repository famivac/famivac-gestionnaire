package fr.famivac.gestionnaire.commons.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "ID")
  @EqualsAndHashCode.Include
  private Long id;
}
