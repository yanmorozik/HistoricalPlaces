package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.dto.RoleDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.service.CountryService;
import eu.morozik.historicalplaces.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDto> save(@RequestBody RoleDto roleDto) {
        RoleDto dto = roleService.save(roleDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable Long id) {
        RoleDto dto = roleService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> findAll(@RequestParam int page,
                                                 @RequestParam int size,
                                                 @RequestParam String name) {
        List<RoleDto> roles = roleService.findAll(page, size, name);
        return ResponseEntity.ok(roles);
    }

    @PutMapping
    public ResponseEntity<RoleDto> update(@RequestBody RoleDto roleDto) {
        RoleDto dto = roleService.save(roleDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
