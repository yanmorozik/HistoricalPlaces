package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.GeneralObjectDto;
import eu.morozik.historicalplaces.service.GlobalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/globalSearch")
public class GlobalController {

    private final GlobalService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralObjectDto> findAll(@RequestParam String name) {
        return ResponseEntity.ok(service.findEntityByName(name));
    }
}
