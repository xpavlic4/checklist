package org.springframework.samples.petclinic.export;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

// This annotation hides the "children" field in the JSON if a node has no children (leaves)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ArgumentView {

	private String name;

	@JsonInclude(JsonInclude.Include.ALWAYS)
	private boolean justified;

	private List<ArgumentView> children;

	// Constructor for quick instantiation
	public ArgumentView(String name) {
		this.name = name;
		this.children = new ArrayList<>();
	}

	// Helper method to easily add a child node
	public ArgumentView addChild(ArgumentView child) {
		this.children.add(child);
		return child; // Returns the child to allow chaining
	}

	// Getters and Setters (Required for Jackson Serialization)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ArgumentView> getChildren() {
		return children;
	}

	public void setChildren(List<ArgumentView> children) {
		this.children = children;
	}

	public void setJustified(boolean justified) {
		this.justified = justified;
	}

	public boolean getJustified() {
		return justified;
	}

}
