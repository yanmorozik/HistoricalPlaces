package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.AuthenticationDto;
import eu.morozik.historicalplaces.dto.AuthenticationDtoWithToken;
import eu.morozik.historicalplaces.dto.GeneralObjectDto;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.userdto.UserDto;
import eu.morozik.historicalplaces.dto.userdto.UserWithRelationIdsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDto save(UserWithRelationIdsDto userWithRelationIdsDto);

    UserDto findById(Long id);

    Page<UserDto> findAll(Pageable pageable);

    List<UserDto> findAll(SearchWithThreeFiltersDto searchDto);

    void deleteById(Long id);

    List<UserDto> findUserByName(String name);

    List<UserDto> findBySurname(String surname);

    AuthenticationDtoWithToken authenticate(AuthenticationDto dto);

    UserDto registration(UserDto userDto);

    GeneralObjectDto searchAsGlobal(String name);
}
