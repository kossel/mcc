package com.kossel.dao;

import java.util.List;

import com.kossel.model.Person;

public interface PersonDao extends GenericDao<Person, Long> {
	public List<Person> findByLastName(String lastName);
}
