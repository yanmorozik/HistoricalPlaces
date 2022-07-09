package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.addressdto.AddressDto;
import eu.morozik.historicalplaces.dto.addressdto.AddressWithRelationIdsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {

    AddressDto save(AddressWithRelationIdsDto addressWithRelationIdsDto);

    AddressDto findById(Long id);

    Page<AddressDto> findAll(Pageable pageable);

    List<AddressDto> findAll(SearchWithThreeFiltersDto searchDto);

    void deleteById(Long id);
}
