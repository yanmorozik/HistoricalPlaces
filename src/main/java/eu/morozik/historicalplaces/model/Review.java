package eu.morozik.historicalplaces.model;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attraction attraction;
}
