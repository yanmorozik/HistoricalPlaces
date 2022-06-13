package eu.morozik.historicalplaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String street;

    private String house;

    private Long apartment;

    private CountryDto country;

    private SettlementDto settlement;
}
