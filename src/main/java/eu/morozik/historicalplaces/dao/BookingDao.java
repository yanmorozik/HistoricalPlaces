package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingDao extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {

    @Query(value = "SELECT * FROM booking b where b.date < :date", nativeQuery = true)
    List<Booking> findAllByDateBefore(@Param("date") LocalDateTime date);
}