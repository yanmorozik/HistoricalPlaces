package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.dao.projection.view.AttractionView;
import eu.morozik.historicalplaces.model.Attraction;
import eu.morozik.historicalplaces.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttractionDao extends JpaRepository<Attraction, Long>, JpaSpecificationExecutor<Attraction> {

    @Modifying
    @Query(value = "delete from similar_place where attraction_id = :id or similar_place_id = :id", nativeQuery = true)
    void deleteSimilarPlaces(Long id);

    List<Attraction> findByName(String name);

//    AttractionView findByName(String name);
}
