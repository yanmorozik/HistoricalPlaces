package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.SettlementDto;
import eu.morozik.historicalplaces.exception.NotFoundException;

import java.util.List;

public interface SettlementService {

    SettlementDto save(SettlementDto settlementDto);

    SettlementDto findById(Long id) throws NotFoundException;

    List<SettlementDto> findAll();

    void deleteById(Long id);
}
