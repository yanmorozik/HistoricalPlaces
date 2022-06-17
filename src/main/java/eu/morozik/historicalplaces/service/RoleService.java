package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.RoleDto;
import eu.morozik.historicalplaces.exception.NotFoundException;

import java.util.List;

public interface RoleService {

    RoleDto save(RoleDto roleDto);

    RoleDto findById(Long id) throws NotFoundException;

    List<RoleDto> findAll();

    void deleteById(Long id);
}
