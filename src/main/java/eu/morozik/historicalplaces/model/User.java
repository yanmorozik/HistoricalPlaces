package eu.morozik.historicalplaces.model;

import eu.morozik.historicalplaces.model.enums.Role;
import eu.morozik.historicalplaces.model.enums.Status;
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
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "telephone")
    private String telephone;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToOne
    private Credential credential;

    @OneToMany
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany
    private Set<Review> reviews = new HashSet<>();

}
