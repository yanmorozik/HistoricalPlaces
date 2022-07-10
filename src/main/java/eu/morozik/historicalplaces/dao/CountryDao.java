package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CountryDao extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {
    List<Country> findByName(String name);
}
