package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dao.projection.view.AttractionView;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionDto;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionWithRelationIdsDto;

import java.util.List;

public interface AttractionService {

    AttractionDto save(AttractionWithRelationIdsDto attractionWithRelationIdsDto);

    AttractionDto findById(Long id);

    List<AttractionDto> findAll(int page, int size, String name);

    void deleteById(Long id);

    AttractionView findByName(String name);
}
