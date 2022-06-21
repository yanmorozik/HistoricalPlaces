package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.userdto.UserDto;
import eu.morozik.historicalplaces.dto.userdto.UserWithRelationIdsDto;
import eu.morozik.historicalplaces.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        UserDto dto = userService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<UserDto>> findAll(@RequestParam int page,
                                                 @RequestParam int size,
                                                 @RequestParam String name) {
        List<UserDto> users = userService.findAll(page, size, name);
        return ResponseEntity.ok(users);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> update(@RequestBody UserWithRelationIdsDto userWithRelationIdsDto) {
        UserDto dto = userService.save(userWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findUserByName")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<List<UserDto>> findUserByStatusAndName(String name) {
        List<UserDto> users = userService.findUserByName(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/findBySurname")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<List<UserDto>> findBySurname(@RequestParam String surname) {
        List<UserDto> users = userService.findBySurname(surname);
        return ResponseEntity.ok(users);
    }
}
