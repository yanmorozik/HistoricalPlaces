package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.service.CountryService;
import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    @PostMapping
    public ResponseEntity<CountryDto> save(@RequestBody CountryDto countryDto) {
        CountryDto dto = countryService.save(countryDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> findById(@PathVariable Long id) {
        CountryDto dto = countryService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CountryDto>> findAll(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestParam String name) {
        List<CountryDto> counties = countryService.findAll(page, size, name);
        return ResponseEntity.ok(counties);
    }

    @PutMapping
    public ResponseEntity<CountryDto> update(@RequestBody CountryDto countryDto) {
        CountryDto dto = countryService.save(countryDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        countryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
