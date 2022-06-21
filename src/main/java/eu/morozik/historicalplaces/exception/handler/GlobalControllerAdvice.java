package eu.morozik.historicalplaces.exception.handler;

import eu.morozik.historicalplaces.dto.ErrorMessageDto;
import eu.morozik.historicalplaces.exception.JwtAuthenticationException;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.exception.NotFoundGradeException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorMessageDto handleNotFoundException(NotFoundException notFoundException) {
        return ErrorMessageDto.builder()
                .message(notFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(NotFoundGradeException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorMessageDto handleNotFoundGradException(NotFoundGradeException notFoundGradeException) {
        return ErrorMessageDto.builder()
                .message(notFoundGradeException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorMessageDto handleNotFoundGradException(UsernameNotFoundException usernameNotFoundException) {
        return ErrorMessageDto.builder()
                .message(usernameNotFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorMessageDto handleNotFoundGradException(JwtException jwtException) {
        return ErrorMessageDto.builder()
                .message(jwtException.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorMessageDto handleNotFoundGradException(IllegalArgumentException illegalArgumentException) {
        return ErrorMessageDto.builder()
                .message(illegalArgumentException.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorMessageDto handleNotFoundGradException(AuthenticationException authenticationException) {
        return ErrorMessageDto.builder()
                .message(authenticationException.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorMessageDto handleNotFoundGradException(JwtAuthenticationException jwtAuthenticationException) {
        return ErrorMessageDto.builder()
                .message(jwtAuthenticationException.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .timestamp(now())
                .build();
    }
}
