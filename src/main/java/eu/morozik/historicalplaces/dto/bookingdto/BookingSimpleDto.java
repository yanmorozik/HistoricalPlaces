package eu.morozik.historicalplaces.dto.bookingdto;

import eu.morozik.historicalplaces.dto.attractiondto.AttractionSimpleDto;
import eu.morozik.historicalplaces.dto.userdto.UserSimpleDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingSimpleDto {
    private Long id;

    private LocalDateTime bookingData;

    private AttractionSimpleDto attraction;
}
