package org.springframework.samples.petclinic.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginAuditService {

	@Autowired
	private LoginAuditRepository repo;

	public void recordLogin(String email, LocalDateTime time) {
		LoginAudit audit = new LoginAudit();
		audit.setEmail(email);
		audit.setLoginTime(time);
		repo.save(audit);
	}

	public Optional<LocalDateTime> getLastLoginTime(String email) {
		return repo.findTopByEmailOrderByLoginTimeDesc(email).map(LoginAudit::getLoginTime);
	}

}
