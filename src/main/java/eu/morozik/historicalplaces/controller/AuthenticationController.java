package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.AuthenticationDto;
import eu.morozik.historicalplaces.dto.AuthenticationDtoWithToken;
import eu.morozik.historicalplaces.dto.userdto.UserDto;
import eu.morozik.historicalplaces.service.UserService;
import eu.morozik.starter.aspect.ExecutionTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @ExecutionTime
    @PostMapping("/login")
    public ResponseEntity<AuthenticationDtoWithToken> authenticate(@RequestBody AuthenticationDto dto) {
        return ResponseEntity.ok(userService.authenticate(dto));
    }

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.registration(userDto));
    }

    @PostMapping("/logout")
    public void logout (HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response,null);
    }
}
