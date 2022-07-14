package eu.morozik.historicalplaces.dto.userdto;

import eu.morozik.historicalplaces.dto.CredentialDto;
import eu.morozik.historicalplaces.dto.RoleDto;
import eu.morozik.historicalplaces.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String firstName;

    private String surname;

    private Status status;

    private CredentialDto credential;

    private Set<RoleDto> roles = new HashSet<>();
}
