package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.SettlementDto;

import java.util.List;

public interface SettlementService {

    SettlementDto save(SettlementDto settlementDto);

    SettlementDto findById(Long id);

    List<SettlementDto> findAll(int page, int size, String name);

    void deleteById(Long id);
}
