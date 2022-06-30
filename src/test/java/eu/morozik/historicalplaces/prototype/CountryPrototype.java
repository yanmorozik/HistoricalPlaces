package eu.morozik.historicalplaces.prototype;

import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.model.Country;

public class CountryPrototype {

    public static Country aCountry() {

        Country country = new Country();
        country.setId(1L);
        country.setName("test");

        return country;
    }

    public static CountryDto aCountryDto(){
        return CountryDto.builder()
                .id(1L)
                .name("test")
                .build();
    }

    public static CountryDto aCountryDtoFindAll(){
        return CountryDto.builder()
                .id(3L)
                .name("Ukraine")
                .build();
    }

    public static Country aCountryFindAll() {

        Country country = new Country();
        country.setId(3L);
        country.setName("Ukraine");

        return country;
    }
}
