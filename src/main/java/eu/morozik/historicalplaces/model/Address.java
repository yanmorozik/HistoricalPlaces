package eu.morozik.historicalplaces.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address extends BaseEntity {

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "apartment")
    private Long apartment;

    @ManyToOne
    private Country country;

    @ManyToOne
    private Settlement settlement;

    @OneToOne
    private Attraction attraction;
}
