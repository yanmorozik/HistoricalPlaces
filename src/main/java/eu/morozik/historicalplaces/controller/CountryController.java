package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
@Api(tags = "Countries")
public class CountryController {

    private final CountryService countryService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CountryDto> save(@RequestBody CountryDto countryDto) {
        CountryDto dto = countryService.save(countryDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "This method return country by id.")
    public ResponseEntity<CountryDto> findById(@PathVariable Long id) {
        CountryDto dto = countryService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<CountryDto>> findAll(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestParam String name) {
        List<CountryDto> counties = countryService.findAll(page, size, name);
        return ResponseEntity.ok(counties);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CountryDto> update(@RequestBody CountryDto countryDto) {
        CountryDto dto = countryService.save(countryDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        countryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
