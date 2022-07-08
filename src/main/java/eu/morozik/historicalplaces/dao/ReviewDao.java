package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ReviewDao extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    Optional<Review> findFirstByGrade(Long grade);

    Optional<Long> countByGradeEquals(Long grade);

    boolean existsReviewByGrade(Long grade);
}
