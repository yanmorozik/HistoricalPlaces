package eu.morozik.historicalplaces.dto.bookingdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingWithRelationIdsDto {
    private Long id;

    private LocalDateTime date;

    private Long userId;

    private Long attractionId;
}
