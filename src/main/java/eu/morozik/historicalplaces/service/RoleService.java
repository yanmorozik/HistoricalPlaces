package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.RoleDto;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    RoleDto save(RoleDto roleDto);

    RoleDto findById(Long id);

    Page<RoleDto> findAll(Pageable pageable);

    List<RoleDto> findAll(SearchWithThreeFiltersDto searchDto);

    void deleteById(Long id);
}
