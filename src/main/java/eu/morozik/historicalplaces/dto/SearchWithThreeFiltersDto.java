package eu.morozik.historicalplaces.dto;

import eu.morozik.historicalplaces.specification.common.QueryOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchWithThreeFiltersDto {
    private String firstField;
    private QueryOperator firstOperator;
    private String firstValue;

    private String secondField;
    private QueryOperator secondOperator;
    private String secondValue;

    private String thirdField;
    private QueryOperator thirdOperator;
    private String thirdValue;
}
