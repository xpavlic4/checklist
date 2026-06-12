package org.springframework.samples.petclinic.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.cases.Argument;
import org.springframework.samples.petclinic.cases.ArgumentRepository;
import org.springframework.samples.petclinic.cases.Case;
import org.springframework.samples.petclinic.cases.CaseRepository;
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
			ArgumentView child = new ArgumentView(format(rootArgument.getPredicate(), rootArgument.getPremise()));

			Set<Argument> attacks = rootArgument.getAttacks();
			for (Argument attack : attacks) {
				child.addChild(new ArgumentView(format(attack.getPredicate(), attack.getPremise())));
			}
			root.addChild(child);
		}
		return root;
	}

	private String format(String a, String b) {
		return a + because + " " + b;
	}

}
