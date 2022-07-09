package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.SettlementDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SettlementService {

    SettlementDto save(SettlementDto settlementDto);

    SettlementDto findById(Long id);

    Page<SettlementDto> findAll(Pageable pageable);

    List<SettlementDto> findAll(SearchWithThreeFiltersDto searchDto);

    void deleteById(Long id);
}
