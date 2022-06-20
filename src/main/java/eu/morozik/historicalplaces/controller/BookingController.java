package eu.morozik.historicalplaces.controller;

import eu.morozik.historicalplaces.dto.bookingdto.BookingDto;
import eu.morozik.historicalplaces.dto.bookingdto.BookingWithRelationIdsDto;
import eu.morozik.historicalplaces.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> save(@RequestBody BookingWithRelationIdsDto bookingWithRelationIdsDto) {
        BookingDto dto = bookingService.save(bookingWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> findById(@PathVariable Long id) {
        BookingDto dto = bookingService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> findAll(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestParam String name) {
        List<BookingDto> bookings = bookingService.findAll(page, size, name);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping
    public ResponseEntity<BookingDto> update(@RequestBody BookingWithRelationIdsDto bookingWithRelationIdsDto) {
        BookingDto dto = bookingService.save(bookingWithRelationIdsDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAllByDateBefore")
    public ResponseEntity<List<BookingDto>> findAllByDateBefore(@RequestParam("date")
                                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                        LocalDateTime date) {
        List<BookingDto> bookings = bookingService.findAllByDateBefore(date);
        return ResponseEntity.ok(bookings);
    }
}
