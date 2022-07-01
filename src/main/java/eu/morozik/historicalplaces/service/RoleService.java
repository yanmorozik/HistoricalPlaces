package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto save(RoleDto roleDto);

    RoleDto findById(Long id);

    List<RoleDto> findAll(int page, int size, String name);

    void deleteById(Long id);
}
