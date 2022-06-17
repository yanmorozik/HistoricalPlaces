package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.RoleDto;
import eu.morozik.historicalplaces.dto.SettlementDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.service.RoleService;
import eu.morozik.historicalplaces.service.SettlementService;
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
@RequestMapping("/settlements")
public class SettlementController {

    private final SettlementService settlementService;

    @PostMapping
    public SettlementDto save(@RequestBody SettlementDto  settlementDto) {
        return settlementService.save(settlementDto);
    }

    @GetMapping("/{id}")
    public SettlementDto findById(@PathVariable Long id) throws NotFoundException {
        return settlementService.findById(id);
    }

    @GetMapping
    public List<SettlementDto> findAll() {
        return settlementService.findAll();
    }

    @PutMapping
    public SettlementDto update(@RequestBody SettlementDto settlementDto) {
        return settlementService.save(settlementDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        settlementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
