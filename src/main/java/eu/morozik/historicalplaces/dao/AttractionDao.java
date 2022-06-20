package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.dao.projection.view.AttractionView;
import eu.morozik.historicalplaces.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface AttractionDao extends JpaRepository<Attraction,Long> {

    @Modifying
    @Query(value = "delete from similar_place where attraction_id = :id or similar_place_id = :id", nativeQuery = true)
    void deleteSimilarPlaces(Long id);

    AttractionView findByName(String name);
}
