package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.SettlementDto;
import eu.morozik.historicalplaces.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<SettlementDto>> findAll(@RequestParam int page,
                                                       @RequestParam int size,
                                                       @RequestParam String name) {
        List<SettlementDto> settlements = settlementService.findAll(page, size, name);
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
