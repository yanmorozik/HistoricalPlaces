package eu.morozik.historicalplaces.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countries")
public class Country extends BaseEntity{

    @Column(name = "country_name")
    private String countryName;

    @OneToMany
    private Set<Address> addresses;
}
