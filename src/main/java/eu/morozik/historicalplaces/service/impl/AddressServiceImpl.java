package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AddressDao;
import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.CountryDao;
import eu.morozik.historicalplaces.dao.SettlementDao;
import eu.morozik.historicalplaces.dto.addressdto.AddressDto;
import eu.morozik.historicalplaces.dto.addressdto.AddressWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Address;
import eu.morozik.historicalplaces.model.Attraction;
import eu.morozik.historicalplaces.model.Country;
import eu.morozik.historicalplaces.model.Settlement;
import eu.morozik.historicalplaces.service.AddressService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;
    private final CountryDao countryDao;
    private final AttractionDao attractionDao;
    private final SettlementDao settlementDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;


    @Override
    public AddressDto save(AddressWithRelationIdsDto addressWithRelationIdsDto) {
        Address response = addressDao.save(reassignment(addressWithRelationIdsDto));
        return modelMapper.map(response, AddressDto.class);
    }

    @Override
    public AddressDto findById(Long id) throws NotFoundException {
        Address address = addressDao.findById(id).orElseThrow(() -> new NotFoundException(id));
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public List<AddressDto> findAll() {
        List<Address> addresses = addressDao.findAll();
        return (List<AddressDto>) mapperUtil.map(addresses,AddressDto.class);
    }

    @Override
    public void deleteById(Long id) {
        addressDao.deleteById(id);
    }

    public Address reassignment(AddressWithRelationIdsDto addressWithRelationIdsDto) {
        final Address address = modelMapper.map(addressWithRelationIdsDto,Address.class);

        Country country = countryDao.findById(addressWithRelationIdsDto.getCountryId())
                .orElseThrow(() -> new NotFoundException(addressWithRelationIdsDto.getCountryId()));
        address.setCountry(country);

        Settlement settlement = settlementDao.findById(addressWithRelationIdsDto.getSettlementId())
                .orElseThrow(() -> new NotFoundException(addressWithRelationIdsDto.getSettlementId()));
        address.setSettlement(settlement);

        return address;
    }
}
