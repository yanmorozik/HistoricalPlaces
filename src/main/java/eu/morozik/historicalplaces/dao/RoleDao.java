package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    Role findByNameRole(String nameRole);
}
