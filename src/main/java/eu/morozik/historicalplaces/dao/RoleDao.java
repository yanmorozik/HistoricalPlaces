package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleDao extends JpaRepository<Role,Long> {
}
