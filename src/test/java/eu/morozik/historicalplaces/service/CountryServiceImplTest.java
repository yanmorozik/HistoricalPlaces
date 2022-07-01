package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.CountryDao;
import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.service.impl.CountryServiceImpl;
import eu.morozik.historicalplaces.utils.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static eu.morozik.historicalplaces.prototype.CountryPrototype.aCountry;
import static eu.morozik.historicalplaces.prototype.CountryPrototype.aCountryDto;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceImplTest {

    @Mock
    private CountryDao countryDao;
    @Mock
    private AttractionDao attractionDao;
    private CountryService countryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ModelMapper modelMapper = new ModelMapper();
        MapperUtil mapperUtil = new MapperUtil(modelMapper);
        countryService = new CountryServiceImpl(countryDao, attractionDao, modelMapper, mapperUtil);

    }

    @Test
    void save() {
        when(countryDao.save(any())).thenReturn(aCountry());
        CountryDto createdCountry = countryService.save(aCountryDto());
        assertThat(createdCountry.getName()).isEqualTo(aCountryDto().getName());
    }

    @Test
    void findById() {
        when(countryDao.findById(any())).thenReturn(java.util.Optional.of(aCountry()));
        CountryDto countryDto = countryService.findById(1L);
        assertThat(countryDto.getName()).isEqualTo(aCountry().getName());
        verify(countryDao, times(1)).findById(any());
    }

    @Test
    void findAll() {
        when(countryDao.findAll()).thenReturn(Collections.singletonList(aCountry()));
        List<CountryDto> countryDtoList = countryService.findAll();
        assert (countryDtoList.get(0).getName().equals(aCountry().getName()));
        verify(countryDao, times(1)).findAll();
    }

    @Test
    public void update() {
        when(countryDao.save(any())).thenReturn(aCountry());
        CountryDto createdCountry = countryService.save(aCountryDto());
        assertThat(createdCountry.getName()).isEqualTo(aCountryDto().getName());
    }

    @Test
    void deleteById() {
        doNothing().when(countryDao).deleteById(any());
        countryService.deleteById(1L);
        verify(countryDao, times(1)).deleteById(1L);
    }
}
