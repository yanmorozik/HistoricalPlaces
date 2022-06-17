package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.exception.NotFoundException;

import java.util.List;

public interface CountryService {
    CountryDto save(CountryDto countryDto);

    CountryDto findById(Long id) throws NotFoundException;

    List<CountryDto> findAll();

    void deleteById(Long id);
}
