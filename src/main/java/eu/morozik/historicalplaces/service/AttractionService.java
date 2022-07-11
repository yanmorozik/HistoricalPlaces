package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.GeneralObjectDto;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionDto;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionWithRelationIdsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttractionService {

    AttractionDto save(AttractionWithRelationIdsDto attractionWithRelationIdsDto);

    AttractionDto findById(Long id);

    Page<AttractionDto> findAll(Pageable pageable);

    List<AttractionDto> findAll(SearchWithThreeFiltersDto searchDto);

    void deleteById(Long id);

    GeneralObjectDto searchAsGlobal(String name);
}
