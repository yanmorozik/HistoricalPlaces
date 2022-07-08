package eu.morozik.historicalplaces.specification.address;

import eu.morozik.historicalplaces.model.Review;
import eu.morozik.historicalplaces.model.Review_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public class ReviewSpecification {
    public Specification<Review> gradeGreaterThan(Long grade) {
        return new Specification<Review>() {
            @Override
            public Predicate toPredicate(Root<Review> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.get(Review_.GRADE), grade);
            }
        };
    }
}
