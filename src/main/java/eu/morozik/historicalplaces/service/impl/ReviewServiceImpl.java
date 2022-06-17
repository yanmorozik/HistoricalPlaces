package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.CountryDao;
import eu.morozik.historicalplaces.dao.ReviewDao;
import eu.morozik.historicalplaces.dao.UserDao;
import eu.morozik.historicalplaces.dto.CountryDto;
import eu.morozik.historicalplaces.dto.bookingdto.BookingWithRelationIdsDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Attraction;
import eu.morozik.historicalplaces.model.Booking;
import eu.morozik.historicalplaces.model.Country;
import eu.morozik.historicalplaces.model.Review;
import eu.morozik.historicalplaces.model.User;
import eu.morozik.historicalplaces.service.ReviewService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.activation.ActivationDataFlavor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;
    private final UserDao userDao;
    private final AttractionDao attractionDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    @Override
    public ReviewDto save(ReviewWithRelationIdsDto reviewWithRelationIdsDto) {
        Review response = reviewDao.save(reassignment(reviewWithRelationIdsDto));
        return modelMapper.map(response, ReviewDto.class);
    }

    @Override
    public ReviewDto findById(Long id) throws NotFoundException {
        Review review = reviewDao.findById(id).orElseThrow(() -> new NotFoundException(id));
        return modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> findAll() {
        List<Review> reviews = reviewDao.findAll();
        return (List<ReviewDto>) mapperUtil.map(reviews,ReviewDto.class);
    }

    @Override
    public void deleteById(Long id) {
        reviewDao.deleteById(id);
    }

    public Review reassignment(ReviewWithRelationIdsDto reviewWithRelationIdsDto) {
        final Review review = modelMapper.map(reviewWithRelationIdsDto, Review.class);

        User user = userDao.findById(reviewWithRelationIdsDto.getUserId())
                .orElseThrow(() -> new NotFoundException(reviewWithRelationIdsDto.getUserId()));
        review.setUser(user);

        Attraction attraction = attractionDao.findById(reviewWithRelationIdsDto.getAttractionId())
                .orElseThrow(() -> new NotFoundException(reviewWithRelationIdsDto.getAttractionId()));
        review.setAttraction(attraction);

        return review;
    }
}
