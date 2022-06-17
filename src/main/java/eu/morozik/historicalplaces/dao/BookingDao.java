package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository<Booking,Long> {
}