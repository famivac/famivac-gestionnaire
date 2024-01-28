package fr.famivac.gestionnaire.domains.enfants.boundary;

import fr.famivac.gestionnaire.domains.enfants.entity.Enfant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EnfantDTO {

    private Long id;

    private String nomEnfant;

    private String prenomEnfant;

    public EnfantDTO() {

    }

    public EnfantDTO(Enfant bean) {
        id = bean.getId();
        nomEnfant = bean.getNom();
        prenomEnfant = bean.getPrenom();
    }
}
