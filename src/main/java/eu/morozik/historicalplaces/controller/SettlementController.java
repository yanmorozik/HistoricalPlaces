package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.SettlementDto;
import eu.morozik.historicalplaces.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/settlements")
public class SettlementController {

    private final SettlementService settlementService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SettlementDto> save(@RequestBody SettlementDto settlementDto) {
        SettlementDto dto = settlementService.save(settlementDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SettlementDto> findById(@PathVariable Long id) {
        SettlementDto dto = settlementService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<SettlementDto> findAll(@RequestParam String sort,
                                       @PageableDefault(value = 5, page = 0) Pageable pageable) {
        return settlementService.findAll(pageable);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<SettlementDto>> findAll(SearchWithThreeFiltersDto searchDto) {
        List<SettlementDto> settlements = settlementService.findAll(searchDto);
        return ResponseEntity.ok(settlements);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SettlementDto> update(@RequestBody SettlementDto settlementDto) {
        SettlementDto dto = settlementService.save(settlementDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        settlementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
