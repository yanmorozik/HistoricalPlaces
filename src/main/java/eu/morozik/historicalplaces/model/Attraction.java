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
@Table(name = "attractions")
public class Attraction extends BaseEntity {

    @Column(name = "attraction_name")
    private String attractionName;

    @Column(name = "description")
    private String description;

    @OneToOne
    private Address address;

    @OneToMany
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany
    private Set<SimilarPlace> similarPlaces = new HashSet<>();
}
