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

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.samples.petclinic.system.CustomUserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
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
	public Argument loadPetWithVisit(@AuthenticationPrincipal CustomUserPrincipal principal,
			@PathVariable("caseId") int caseId, @PathVariable("argumentId") int argId, Map<String, Object> model) {
		Optional<Case> optionalOwner = cases.findById(caseId);
		Case aCase = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Case not found with id: " + caseId + ". Please ensure the ID is correct "));

		// Argument argument = aCase.getArgument(argId);
		Argument argument = arguments.findById(argId)
			.orElseThrow(() -> new IllegalArgumentException(
					"Argument not found with id: " + argId + ". Please ensure the ID is correct "));
		model.put("argument", argument);
		model.put("case", aCase);

		Argument attack = new Argument();
		attack.setUser(principal.getUser());
		// argument.addAttack(attack);
		return attack;
	}

	// @ModelAttribute("case")
	// public Case findCase(@PathVariable("caseId") int caseId) {
	// Optional<Case> optionalCase = this.cases.findById(caseId);
	// Case aCase = optionalCase.orElseThrow(() -> new IllegalArgumentException(
	// "Case not found with id: " + caseId + ". Please ensure the ID is correct "));
	// return aCase;
	// }
	//
	// @ModelAttribute("argument")
	// public Argument findPet(@PathVariable("caseId") int caseId,
	// @PathVariable(name = "argumentId") Integer argumentId) {
	//
	// Optional<Case> optionalCase = this.cases.findById(caseId);
	// Case aCase = optionalCase.orElseThrow(() -> new IllegalArgumentException(
	// "Case not found with id: " + caseId + ". Please ensure the ID is correct "));
	// return aCase.getArgument(argumentId);
	// }

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
	@PostMapping({ "/cases/{caseId}/arguments/{argumentId}/rebutting/new",
			"/cases/{caseId}/arguments/{argumentId}/undercutting/new",
			"/cases/{caseId}/arguments/{argumentId}/undermining/new" })
	@Transactional
	public String processNewVisitForm(@AuthenticationPrincipal CustomUserPrincipal principal,
			@PathVariable Integer caseId, @PathVariable(name = "argumentId") Integer argumentId, @Valid Argument attack,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "arguments/createOrUpdateAttackForm";
		}
		Argument att = new Argument();

		Optional<Case> optionalOwner = cases.findById(caseId);
		Case aCase = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Case not found with id: " + caseId + ". Please ensure the ID is correct "));

		Argument parentArg = arguments.findById(argumentId)
			.orElseThrow(() -> new IllegalArgumentException("Argument not found with id: " + argumentId));

		att.setCase(aCase);
		att.setParentId(parentArg.getId());
		att.setPredicate(attack.getPredicate());
		att.setPremise(attack.getPremise());
		att.setWarrant(attack.getWarrant());
		att.setUser(principal.getUser());
		// attack.setCase(aCase);
		// aCase.addArgument(attack);
		// this.cases.save(aCase);
		this.arguments.saveAndFlush(att);
		redirectAttributes.addFlashAttribute("message", "Added.");
		return "redirect:/cases/{caseId}";
	}

}
