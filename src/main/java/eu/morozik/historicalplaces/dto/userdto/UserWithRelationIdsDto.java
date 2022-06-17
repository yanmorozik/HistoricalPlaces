package eu.morozik.historicalplaces.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithRelationIdsDto {

    private Long id;

    private String firstName;

    private String surname;

    private Long statusId;

    private Long credentialId;

    private List<Long> roleIds;
}
