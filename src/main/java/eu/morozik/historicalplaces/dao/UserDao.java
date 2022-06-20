package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.firstName = :name")
    List<User> findUserByName(String name);

    @EntityGraph(attributePaths = {"credential"})
    List<User> findBySurname(String surname);
}
