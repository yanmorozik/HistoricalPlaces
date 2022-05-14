package eu.morozik.historicalplaces.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review extends BaseEntity{

    @Column(name = "review")
    private String review;

    @Column(name = "grade")
    private Long grade;

    @ManyToOne
    private User user;

    @ManyToOne
    private Attraction attraction;
}
