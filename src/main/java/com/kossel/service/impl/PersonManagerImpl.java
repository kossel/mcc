package com.kossel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kossel.dao.PersonDao;
import com.kossel.model.Person;
import com.kossel.service.PersonManager;

@Service("personManager")
public class PersonManagerImpl extends GenericManagerImpl<Person, Long> implements PersonManager{
	
	PersonDao personDao;
	
	@Autowired
	public PersonManagerImpl(PersonDao personDao) {
        super(personDao);
        this.personDao = personDao;
    }
 
    public List<Person> findByLastName(String lastName) {
        return personDao.findByLastName(lastName);
    }
}
