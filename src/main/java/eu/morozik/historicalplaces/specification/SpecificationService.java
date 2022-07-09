package eu.morozik.historicalplaces.specification;

import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.model.BaseEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class SpecificationService<T extends BaseEntity> {

    public Specification<T> createSpecification(Filter input) {
        switch (input.getOperator()) {
            case EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case NOT_EQ:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(input.getField()),
                                (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case LESS_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(input.getField()),
                                (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case LIKE:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()), "%" + input.getValue() + "%");
            case IN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(input.getField()))
                                .value(castToRequiredType(root.get(input.getField()).getJavaType(), String.valueOf(input.getValues())));
            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }

    private Object castToRequiredType(Class fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        } else if (fieldType.isAssignableFrom(Long.class)) {
            return Long.valueOf(value);
        } else if (fieldType.isAssignableFrom(String.class)) {
            return String.valueOf(value);
        }

        return null;
    }

    public boolean isEmptyFilter(String field, QueryOperator operator, String value) {
        return field == null && operator == null && value == null;
    }

    public Specification<T> getSpecificationFromFilters(List<Filter> filter) {
        Specification<T> specification;
        specification = where(createSpecification(filter.remove(0)));
        for (Filter input : filter) {
            specification = specification.and(createSpecification(input));
        }
        return specification;
    }

    public List<Filter> checkFilters(SearchWithThreeFiltersDto searchDto) {
        List<Filter> filters = new ArrayList<>();

        checkFilter(searchDto.getFirstField(), searchDto.getFirstOperator(), searchDto.getFirstValue(), filters);
        checkFilter(searchDto.getSecondField(), searchDto.getSecondOperator(), searchDto.getSecondValue(), filters);
        checkFilter(searchDto.getThirdField(), searchDto.getThirdOperator(), searchDto.getThirdValue(), filters);

        return filters;
    }

    private void checkFilter(String field, QueryOperator operator, String value, List<Filter> filters) {
        if (!(isEmptyFilter(field, operator, value))) {
            Filter filter = Filter.builder()
                    .field(field)
                    .operator(operator)
                    .value(value)
                    .build();
            filters.add(filter);
        }
    }
}
