package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDao extends JpaRepository<Review,Long> {
}
