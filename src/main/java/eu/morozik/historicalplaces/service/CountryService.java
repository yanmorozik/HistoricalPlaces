package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dao.ReviewDao;
import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Attraction;

import java.util.List;

public interface CountryService {
    CountryDto save(CountryDto countryDto);

    CountryDto findById(Long id) throws NotFoundException;

    List<CountryDto> findAll(int page,int size, String name);

    void deleteById(Long id);
}
