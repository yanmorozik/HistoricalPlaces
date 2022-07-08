package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressDao extends JpaRepository<Address,Long>, JpaSpecificationExecutor<Address> {
}
