package com.example.project.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.project.model.Name;

@Stateless
public class NameService {

	@PersistenceContext
	private EntityManager entityManager;

	public void create(Name name) {
		entityManager.persist(name);
	}

	public List<Name> getNames() {
		return entityManager.createQuery("FROM Name n", Name.class).getResultList();
	}

	public void deleteName(Name name) {
		Name n = entityManager.find(Name.class, name.getId());

		entityManager.remove(n);
	}

	public void updateName(Name name) {
		Name n = entityManager.find(Name.class, name.getId());
		n.setName(name.getName());
		n.setSurname(name.getSurname());

		entityManager.persist(n);
	}

}
