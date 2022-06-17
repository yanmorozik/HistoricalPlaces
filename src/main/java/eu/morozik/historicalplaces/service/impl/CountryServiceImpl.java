package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.CountryDao;
import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Country;
import eu.morozik.historicalplaces.service.CountryService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    @Override
    public CountryDto save(CountryDto attractionDto) {
        Country country = modelMapper.map(attractionDto, Country.class);
        Country response = countryDao.save(country);
        return modelMapper.map(response, CountryDto.class);
    }

    @Override
    public CountryDto findById(Long id) throws NotFoundException {
        Country country = countryDao.findById(id).orElseThrow(() -> new NotFoundException(id));
        return modelMapper.map(country, CountryDto.class);
    }

    @Override
    public List<CountryDto> findAll() {
        List<Country> countries = countryDao.findAll();
        return (List<CountryDto>) mapperUtil.map(countries,CountryDto.class);
    }

    @Override
    public void deleteById(Long id) {
        countryDao.deleteById(id);
    }
}
