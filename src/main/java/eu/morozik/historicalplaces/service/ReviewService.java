package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dao.ReviewDao;
import eu.morozik.historicalplaces.dto.CountGradeDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.exception.NotFoundGradeException;
import eu.morozik.historicalplaces.model.Attraction;

import java.util.List;

public interface ReviewService {

    ReviewDto save(ReviewWithRelationIdsDto reviewWithRelationIdsDto);

    ReviewDto findById(Long id);

    List<ReviewDto> findAll(int page,int size, String name);

    void deleteById(Long id);

    ReviewDto findFirstByGrade(Long grade);

    CountGradeDto countByGradeEquals(Long grade);

    boolean existsReviewByGrade(Long grade);
}
