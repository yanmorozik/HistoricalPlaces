package eu.morozik.historicalplaces.dto.reviewdto;

import eu.morozik.historicalplaces.dto.attractiondto.AttractionSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSimpleWithAttractionDto {
    private String review;

    private Long grade;

    private AttractionSimpleDto attraction;
}
