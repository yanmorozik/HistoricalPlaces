package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.service.api.AttractionService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    @PostMapping
    public AttractionDto save(@RequestBody AttractionDto attractionDto) {
        return attractionService.save(attractionDto);
    }

    @GetMapping("/{id}")
    public AttractionDto findById(@PathVariable Long id) throws NotFoundException {
        return attractionService.findById(id);
    }

    @GetMapping
    public List<AttractionDto> findAll() {
        return attractionService.findAll();
    }

    @PutMapping
    public AttractionDto update(@RequestBody AttractionDto attractionDto) {
        return attractionService.save(attractionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        attractionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}