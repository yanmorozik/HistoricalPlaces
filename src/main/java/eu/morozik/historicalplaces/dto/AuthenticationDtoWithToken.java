package eu.morozik.historicalplaces.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationDtoWithToken {
    private String login;
    private String token;
}
