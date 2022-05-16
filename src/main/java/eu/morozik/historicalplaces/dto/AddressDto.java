package eu.morozik.historicalplaces.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String street;

    private String house;

    private Long apartment;

    private CountryDto country;

    private SettlementDto settlement;
}
