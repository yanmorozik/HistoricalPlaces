package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;

import java.util.List;

public interface CountryService {
    CountryDto save(CountryDto countryDto);

    CountryDto findById(Long id);

    List<CountryDto> findAll(int page, int size, String name);

    List<CountryDto> findAll(SearchWithThreeFiltersDto searchDto);

    List<CountryDto> findAll();

    void deleteById(Long id);

}
