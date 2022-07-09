package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.addressdto.AddressDto;
import eu.morozik.historicalplaces.dto.addressdto.AddressWithRelationIdsDto;

import java.util.List;

public interface AddressService {

    AddressDto save(AddressWithRelationIdsDto addressWithRelationIdsDto);

    AddressDto findById(Long id);

    List<AddressDto> findAll(int page, int size, String name);

    List<AddressDto> findAll(SearchWithThreeFiltersDto searchDto);

    void deleteById(Long id);
}
