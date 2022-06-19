package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.userdto.UserDto;
import eu.morozik.historicalplaces.dto.userdto.UserWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto save(@RequestBody UserWithRelationIdsDto userWithRelationIdsDto) {
        return userService.save(userWithRelationIdsDto);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) throws NotFoundException {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @PutMapping
    public UserDto update(@RequestBody UserWithRelationIdsDto userWithRelationIdsDto) {
        return userService.save(userWithRelationIdsDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findUserByName")
    List<UserDto> findUserByStatusAndName(String name){
        return userService.findUserByName(name);
    }

    @GetMapping("/findBySurname")
    List<UserDto> findBySurname(@RequestParam String surname){
        return userService.findBySurname(surname);
    }
}
