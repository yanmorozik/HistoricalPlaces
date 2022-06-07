package eu.morozik.historicalplaces.dao.api;

import eu.morozik.historicalplaces.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionDao extends JpaRepository<Attraction,Long> {

}
