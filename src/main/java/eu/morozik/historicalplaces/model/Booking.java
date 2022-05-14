package eu.morozik.historicalplaces.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking extends BaseEntity{

    @Column(name = "booking_date")
    private LocalDateTime bookingData;

    @ManyToOne
    private User user;

    @ManyToOne
    private Attraction attraction;
}
