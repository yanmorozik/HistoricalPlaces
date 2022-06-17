package eu.morozik.historicalplaces.dto.attractiondto;

import eu.morozik.historicalplaces.dto.addressdto.AddressDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewSimpleWithUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDto {

    private Long id;

    private String name;

    private String description;

    private AddressDto address;

    private Set<ReviewSimpleWithUserDto> reviews = new HashSet<>();

    private Set<AttractionSimpleDto> similarAttractions = new HashSet<>();
}
