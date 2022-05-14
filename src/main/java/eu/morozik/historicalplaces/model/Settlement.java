package eu.morozik.historicalplaces.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "settlements")
public class Settlement extends BaseEntity{

    @Column(name = "locality_name")
    private String localityName;

    @OneToMany
    private Set<Address> addresses= new HashSet<>();
}
