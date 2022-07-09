package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.bookingdto.BookingDto;
import eu.morozik.historicalplaces.dto.bookingdto.BookingWithRelationIdsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    BookingDto save(BookingWithRelationIdsDto bookingWithRelationIdsDto);

    BookingDto findById(Long id);

    List<BookingDto> findAll(int page, int size, String name);

    List<BookingDto> findAll(SearchWithThreeFiltersDto searchDto);

    void deleteById(Long id);

    List<BookingDto> findAllByDateBefore(LocalDateTime date);

}
