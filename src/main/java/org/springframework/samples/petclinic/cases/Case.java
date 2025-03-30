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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.util.Assert;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

/**
 * Simple JavaBean domain object representing a case.
 *
 * @author Radim Pavlicek
 */
@Entity
@Table(name = "cases")
public class Case extends NamedEntity {

	@Column(name = "email")
	// @NotBlank
	// @Pattern(regexp = "\\d{10}", message = "Telephone must be a 10-digit number")
	private String email;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "case_id")
	@OrderBy("ordering")
	private final List<Argument> arguments = new ArrayList<>();

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String telephone) {
		this.email = telephone;
	}

	public List<Argument> getArguments() {
		return this.arguments;
	}

	public List<Argument> getRootAruments() {
		return this.arguments.stream().filter(argument -> argument.getParentId() == null).collect(Collectors.toList());
	}

	public void addArgument(Argument argument) {
		if (argument.isNew()) {
			getArguments().add(argument);
		}
		else {
			// update
			Argument del = getArgument(argument.getId());
			getArguments().remove(del);
			getArguments().add(argument);
		}
	}

	/**
	 * Return the Pet with the given name, or null if none found for this Owner.
	 * @param name to test
	 * @return the Pet with the given name, or null if no such Pet exists for this Owner
	 */
	// public Argument getArgument(String name) {
	// return getArgument(name, false);
	// }

	/**
	 * Return the Pet with the given id, or null if none found for this Owner.
	 * @param id to test
	 * @return the Pet with the given id, or null if no such Pet exists for this Owner
	 */
	public Argument getArgument(Integer id) {
		for (Argument argument : getArguments()) {
			if (!argument.isNew()) {
				Integer compId = argument.getId();
				if (compId.equals(id)) {
					return argument;
				}
			}
		}
		return null;
	}

	/**
	 * Return the Pet with the given name, or null if none found for this Owner.
	 * @param name to test
	 * @param ignoreNew whether to ignore new pets (pets that are not saved yet)
	 * @return the Pet with the given name, or null if no such Pet exists for this Owner
	 */
	// public Argument getArgument(String name, boolean ignoreNew) {
	// for (Argument argument : getArguments()) {
	// String compName = argument.getName();
	// if (compName != null && compName.equalsIgnoreCase(name)) {
	// if (!ignoreNew || !argument.isNew()) {
	// return argument;
	// }
	// }
	// }
	// return null;
	// }

	@Override
	public String toString() {
		return new ToStringCreator(this).append("id", this.getId())
			.append("new", this.isNew())
			.append("name", this.getName())
			.toString();
	}

	/**
	 * Adds the given {@link Evaluation} to the {@link Argument} with the given
	 * identifier.
	 * @param argumentId the identifier of the {@link Argument}, must not be
	 * {@literal null}.
	 * @param evaluation the visit to add, must not be {@literal null}.
	 */
	public void addEvaluation(Integer argumentId, Evaluation evaluation) {

		Assert.notNull(argumentId, "Argument identifier must not be null!");
		Assert.notNull(evaluation, "Evaluation must not be null!");

		Argument argument = getArgument(argumentId);

		Assert.notNull(argument, "Invalid Pet identifier!");

		argument.addEvaluation(evaluation);
	}

}
