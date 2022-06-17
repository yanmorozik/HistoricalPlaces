package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.addressdto.AddressDto;
import eu.morozik.historicalplaces.dto.addressdto.AddressWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;

import java.util.List;

public interface AddressService {

    AddressDto save(AddressWithRelationIdsDto addressWithRelationIdsDto);

    AddressDto findById(Long id) throws NotFoundException;

    List<AddressDto> findAll();

    void deleteById(Long id);
}
