package eu.morozik.historicalplaces.dto.addressdto;

import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.dto.SettlementDto;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Long id;

    private String street;

    private String house;

    private Long apartment;

    private CountryDto country;

    private SettlementDto settlement;
}
