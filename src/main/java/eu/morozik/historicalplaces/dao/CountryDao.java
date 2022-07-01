package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDao extends JpaRepository<Country, Long> {
}
