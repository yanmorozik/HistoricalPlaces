package eu.morozik.historicalplaces.model;

import eu.morozik.historicalplaces.model.BaseEntity;
import lombok.*;
import org.w3c.dom.Attr;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "similar_places")
public class SimilarPlace extends BaseEntity {

    @ManyToMany
    private Set<Attraction> attractions= new HashSet<>();
}
