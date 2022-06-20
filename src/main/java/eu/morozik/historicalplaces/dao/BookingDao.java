package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface BookingDao extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM booking b where b.date < :date", nativeQuery = true)
    List<Booking> findAllByDateBefore(@Param("date") LocalDateTime date);
}