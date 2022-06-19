package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dao.projection.view.AttractionView;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionDto;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;

import java.util.List;

public interface AttractionService {

    AttractionDto save(AttractionWithRelationIdsDto attractionWithRelationIdsDto);

    AttractionDto findById(Long id) throws NotFoundException;

    List<AttractionDto> findAll();

    void deleteById(Long id);

    AttractionView findByName(String name);
}
