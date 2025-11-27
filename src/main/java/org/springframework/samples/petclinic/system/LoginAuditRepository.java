package org.springframework.samples.petclinic.system;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginAuditRepository extends JpaRepository<LoginAudit, Long> {

	Optional<LoginAudit> findTopByEmailOrderByLoginTimeDesc(String email);

}
