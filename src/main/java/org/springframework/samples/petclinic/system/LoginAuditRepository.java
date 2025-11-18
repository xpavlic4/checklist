package org.springframework.samples.petclinic.system;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAuditRepository extends JpaRepository<LoginAudit, Long> {

}
