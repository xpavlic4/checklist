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
package org.springframework.samples.petclinic.system;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
class UserController {

	private final UserRepository users;

	public UserController(UserRepository users) {
		this.users = users;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("user")
	public User findOwner(@PathVariable(name = "userId", required = false) Long userId) {
		return userId == null ? new User()
				: this.users.findById(userId)
					.orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId
							+ ". Please ensure the ID is correct " + "and the case exists in the database."));
	}

	@GetMapping("/users/find")
	public String initFindForm() {
		return "users/findUsers";
	}

	/**
	 * Custom handler for displaying an owner.
	 * @param userId the ID of the user to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/users/{userId}")
	public ModelAndView showCase(@PathVariable("userId") long userId) {
		ModelAndView mav = new ModelAndView("users/userDetails");
		Optional<User> optionalCase = this.users.findById(userId);
		User aCase = optionalCase.orElseThrow(() -> new IllegalArgumentException(
				"User not found with id: " + userId + ". Please ensure the ID is correct "));
		mav.addObject(aCase);
		return mav;
	}

	/**
	 * Cases
	 */

	@GetMapping("/users")
	public String processFindForm(@RequestParam(defaultValue = "1") int page, User anUser, BindingResult result,
			Model model) {
		// allow parameterless GET request for /owners to return all records
		if (anUser.getName() == null) {
			anUser.setName(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		Page<User> casesResult = findPaginatedForCasesLastName(page, anUser.getName());
		if (casesResult.isEmpty()) {
			// no owners found
			result.rejectValue("name", "notFound", "not found");
			return "users/findUsers";
		}

		if (casesResult.getTotalElements() == 1) {
			// 1 case found
			anUser = casesResult.iterator().next();
			return "redirect:/users/" + anUser.getId();
		}

		// multiple owners found
		return addPaginationModelCases(page, model, casesResult);
	}

	private String addPaginationModelCases(int page, Model model, Page<User> paginated) {
		List<User> listOwners = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listUsers", listOwners);
		return "users/usersList";
	}

	private Page<User> findPaginatedForCasesLastName(int page, String lastname) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return users.findByNameStartingWith(lastname, pageable);
	}

}
