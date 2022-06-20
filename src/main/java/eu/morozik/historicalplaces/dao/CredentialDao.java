package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CredentialDao extends JpaRepository<Credential,Long> {
}
