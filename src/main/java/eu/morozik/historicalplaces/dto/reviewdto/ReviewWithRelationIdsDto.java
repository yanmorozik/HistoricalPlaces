package eu.morozik.historicalplaces.dto.reviewdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewWithRelationIdsDto {

    private Long id;

    private String review;

    private Long grade;

    private Long userId;

    private Long attractionId;
}
