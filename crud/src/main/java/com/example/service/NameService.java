package com.example.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.example.model.Name;

@ApplicationScoped
public class NameService {

	@Inject
	private EntityManager entityManager;

	@Transactional
	public void create(Name name) {
		entityManager.persist(name);
	}

	public List<Name> getNames() {
		return entityManager.createQuery("FROM Name n", Name.class).getResultList();
	}

	@Transactional
	public void deleteName(Name name) {
		Name n = entityManager.find(Name.class, name.getId());

		entityManager.remove(n);
	}

	@Transactional
	public void updateName(Name name) {
		Name n = entityManager.find(Name.class, name.getId());
		n.setName(name.getName());
		n.setSurname(name.getSurname());

		entityManager.persist(n);
	}

}
