package org.springframework.samples.petclinic.report;

public class CaseReport {
	String premise;
	String predicate;

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
}
