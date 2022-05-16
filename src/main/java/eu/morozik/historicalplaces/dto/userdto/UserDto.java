package eu.morozik.historicalplaces.dto.userdto;

import eu.morozik.historicalplaces.dto.reviewdto.ReviewSimpleWithAttractionDto;
import eu.morozik.historicalplaces.dto.bookingdto.BookingSimpleDto;
import eu.morozik.historicalplaces.dto.CredentialDto;
import eu.morozik.historicalplaces.model.enums.Role;
import eu.morozik.historicalplaces.model.enums.Status;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private Long id;

    private String firstName;

    private String surname;

    private Role role;

    private Status status;

    private CredentialDto credential;

    private Set<BookingSimpleDto> bookings = new HashSet<>();

    private Set<ReviewSimpleWithAttractionDto> reviews = new HashSet<>();
}
