package eu.morozik.historicalplaces.service;

import eu.morozik.historicalplaces.dto.CountGradeDto;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewWithRelationIdsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    ReviewDto save(ReviewWithRelationIdsDto reviewWithRelationIdsDto);

    ReviewDto findById(Long id);

    Page<ReviewDto> findAll(Pageable pageable);

    List<ReviewDto> findAll(SearchWithThreeFiltersDto searchDto);

    void deleteById(Long id);

    ReviewDto findFirstByGrade(Long grade);

    CountGradeDto countByGradeEquals(Long grade);

    boolean existsReviewByGrade(Long grade);
}
