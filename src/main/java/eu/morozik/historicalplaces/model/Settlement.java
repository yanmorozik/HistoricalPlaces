package eu.morozik.historicalplaces.model;

import lombok.*;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "settlement", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses= new HashSet<>();
}
