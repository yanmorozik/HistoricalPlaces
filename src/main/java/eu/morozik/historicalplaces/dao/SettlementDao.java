package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SettlementDao extends JpaRepository<Settlement, Long>, JpaSpecificationExecutor<Settlement> {
}
