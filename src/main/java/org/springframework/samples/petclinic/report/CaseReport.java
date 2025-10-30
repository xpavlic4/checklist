package org.springframework.samples.petclinic.report;

import org.springframework.samples.petclinic.cases.VerificationStatus;

public class CaseReport {

	String premise;

	String predicate;

	private VerificationStatus verificationStatus;

	public String getPredicate() {
		return predicate;
	}

	public String getPremise() {
		return premise;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public void setPremise(String premise) {
		this.premise = premise;
	}

	public void setVerificationStatus(VerificationStatus verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public VerificationStatus getVerificationStatus() {
		return verificationStatus;
	}

}
