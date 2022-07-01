package eu.morozik.historicalplaces.dao;

import eu.morozik.historicalplaces.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialDao extends JpaRepository<Credential, Long> {
}
