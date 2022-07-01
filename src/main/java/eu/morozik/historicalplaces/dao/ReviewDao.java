package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewDao extends JpaRepository<Review, Long> {

    Optional<Review> findFirstByGrade(Long grade);

    Optional<Long> countByGradeEquals(Long grade);

    boolean existsReviewByGrade(Long grade);
}
