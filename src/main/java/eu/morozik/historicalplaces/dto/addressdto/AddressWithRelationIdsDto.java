package eu.morozik.historicalplaces.dto.addressdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressWithRelationIdsDto {

    private Long id;

    private String street;

    private String house;

    private Long apartment;

    private Long countryId;

    private Long settlementId;
}
