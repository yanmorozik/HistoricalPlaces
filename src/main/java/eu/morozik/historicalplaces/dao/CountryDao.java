package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryDao extends JpaRepository<Country, Long> {
}
