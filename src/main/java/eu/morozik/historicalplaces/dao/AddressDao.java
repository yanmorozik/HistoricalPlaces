package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address,Long> {
}
