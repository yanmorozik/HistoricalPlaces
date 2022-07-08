package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AddressDao;
import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.CountryDao;
import eu.morozik.historicalplaces.dao.SettlementDao;
import eu.morozik.historicalplaces.dto.addressdto.AddressDto;
import eu.morozik.historicalplaces.dto.addressdto.AddressWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Address;
import eu.morozik.historicalplaces.model.Country;
import eu.morozik.historicalplaces.model.Settlement;
import eu.morozik.historicalplaces.service.AddressService;
import eu.morozik.historicalplaces.specification.address.AddressSpecification;
import eu.morozik.historicalplaces.utils.MapperUtil;
import eu.morozik.starter.aspect.ExecutionTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;
    private final CountryDao countryDao;
    private final AttractionDao attractionDao;
    private final SettlementDao settlementDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    private final AddressSpecification addressSpecification;

    @ExecutionTime
    @Transactional
    @Override
    public AddressDto save(AddressWithRelationIdsDto addressWithRelationIdsDto) {
        Address response = addressDao.save(reassignment(addressWithRelationIdsDto));
        return modelMapper.map(response, AddressDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public AddressDto findById(Long id) {
        Address address = addressDao.findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(id);
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        return modelMapper.map(address, AddressDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDto> findAll(int page, int size, String name) {
        Pageable pages = PageRequest.of(page, size, Sort.by(name));
        Page<Address> addresses = addressDao.findAll(pages);
        return (List<AddressDto>) mapperUtil.map(addresses.getContent(), AddressDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        attractionDao.deleteSimilarPlaces(id);
        addressDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDto> findAllByStreet(String street) {
       List<Address> addresses = addressDao.findAll(addressSpecification.streetLike(street));
       return (List<AddressDto>) mapperUtil.map(addresses,AddressDto.class);
    }

    public Address reassignment(AddressWithRelationIdsDto addressWithRelationIdsDto) {
        final Address address = modelMapper.map(addressWithRelationIdsDto, Address.class);

        Country country = countryDao.findById(addressWithRelationIdsDto.getCountryId())
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(addressWithRelationIdsDto
                            .getCountryId());
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        address.setCountry(country);

        Settlement settlement = settlementDao.findById(addressWithRelationIdsDto.getSettlementId())
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(addressWithRelationIdsDto
                            .getSettlementId());
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        address.setSettlement(settlement);

        return address;
    }
}
