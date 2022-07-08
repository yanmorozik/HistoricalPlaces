package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.addressdto.AddressDto;
import eu.morozik.historicalplaces.dto.addressdto.AddressWithRelationIdsDto;

import java.util.List;

public interface AddressService {

    AddressDto save(AddressWithRelationIdsDto addressWithRelationIdsDto);

    AddressDto findById(Long id);

    List<AddressDto> findAll(int page, int size, String name);

    void deleteById(Long id);

    List<AddressDto> findAllByStreet(String street);
}
