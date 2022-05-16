package eu.morozik.historicalplaces.dto.reviewdto;

import eu.morozik.historicalplaces.dto.attractiondto.AttractionSimpleDto;
import eu.morozik.historicalplaces.dto.userdto.UserSimpleDto;
import lombok.Data;

@Data
public class ReviewSimpleWithUserDto {
    private String review;

    private Long grade;

    private UserSimpleDto user;
}
