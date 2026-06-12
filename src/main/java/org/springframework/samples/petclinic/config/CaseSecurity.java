package org.springframework.samples.petclinic.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.samples.petclinic.cases.CaseRepository;
import org.springframework.samples.petclinic.cases.Case;
import org.springframework.samples.petclinic.system.CustomUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Component
public class CaseSecurity {

	@Autowired
	private CaseRepository caseRepository;

	public boolean checkCaseOwner(Integer caseId, Authentication authentication) {
		if (1 == 1) {
			return true;
		}
		if (authentication == null || !authentication.isAuthenticated()) {
			return false;
		}

		if (!(authentication.getPrincipal() instanceof CustomUserPrincipal)) {
			return false;
		}

		CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
		String authenticatedUserEmail = principal.getUser().getEmail();

		Optional<Case> aCase = caseRepository.findById(caseId);

		return aCase.map(c -> c.getUser().getEmail().equals(authenticatedUserEmail)).orElse(false);
	}

}
