package eu.morozik.historicalplaces.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "country")
public class Country extends BaseEntity{

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;
}
