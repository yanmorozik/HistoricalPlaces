package eu.morozik.historicalplaces.dto.attractiondto;

import eu.morozik.historicalplaces.dto.AddressDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewSimpleWithUserDto;
import eu.morozik.historicalplaces.model.Attraction;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class AttractionDto {

    private Long id;

    private String attractionName;

    private String description;

    private AddressDto address;

    private Set<ReviewSimpleWithUserDto> reviews = new HashSet<>();

    private Set<AttractionSimpleDto> attractions = new HashSet<>();
}
