package es.iesrafaelalberti.clasesspring2223.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrisonerCellDTO implements Serializable {
    private Long prisoner_id;
    private Integer cell_number;
}
