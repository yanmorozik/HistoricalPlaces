package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.CountryDao;
import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Country;
import eu.morozik.historicalplaces.service.CountryService;
import eu.morozik.historicalplaces.specification.common.Filter;
import eu.morozik.historicalplaces.specification.common.QueryOperator;
import eu.morozik.historicalplaces.specification.common.SpecificationCreator;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;
    private final AttractionDao attractionDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    private final SpecificationCreator<Country> creator;

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
        List<Filter> filters = checkFilters(searchDto);
        if (filters.size() > 0) {
            return (List<CountryDto>) mapperUtil.map(countryDao.findAll(getSpecificationFromFilters(filters)), CountryDto.class);
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

    private Specification<Country> getSpecificationFromFilters(List<Filter> filter) {
        Specification<Country> specification = where(creator.createSpecification(filter.remove(0)));
        for (Filter input : filter) {
            specification = specification.and(creator.createSpecification(input));
        }
        return specification;
    }

    private List<Filter> checkFilters(SearchWithThreeFiltersDto searchDto) {
        List<Filter> filters = new ArrayList<>();

        checkFilter(searchDto.getFirstField(), searchDto.getFirstOperator(), searchDto.getFirstValue(),filters);
        checkFilter(searchDto.getSecondField(), searchDto.getSecondOperator(), searchDto.getSecondValue(),filters);
        checkFilter(searchDto.getThirdField(), searchDto.getThirdOperator(), searchDto.getThirdValue(),filters);

        return filters;
    }

    private void checkFilter(String field, QueryOperator operator, String value, List<Filter> filters) {
        if (!(creator.isEmptyFilter(field, operator, value))) {
            Filter filter = Filter.builder()
                    .field(field)
                    .operator(operator)
                    .value(value)
                    .build();
            filters.add(filter);
        }
    }
}
