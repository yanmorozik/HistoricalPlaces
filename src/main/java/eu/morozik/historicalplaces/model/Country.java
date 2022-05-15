package eu.morozik.historicalplaces.model;

import lombok.*;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;
}
