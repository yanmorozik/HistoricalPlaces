package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.ReviewDao;
import eu.morozik.historicalplaces.dao.UserDao;
import eu.morozik.historicalplaces.dto.CountGradeDto;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.exception.NotFoundGradeException;
import eu.morozik.historicalplaces.model.Attraction;
import eu.morozik.historicalplaces.model.Review;
import eu.morozik.historicalplaces.model.User;
import eu.morozik.historicalplaces.service.ReviewService;
import eu.morozik.historicalplaces.specification.Filter;
import eu.morozik.historicalplaces.specification.QueryOperator;
import eu.morozik.historicalplaces.specification.SpecificationCreator;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;
    private final UserDao userDao;
    private final AttractionDao attractionDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    private final SpecificationCreator<Review> creator;

    @Transactional
    @Override
    public ReviewDto save(ReviewWithRelationIdsDto reviewWithRelationIdsDto) {
        Review response = reviewDao.save(reassignment(reviewWithRelationIdsDto));
        return modelMapper.map(response, ReviewDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public ReviewDto findById(Long id) {
        Review review = reviewDao.findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(id);
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        return modelMapper.map(review, ReviewDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReviewDto> findAll(int page, int size, String name) {
        Pageable pages = PageRequest.of(page, size, Sort.by(name));
        Page<Review> reviews = reviewDao.findAll(pages);
        return (List<ReviewDto>) mapperUtil.map(reviews.getContent(), ReviewDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReviewDto> findAll(SearchWithThreeFiltersDto searchDto) {
        List<Filter> filters = checkFilters(searchDto);
        if (filters.size() > 0) {
            return (List<ReviewDto>) mapperUtil.map(reviewDao.findAll(getSpecificationFromFilters(filters)), ReviewDto.class);
        } else {
            return (List<ReviewDto>) mapperUtil.map(reviewDao.findAll(), ReviewDto.class);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReviewDto> findAll() {
        List<Review> reviews = reviewDao.findAll();
        return (List<ReviewDto>) mapperUtil.map(reviews, ReviewDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        reviewDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ReviewDto findFirstByGrade(Long grade) {
        Review review = reviewDao.findFirstByGrade(grade)
                .orElseThrow(() -> {
                    NotFoundGradeException notFoundException = new NotFoundGradeException(grade);
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        return modelMapper.map(review, ReviewDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public CountGradeDto countByGradeEquals(Long grade) {
        Long count = reviewDao.countByGradeEquals(grade)
                .orElseThrow(() -> {
                    NotFoundGradeException notFoundException = new NotFoundGradeException(grade);
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        return CountGradeDto.builder().countGrade(count).build();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsReviewByGrade(Long grade) {
        return reviewDao.existsReviewByGrade(grade);
    }

    public Review reassignment(ReviewWithRelationIdsDto reviewWithRelationIdsDto) {
        final Review review = modelMapper.map(reviewWithRelationIdsDto, Review.class);

        User user = userDao.findById(reviewWithRelationIdsDto.getUserId())
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(reviewWithRelationIdsDto
                            .getUserId());
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        review.setUser(user);

        Attraction attraction = attractionDao.findById(reviewWithRelationIdsDto.getAttractionId())
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(reviewWithRelationIdsDto
                            .getAttractionId());
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        review.setAttraction(attraction);

        return review;
    }

    private Specification<Review> getSpecificationFromFilters(List<Filter> filter) {
        Specification<Review> specification = where(creator.createSpecification(filter.remove(0)));
        for (Filter input : filter) {
            specification = specification.and(creator.createSpecification(input));
        }
        return specification;
    }

    private List<Filter> checkFilters(SearchWithThreeFiltersDto searchDto) {
        List<Filter> filters = new ArrayList<>();

        checkFilter(searchDto.getFirstField(), searchDto.getFirstOperator(), searchDto.getFirstValue(),filters);
        checkFilter(searchDto.getSecondField(), searchDto.getSecondOperator(), searchDto.getSecondValue(),filters);
        checkFilter(searchDto.getThirdField(), searchDto.getThirdOperator(), searchDto.getThirdValue(),filters);

        return filters;
    }

    private void checkFilter(String field, QueryOperator operator, String value, List<Filter> filters) {
        if (!(creator.isEmptyFilter(field, operator, value))) {
            Filter filter = Filter.builder()
                    .field(field)
                    .operator(operator)
                    .value(value)
                    .build();
            filters.add(filter);
        }
    }

}
