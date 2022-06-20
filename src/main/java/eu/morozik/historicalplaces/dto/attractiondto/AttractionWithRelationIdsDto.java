package eu.morozik.historicalplaces.dto.attractiondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttractionWithRelationIdsDto {

    private Long id;

    private String name;

    private String description;

    private Long address;

    private List<Long> reviewIds;

    private List<Long> attractionIds;
}
