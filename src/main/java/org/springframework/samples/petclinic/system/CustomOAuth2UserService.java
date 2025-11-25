package org.springframework.samples.petclinic.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginAuditService loginAuditService;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		String provider = userRequest.getClientRegistration().getRegistrationId(); // google
		String providerId = oAuth2User.getAttribute("sub");
		String email = oAuth2User.getAttribute("email");
		String name = oAuth2User.getAttribute("name");

		User user = userRepository.findByEmail(email)
			.orElseGet(() -> registerNewUser(provider, providerId, email, name));

		if (user.getLanguage() == null) {
			user.setLanguage("en");
			userRepository.save(user);
		}

		loginAuditService.recordLogin(email, LocalDateTime.now());

		return CustomUserPrincipal.create(user, oAuth2User.getAttributes());
	}

	private User registerNewUser(String provider, String providerId, String email, String name) {
		User user = new User();
		user.setProvider(provider);

		user.setProviderid(providerId);
		user.setEmail(email);
		user.setName(name);
		user.setLanguage("en");
		return userRepository.save(user);
	}

}
