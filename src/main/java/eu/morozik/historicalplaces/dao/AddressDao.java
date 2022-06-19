package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Address;
import eu.morozik.historicalplaces.model.Country;
import eu.morozik.historicalplaces.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao extends JpaRepository<Address,Long> {
}
