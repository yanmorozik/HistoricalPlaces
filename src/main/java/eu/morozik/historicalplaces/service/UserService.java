package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.userdto.UserDto;
import eu.morozik.historicalplaces.dto.userdto.UserWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;

import java.util.List;

public interface UserService {

    UserDto save(UserWithRelationIdsDto userWithRelationIdsDto);

    UserDto findById(Long id) throws NotFoundException;

    List<UserDto> findAll();

    void deleteById(Long id);
}
