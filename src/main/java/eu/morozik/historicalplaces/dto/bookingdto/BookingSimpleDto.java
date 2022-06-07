package eu.morozik.historicalplaces.dto.bookingdto;

import eu.morozik.historicalplaces.dto.attractiondto.AttractionSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingSimpleDto {
    private Long id;

    private LocalDateTime bookingData;

    private AttractionSimpleDto attraction;
}
