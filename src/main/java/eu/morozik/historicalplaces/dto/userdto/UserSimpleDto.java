package eu.morozik.historicalplaces.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleDto {
    private Long id;

    private String firstName;

    private String surname;
}
