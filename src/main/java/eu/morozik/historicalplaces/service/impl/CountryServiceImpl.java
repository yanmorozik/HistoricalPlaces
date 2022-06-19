package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.CountryDao;
import eu.morozik.historicalplaces.dao.ReviewDao;
import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Attraction;
import eu.morozik.historicalplaces.model.Country;
import eu.morozik.historicalplaces.service.CountryService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;
    private final AttractionDao attractionDao;
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
    public List<CountryDto> findAll(int page, int size, String name) {
        Pageable pages = PageRequest.of(page, size, Sort.by(name));
        Page<Country> countries = countryDao.findAll(pages);
        return (List<CountryDto>) mapperUtil.map(countries.getContent(), CountryDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        attractionDao.deleteSimilarPlaces(id);
        countryDao.deleteById(id);
    }


}
