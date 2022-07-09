package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.CountryDao;
import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Country;
import eu.morozik.historicalplaces.service.CountryService;
import eu.morozik.historicalplaces.specification.Filter;
import eu.morozik.historicalplaces.specification.SpecificationService;
import eu.morozik.historicalplaces.utils.MapperUtil;
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
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;
    private final AttractionDao attractionDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    private final SpecificationService<Country> creator;

    @Transactional
    @Override
    public CountryDto save(CountryDto attractionDto) {
        Country country = modelMapper.map(attractionDto, Country.class);
        Country response = countryDao.save(country);
        return modelMapper.map(response, CountryDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public CountryDto findById(Long id) {
        Country country = countryDao.findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(id);
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        return modelMapper.map(country, CountryDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CountryDto> findAll(int page, int size, String name) {
        Pageable pages = PageRequest.of(page, size, Sort.by(name));
        Page<Country> countries = countryDao.findAll(pages);
        return (List<CountryDto>) mapperUtil.map(countries.getContent(), CountryDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CountryDto> findAll(SearchWithThreeFiltersDto searchDto) {
        List<Filter> filters = creator.checkFilters(searchDto);
        if (filters.size() > 0) {
            return (List<CountryDto>) mapperUtil.map(countryDao.findAll(creator.getSpecificationFromFilters(filters)), CountryDto.class);
        } else {
            return (List<CountryDto>) mapperUtil.map(countryDao.findAll(), CountryDto.class);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CountryDto> findAll() {
        List<Country> countries = countryDao.findAll();
        return (List<CountryDto>) mapperUtil.map(countries, CountryDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        attractionDao.deleteSimilarPlaces(id);
        countryDao.deleteById(id);
    }
}
