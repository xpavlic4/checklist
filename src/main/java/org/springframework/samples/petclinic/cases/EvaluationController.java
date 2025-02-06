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

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 * @author Dave Syer
 * @author Wick Dynex
 */
@Controller
class EvaluationController {

	private final CaseRepository cases;

	public EvaluationController(CaseRepository cases) {
		this.cases = cases;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	/**
	 * Called before each and every @RequestMapping annotated method. 2 goals: - Make sure
	 * we always have fresh data - Since we do not use the session scope, make sure that
	 * Pet object always has an id (Even though id is not part of the form fields)
	 * @param argumentId
	 * @return Pet
	 */
	@ModelAttribute("visit")
	public Evaluation loadPetWithVisit(@PathVariable("caseId") int caseId, @PathVariable("argumentId") int argumentId,
			Map<String, Object> model) {
		Optional<Case> optionalOwner = cases.findById(caseId);
		Case owner = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Case not found with id: " + caseId + ". Please ensure the ID is correct "));

		Argument pet = owner.getArgument(argumentId);
		model.put("pet", pet);
		model.put("owner", owner);

		Evaluation evaluation = new Evaluation();
		pet.addEvaluation(evaluation);
		return evaluation;
	}

	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is
	// called
	@GetMapping("/cases/{caseId}/arguments/{argumentId}/evaluations/new")
	public String initNewVisitForm() {
		return "arguments/createOrUpdateEvaluationsForm";
	}

	// Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is
	// called
	@PostMapping("/cases/{caseId}/arguments/{argumentId}/evaluations/new")
	public String processNewVisitForm(@ModelAttribute Case aCase, @PathVariable int argumentId,
			@Valid Evaluation evaluation, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "arguments/createOrUpdateEvaluationsForm";
		}

		aCase.addVisit(argumentId, evaluation);
		this.cases.save(aCase);
		redirectAttributes.addFlashAttribute("message", "Added.");
		return "redirect:/cases/{ownerId}";
	}

}
