package eu.morozik.historicalplaces.dto;

import lombok.Data;

@Data
public class AuthenticationDto {
    private String login;
    private String password;
}
