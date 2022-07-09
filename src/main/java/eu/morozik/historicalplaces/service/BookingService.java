package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.bookingdto.BookingDto;
import eu.morozik.historicalplaces.dto.bookingdto.BookingWithRelationIdsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    BookingDto save(BookingWithRelationIdsDto bookingWithRelationIdsDto);

    BookingDto findById(Long id);

    Page<BookingDto> findAll(Pageable pageable);

    List<BookingDto> findAll(SearchWithThreeFiltersDto searchDto);

    void deleteById(Long id);

    List<BookingDto> findAllByDateBefore(LocalDateTime date);
}
