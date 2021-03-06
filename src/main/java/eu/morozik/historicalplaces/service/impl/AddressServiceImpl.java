package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AddressDao;
import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.CountryDao;
import eu.morozik.historicalplaces.dao.SettlementDao;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.addressdto.AddressDto;
import eu.morozik.historicalplaces.dto.addressdto.AddressWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Address;
import eu.morozik.historicalplaces.model.Country;
import eu.morozik.historicalplaces.model.Settlement;
import eu.morozik.historicalplaces.service.AddressService;
import eu.morozik.historicalplaces.specification.Filter;
import eu.morozik.historicalplaces.specification.SpecificationService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import eu.morozik.starter.aspect.ExecutionTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final SpecificationService<Address> creator;

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

    @Override
    public Page<AddressDto> findAll(Pageable pageable) {
        Page<Address> pagesDto = addressDao.findAll(pageable);
        return addressDao.findAll(pageable)
                .map(address -> modelMapper.map(address, AddressDto.class));
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDto> findAll(SearchWithThreeFiltersDto searchDto) {
        List<Filter> filters = creator.checkFilters(searchDto);
        if (filters.size() > 0) {
            return (List<AddressDto>) mapperUtil.map(addressDao.findAll(creator.getSpecificationFromFilters(filters)), AddressDto.class);
        } else {
            return (List<AddressDto>) mapperUtil.map(addressDao.findAll(), AddressDto.class);
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        attractionDao.deleteSimilarPlaces(id);
        addressDao.deleteById(id);
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
