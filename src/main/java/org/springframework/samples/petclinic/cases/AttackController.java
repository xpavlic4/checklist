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

import jakarta.validation.Valid;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.Visit;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Optional;

@Controller
class AttackController {

	private final CaseRepository cases;
	private final ArgumentRepository arguments;

	public AttackController(CaseRepository cases, ArgumentRepository arguments) {
		this.cases = cases;
		this.arguments = arguments;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	/**
	 * Called before each and every @RequestMapping annotated method. 2 goals: - Make sure
	 * we always have fresh data - Since we do not use the session scope, make sure that
	 * Pet object always has an id (Even though id is not part of the form fields)
	 * @param argId
	 * @return Pet
	 */
	@ModelAttribute("attack")
	public Argument loadPetWithVisit(@PathVariable("caseId") int caseId, @PathVariable("argumentId") int argId,
                                  Map<String, Object> model) {
		Optional<Case> optionalOwner = cases.findById(caseId);
		Case aCase = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Case not found with id: " + caseId + ". Please ensure the ID is correct "));

		Argument argument = aCase.getArgument(argId);
		model.put("argument", argument);
		model.put("case", aCase);

		Argument attack = new Argument();
//		argument.addAttack(attack);
		return attack;
	}

//	@ModelAttribute("case")
//	public Case findCase(@PathVariable("caseId") int caseId) {
//		Optional<Case> optionalCase = this.cases.findById(caseId);
//		Case aCase = optionalCase.orElseThrow(() -> new IllegalArgumentException(
//			"Case not found with id: " + caseId + ". Please ensure the ID is correct "));
//		return aCase;
//	}
//
//	@ModelAttribute("argument")
//	public Argument findPet(@PathVariable("caseId") int caseId,
//							@PathVariable(name = "argumentId") Integer argumentId) {
//
//		Optional<Case> optionalCase = this.cases.findById(caseId);
//		Case aCase = optionalCase.orElseThrow(() -> new IllegalArgumentException(
//			"Case not found with id: " + caseId + ". Please ensure the ID is correct "));
//		return aCase.getArgument(argumentId);
//	}

	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is
	// called
	@GetMapping("/cases/{caseId}/arguments/{argumentId}/rebutting/new")
	public String initNewVisitForm() {
		return "arguments/createOrUpdateAttackForm";
	}

	@GetMapping("/cases/{caseId}/arguments/{argumentId}/undercutting/new")
	public String initNewUndercuttingForm() {
		return "arguments/createOrUpdateAttackForm";
	}

	@GetMapping("/cases/{caseId}/arguments/{argumentId}/undermining/new")
	public String initNewUnderminingForm() {
		return "arguments/createOrUpdateAttackForm";
	}


	// Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is
	// called
	@PostMapping("/cases/{caseId}/arguments/{argumentId}/rebutting/new")
	public String processNewVisitForm(@PathVariable Integer caseId, @PathVariable(name = "argumentId") Integer argumentId, @Valid Argument argument,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "arguments/createOrUpdateAttackForm";
		}
		argument.setId(null);

		Optional<Case> optionalOwner = cases.findById(caseId);
		Case aCase = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
			"Case not found with id: " + caseId + ". Please ensure the ID is correct "));
		aCase.addAttack(argumentId, argument);

//		this.cases.save(aCase);
		this.arguments.save(argument);
		redirectAttributes.addFlashAttribute("message", "Added.");
		return "redirect:/cases/{caseId}";
	}

}
