package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.userdto.UserDto;
import eu.morozik.historicalplaces.dto.userdto.UserWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;

import java.util.List;

public interface UserService {

    UserDto save(UserWithRelationIdsDto userWithRelationIdsDto);

    UserDto findById(Long id);

    List<UserDto> findAll(int page,int size, String name);

    void deleteById(Long id);

    List<UserDto> findUserByName( String name);

    List<UserDto> findBySurname(String surname);

    UserDto registration(UserDto userDto);
}
