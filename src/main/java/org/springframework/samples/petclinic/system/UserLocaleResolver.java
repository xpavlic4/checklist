package org.springframework.samples.petclinic.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class UserLocaleResolver extends SessionLocaleResolver {

	private final UserRepository userRepository;

	public UserLocaleResolver(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof CustomUserPrincipal) {
			CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
			User user = principal.getUser();
			if (user != null && user.getLanguage() != null) {
				return Locale.forLanguageTag(user.getLanguage());
			}
		}
		return super.resolveLocale(request);
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		super.setLocale(request, response, locale);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof CustomUserPrincipal) {
			CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
			User user = principal.getUser();
			if (user != null && locale != null) {
				user.setLanguage(locale.toLanguageTag());
				userRepository.save(user);
			}
		}
	}

}
