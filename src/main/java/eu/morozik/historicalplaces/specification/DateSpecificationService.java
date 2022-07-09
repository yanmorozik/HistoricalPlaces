package eu.morozik.historicalplaces.specification;

import eu.morozik.historicalplaces.model.Booking;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class DateSpecificationService extends SpecificationService<Booking> {

    public Specification<Booking> createSpecificationWithDate(Filter input) {
        LocalDateTime time = LocalDateTime.parse(input.getValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        switch (input.getOperator()) {
            case EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(input.getField()), time);
            case NOT_EQ:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(input.getField()), time);

            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.greaterThanOrEqualTo(root.get(input.getField()), time);

            case LESS_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lessThanOrEqualTo(root.get(input.getField()), time);
            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }

    public Specification<Booking> getSpecificationFromFilters(List<Filter> filter) {
        Specification<Booking> specification;
        specification = where(createSpecificationWithDate(filter.remove(0)));
        for (Filter input : filter) {
            specification = specification.and(createSpecificationWithDate(input));
        }
        return specification;
    }

}
