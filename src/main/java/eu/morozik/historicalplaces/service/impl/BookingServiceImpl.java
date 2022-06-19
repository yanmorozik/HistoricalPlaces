package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.BookingDao;
import eu.morozik.historicalplaces.dao.UserDao;
import eu.morozik.historicalplaces.dto.bookingdto.BookingDto;
import eu.morozik.historicalplaces.dto.bookingdto.BookingWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Attraction;
import eu.morozik.historicalplaces.model.Booking;
import eu.morozik.historicalplaces.model.User;
import eu.morozik.historicalplaces.service.BookingService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final UserDao userDao;
    private final AttractionDao attractionDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    @Transactional
    @Override
    public BookingDto save(BookingWithRelationIdsDto bookingWithRelationIdsDto) {
        Booking response = bookingDao.save(reassignment(bookingWithRelationIdsDto));
        return modelMapper.map(response, BookingDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public BookingDto findById(Long id) throws NotFoundException {
        Booking booking = bookingDao.findById(id).orElseThrow(() -> new NotFoundException(id));
        return modelMapper.map(booking, BookingDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookingDto> findAll() {
        List<Booking> bookings = bookingDao.findAll();
        return (List<BookingDto>) mapperUtil.map(bookings, BookingDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookingDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookingDto> findAllByDateBefore(LocalDateTime date) {
        List<Booking> bookings = bookingDao.findAllByDateBefore(date);
        return (List<BookingDto>) mapperUtil.map(bookings, BookingDto.class);
    }

    public Booking reassignment(BookingWithRelationIdsDto bookingWithRelationIdsDto) {
        final Booking booking = modelMapper.map(bookingWithRelationIdsDto, Booking.class);

        User user = userDao.findById(bookingWithRelationIdsDto.getUserId())
                .orElseThrow(() -> new NotFoundException(bookingWithRelationIdsDto.getUserId()));
        booking.setUser(user);

        Attraction attraction = attractionDao.findById(bookingWithRelationIdsDto.getAttractionId())
                .orElseThrow(() -> new NotFoundException(bookingWithRelationIdsDto.getAttractionId()));
        booking.setAttraction(attraction);

        return booking;
    }
}
