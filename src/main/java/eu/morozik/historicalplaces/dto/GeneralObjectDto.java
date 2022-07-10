package eu.morozik.historicalplaces.dto;

import eu.morozik.historicalplaces.model.enums.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralObjectDto {
    String name;
    Entity type;
}
