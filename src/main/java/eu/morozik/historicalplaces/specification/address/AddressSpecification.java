package eu.morozik.historicalplaces.specification.address;

import eu.morozik.historicalplaces.model.Address;
import eu.morozik.historicalplaces.model.Address_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public class AddressSpecification {
    public Specification<Address> streetLike(String street){
        return new Specification<Address>() {
            @Override
            public Predicate toPredicate(Root<Address> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(Address_.STREET), "%"+street+"%");
            }
        };
    }
}
