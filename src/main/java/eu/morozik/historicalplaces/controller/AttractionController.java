package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dao.projection.view.AttractionView;
import eu.morozik.historicalplaces.dto.addressdto.AddressDto;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionWithRelationIdsDto;
import eu.morozik.historicalplaces.service.AttractionService;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    @PostMapping
    public ResponseEntity<AttractionDto> save(@RequestBody AttractionWithRelationIdsDto attractionWithRelationIdsDto) {
       AttractionDto dto = attractionService.save(attractionWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttractionDto> findById(@PathVariable Long id){
        AttractionDto dto = attractionService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<AttractionDto>> findAll(@RequestParam int page,
                                    @RequestParam int size,
                                    @RequestParam String name) {
        List<AttractionDto> attractions = attractionService.findAll(page, size, name);
        return ResponseEntity.ok(attractions);
    }

    @PutMapping
    public ResponseEntity<AttractionDto> update(@RequestBody AttractionWithRelationIdsDto attractionWithRelationIdsDto) {
        AttractionDto dto = attractionService.save(attractionWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        attractionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findByName")
    public ResponseEntity<AttractionView> findByName(@RequestParam String name){
        AttractionView view = attractionService.findByName(name);
        return ResponseEntity.ok(view);
    }
}
