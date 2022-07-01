package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByNameRole(String nameRole);
}
