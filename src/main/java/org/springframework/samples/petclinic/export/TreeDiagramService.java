package org.springframework.samples.petclinic.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.cases.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TreeDiagramService {

	@Autowired
	private ArgumentRepository argumentRepository;

	@Autowired
	private CaseRepository caseRepository;

	private String because = ", protože ";

	public ArgumentView generateTreeForCase(int caseId) {
		Optional<Case> byId = caseRepository.findById(caseId);
		byId.orElseThrow(() -> new IllegalArgumentException("Case not found with id: " + caseId));
		Case aCase = byId.get();

		ArgumentView root = new ArgumentView(aCase.getName());

		// Fetch rootAruments from DB
		List<Argument> rootAruments = argumentRepository.findRootAruments(caseId);

		for (Argument rootArgument : rootAruments) {
			addArgument(root, rootArgument);
		}
		return root;
	}

	void addArgument(ArgumentView parent, Argument argument) {
		String name = format(argument.getPredicate(), argument.getPremise());
		boolean isJustified = argument.getEvaluations().stream()
			.anyMatch(e -> VerificationStatus.JUSTIFIED.equals(e.getVerification_status()));
		ArgumentView child = new ArgumentView(name);
		child.setJustified(isJustified);
		Set<Argument> attacks = argument.getAttacks();
		for (Argument attack : attacks) {
			addArgument(child, attack);
		}
		parent.addChild(child);
	}

	private String format(String a, String b) {
		return a + because + " " + b;
	}

}
