package com.example.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.project.model.Name;
import com.example.project.service.NameService;

@Named
@ViewScoped
public class Crud implements Serializable {

	private static final long serialVersionUID = -1180839495589038195L;

	@Inject
	private NameService nameService;

	private Name name;

	private String inputName;
	private String inputSurname;

	private List<Name> names;

	private String searchString;

	@PostConstruct
	public void init() {
		setNames(nameService.getNames());
	}

	public Name getName() {
		return name;
	}

	public Name getName(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("no id provided");
		}
		for (Name name : names) {
			if (id.equals(name.getId())) {
				return name;
			}
		}
		return null;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public List<Name> getNames() {
		return names;
	}

	public void setNames(List<Name> names) {
		this.names = names;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public String getInputSurname() {
		return inputSurname;
	}

	public void setInputSurname(String inputSurname) {
		this.inputSurname = inputSurname;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public void create() {
		Name name = new Name();
		name.setName(this.inputName);
		name.setSurname(this.inputSurname);
		nameService.create(name);

		loadNames();
		clearInput();
	}

	public void update() {
		this.name.setName(inputName);
		this.name.setSurname(inputSurname);
		nameService.updateName(this.name);
		loadNames();
		clearInput();
	}

	public void delete() {
		nameService.deleteName(this.name);
		loadNames();
		clearInput();
	}

	public void search() {
		List<Name> names = nameService.getNames();

		List<Name> filteredName = new ArrayList<>();

		for (Name name : names) {
			if (name.getSurname().contains(this.searchString)) {
				filteredName.add(name);
			}
		}

		setNames(filteredName);
	}

	private void loadNames() {
		setNames(nameService.getNames());
	}

	private void clearInput() {
		this.inputName = null;
		this.inputSurname = null;
	}

	public void listBoxChange() {
		this.inputName = name.getName();
		this.inputSurname = name.getSurname();
	}

}
