package com.kossel.service;

import java.util.List;

import com.kossel.model.Person;

public interface PersonManager extends GenericManager<Person, Long> {
    List<Person> findByLastName(String lastName);
    List<Person> getByDept();

}


