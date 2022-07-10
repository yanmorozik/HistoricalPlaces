package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Country;
import eu.morozik.historicalplaces.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SettlementDao extends JpaRepository<Settlement, Long>, JpaSpecificationExecutor<Settlement> {
    List<Settlement> findByName(String name);

}
