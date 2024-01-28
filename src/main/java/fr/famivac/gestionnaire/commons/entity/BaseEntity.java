package fr.famivac.gestionnaire.commons.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
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
