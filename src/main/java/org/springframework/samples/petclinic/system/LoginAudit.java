package org.springframework.samples.petclinic.system;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "audits")
public class LoginAudit {

	@Id
	@GeneratedValue
	private Long id;

	private String email;

	private LocalDateTime loginTime;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

}
