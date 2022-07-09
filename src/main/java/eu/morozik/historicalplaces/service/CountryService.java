package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {
    CountryDto save(CountryDto countryDto);

    CountryDto findById(Long id);

    Page<CountryDto> findAll(Pageable pageable);

    List<CountryDto> findAll(SearchWithThreeFiltersDto searchDto);

    List<CountryDto> findAll();

    void deleteById(Long id);
}
