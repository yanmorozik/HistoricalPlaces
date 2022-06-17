package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.addressdto.AddressDto;
import eu.morozik.historicalplaces.dto.addressdto.AddressWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.service.AddressService;
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
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public AddressDto save(@RequestBody AddressWithRelationIdsDto addressWithRelationIdsDto) {
        return addressService.save(addressWithRelationIdsDto);
    }

    @GetMapping("/{id}")
    public AddressDto findById(@PathVariable Long id) throws NotFoundException {
        return addressService.findById(id);
    }

    @GetMapping
    public List<AddressDto> findAll() {
        return addressService.findAll();
    }

    @PutMapping
    public AddressDto update(@RequestBody AddressWithRelationIdsDto addressWithRelationIdsDto) {
        return addressService.save(addressWithRelationIdsDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
