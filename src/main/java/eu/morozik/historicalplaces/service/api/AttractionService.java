package eu.morozik.historicalplaces.service.api;

import eu.morozik.historicalplaces.dto.attractiondto.AttractionDto;
import eu.morozik.historicalplaces.exception.NotFoundException;

import java.util.List;

public interface AttractionService {

    AttractionDto save(AttractionDto attractionDto);

    AttractionDto findById(Long id) throws NotFoundException;

    List<AttractionDto> findAll();

    void deleteById(Long id);
}
