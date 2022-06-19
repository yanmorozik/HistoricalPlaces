package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.bookingdto.BookingDto;
import eu.morozik.historicalplaces.dto.bookingdto.BookingWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    BookingDto save(BookingWithRelationIdsDto bookingWithRelationIdsDto);

    BookingDto findById(Long id) throws NotFoundException;

    List<BookingDto> findAll();

    void deleteById(Long id);

    List<BookingDto> findAllByDateBefore(LocalDateTime date);
}
