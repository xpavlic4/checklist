package org.springframework.samples.petclinic.system;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audits")
public class LoginAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audits_seq")
	@SequenceGenerator(name = "audits_seq", sequenceName = "audits_seq", allocationSize = 1)
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
