package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.service.api.CountryService;
import eu.morozik.historicalplaces.dto.CountryDto;
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
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    @PostMapping
    public CountryDto save(@RequestBody CountryDto  countryDto) {
        return countryService.save(countryDto);
    }

    @GetMapping("/{id}")
    public CountryDto findById(@PathVariable Long id) throws NotFoundException {
        return countryService.findById(id);
    }

    @GetMapping
    public List<CountryDto> findAll() {
        return countryService.findAll();
    }

    @PutMapping
    public CountryDto update(@RequestBody CountryDto countryDto) {
        return countryService.save(countryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        countryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
