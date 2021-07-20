package fr.famivac.gestionnaire.domains.enfants.entity.views;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnfantListView {

  private Long id;

  private String nom;

  private String prenom;
}
