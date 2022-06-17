package eu.morozik.historicalplaces.dto.bookingdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionSimpleDto;
import eu.morozik.historicalplaces.dto.userdto.UserSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long id;

    private LocalDateTime date;

    private UserSimpleDto user;

    private AttractionSimpleDto attraction;
}
