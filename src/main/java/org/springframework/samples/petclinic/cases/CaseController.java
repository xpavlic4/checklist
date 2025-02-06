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

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class CaseController {

	private static final String VIEWS_CASE_CREATE_OR_UPDATE_FORM = "cases/createOrUpdateCaseForm";

	private final CaseRepository cases;

	public CaseController(CaseRepository clinicService) {
		this.cases = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("case")
	public Case findOwner(@PathVariable(name = "caseId", required = false) Integer caseId) {
		return caseId == null ? new Case()
				: this.cases.findById(caseId)
					.orElseThrow(() -> new IllegalArgumentException("Case not found with id: " + caseId
							+ ". Please ensure the ID is correct " + "and the case exists in the database."));
	}

	@GetMapping("/cases/new")
	public String initCreationForm() {
		return VIEWS_CASE_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/cases/new")
	public String processCreationForm(@Valid Case aCase, BindingResult result, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal OAuth2User oauthUser) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "There was an error in creating the case.");
			return VIEWS_CASE_CREATE_OR_UPDATE_FORM;
		}
		String email = oauthUser.getAttribute("email");
		aCase.setEmail(email);
		this.cases.save(aCase);
		redirectAttributes.addFlashAttribute("message", "Created.");
		return "redirect:/cases/" + aCase.getId();
	}

	@GetMapping("/cases/find")
	public String initFindForm() {
		return "cases/findCases";
	}

	@GetMapping("/cases/{caseId}/edit")
	public String initUpdateOwnerForm() {
		return VIEWS_CASE_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/cases/{caseId}/edit")
	public String processUpdateOwnerForm(@Valid Case aCase, BindingResult result, @PathVariable("caseId") int caseId,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "There was an error in updating the case.");
			return VIEWS_CASE_CREATE_OR_UPDATE_FORM;
		}

		if (aCase.getId() != caseId) {
			result.rejectValue("id", "mismatch", "The case ID in the form does not match the URL.");
			redirectAttributes.addFlashAttribute("error", "Owner ID mismatch. Please try again.");
			return "redirect:/cases/{caseId}/edit";
		}

		aCase.setId(caseId);
		this.cases.save(aCase);
		redirectAttributes.addFlashAttribute("message", "Case Values Updated");
		return "redirect:/cases/{caseId}";
	}

	/**
	 * Custom handler for displaying an owner.
	 * @param caseId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/cases/{caseId}")
	public ModelAndView showCase(@PathVariable("caseId") int caseId) {
		ModelAndView mav = new ModelAndView("cases/caseDetails");
		Optional<Case> optionalCase = this.cases.findById(caseId);
		Case aCase = optionalCase.orElseThrow(() -> new IllegalArgumentException(
				"Case not found with id: " + caseId + ". Please ensure the ID is correct "));
		mav.addObject(aCase);
		return mav;
	}

	/**
	 * Cases
	 */

	@GetMapping("/cases")
	public String processFindForm(@RequestParam(defaultValue = "1") int page, Case aCase, BindingResult result,
			Model model) {
		// allow parameterless GET request for /owners to return all records
		if (aCase.getName() == null) {
			aCase.setName(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		Page<Case> casesResult = findPaginatedForCasesLastName(page, aCase.getName());
		if (casesResult.isEmpty()) {
			// no owners found
			result.rejectValue("name", "notFound", "not found");
			return "cases/findCases";
		}

		if (casesResult.getTotalElements() == 1) {
			// 1 case found
			aCase = casesResult.iterator().next();
			return "redirect:/cases/" + aCase.getId();
		}

		// multiple owners found
		return addPaginationModelCases(page, model, casesResult);
	}

	private String addPaginationModelCases(int page, Model model, Page<Case> paginated) {
		List<Case> listOwners = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listCases", listOwners);
		return "cases/casesList";
	}

	private Page<Case> findPaginatedForCasesLastName(int page, String lastname) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return cases.findByNameStartingWith(lastname, pageable);
	}

}
