package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Country;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static eu.morozik.historicalplaces.prototype.CountryPrototype.aCountry;
import static eu.morozik.historicalplaces.prototype.CountryPrototype.aCountryFindAll;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestPropertySource(locations = "classpath:application.yaml")
@DataJpaTest

public class CountryDaoTest {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private EntityManager entityManager;

    @Test
    void save() {
        Country result = countryDao.save(aCountry());
        assertNotNull(result.getId());
    }

    @Test
    void findById(){
        Country country = countryDao.save(aCountry());
        Optional<Country> result = countryDao.findById(country.getId());
        Assertions.assertEquals(result.get().getName(), "test");
    }

    @Test
    void findAll(){
        countryDao.save(aCountry());
        countryDao.save(aCountryFindAll());
        Collection<Country> countries = countryDao.findAll();
        Assertions.assertEquals(countries.size(), 2);
    }

    @Test
    void update(){
        Country country = countryDao.save(aCountry());
        country.setName("update");
        countryDao.save(country);
        Optional<Country> result = countryDao.findById(country.getId());
        Assertions.assertEquals(result.get().getName(), "update");
    }

    @Test
    public void delete() {

        Country country = countryDao.save(aCountry());
        Long id = country.getId();
        countryDao.delete(country);
        entityManager.flush();
        Optional<Country> result = countryDao.findById(id);
        Assertions.assertEquals(Optional.empty(),result);
    }
}
