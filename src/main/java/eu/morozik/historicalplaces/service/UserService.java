package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.AuthenticationDto;
import eu.morozik.historicalplaces.dto.AuthenticationDtoWithToken;
import eu.morozik.historicalplaces.dto.userdto.UserDto;
import eu.morozik.historicalplaces.dto.userdto.UserWithRelationIdsDto;

import java.util.List;

public interface UserService {

    UserDto save(UserWithRelationIdsDto userWithRelationIdsDto);

    UserDto findById(Long id);

    List<UserDto> findAll(int page, int size, String name);

    void deleteById(Long id);

    List<UserDto> findUserByName(String name);

    List<UserDto> findBySurname(String surname);

    AuthenticationDtoWithToken authenticate(AuthenticationDto dto);

    UserDto registration(UserDto userDto);
}
