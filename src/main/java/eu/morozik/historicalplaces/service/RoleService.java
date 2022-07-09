package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.RoleDto;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;

import java.util.List;

public interface RoleService {

    RoleDto save(RoleDto roleDto);

    RoleDto findById(Long id);

    List<RoleDto> findAll(int page, int size, String name);

    List<RoleDto> findAll(SearchWithThreeFiltersDto searchDto);

    void deleteById(Long id);
}
