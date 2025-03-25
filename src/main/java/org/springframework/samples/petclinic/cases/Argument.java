/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.cases;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.samples.petclinic.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

/**
 * Simple business object representing an argument.
 */
@Entity
@Table(name = "arguments")
public class Argument extends BaseEntity {

	@Column(name = "premise")
	private String premise;

	@Column(name = "predicate")
	private String predicate;

	@Column(name = "warrant")
	private String warrant;

	@Column(name = "ordering")
	private Integer ordering;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private ArgumentType type;

	@ManyToOne
	@JoinColumn(name = "parent_id", nullable = true)
	private Argument parent;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	@OrderBy("id ASC")
	private final Set<Argument> attacks = new LinkedHashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "argument_id")
	// @OrderBy("ordering ASC")
	private final Set<Evaluation> evaluations = new LinkedHashSet<>();

	@ManyToOne
	@JoinColumn(name = "case_id", nullable = true)
	private Case aCase;

	public ArgumentType getType() {
		return this.type;
	}

	public void setType(ArgumentType type) {
		this.type = type;
	}

	public void addEvaluation(Evaluation evaluation) {
		getEvaluations().add(evaluation);
	}

	public String getPremisa() {
		return premise;
	}

	public void setPremisa(String premisa) {
		this.premise = premisa;
	}

	public Integer getOrdering() {
		return ordering;
	}

	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public Set<Evaluation> getEvaluations() {
		return evaluations;
	}

	public String getPremise() {
		return premise;
	}

	public void setPremise(String premise) {
		this.premise = premise;
	}

	public void setCase(Case aCase) {
		this.aCase = aCase;
	}

	public Case getCase() {
		return aCase;
	}

	public Argument getParent() {
		return parent;
	}

	public void setParent(Argument parent) {
		this.parent = parent;
	}

	public String getWarrant() {
		return warrant;
	}

	public void setWarrant(String warrant) {
		this.warrant = warrant;
	}

	public Set<Argument> getAttacks() {
		return attacks;
	}

	@Override
	public String toString() {
		return "Argument{" + "premise='" + premise + '\'' + ", predicate='" + predicate + '\'' + ", ordering="
				+ ordering + ", warrant=" + warrant + ", type=" + type + ", evaluations=" + evaluations + "} "
				+ super.toString();
	}

}
