package eu.morozik.historicalplaces.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "\"review\"")
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
