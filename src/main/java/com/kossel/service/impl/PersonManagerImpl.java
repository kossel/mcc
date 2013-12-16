package com.kossel.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.kossel.service.OptionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kossel.dao.PersonDao;
import com.kossel.model.Person;
import com.kossel.service.PersonManager;
import org.springframework.transaction.annotation.Transactional;

@Service("personManager")
public class PersonManagerImpl extends GenericManagerImpl<Person, Long> implements PersonManager{
	
	PersonDao personDao;
    OptionManager optionManager;
	
	@Autowired
	public PersonManagerImpl(PersonDao personDao) {
        super(personDao);
        this.personDao = personDao;
    }

    @Autowired
    public void setOptionManager(OptionManager optionManager){
        this.optionManager=optionManager;
    }

    public List<Person> findByLastName(String lastName) {
        return personDao.findByLastName(lastName);
    }

    public List<Person> getByDept(){
        return personDao.getByDept();
    }

    @Transactional
    public Person save(String name, Person person){
        java.util.Date date= new java.util.Date();
        optionManager.updateLastEditInfo(name,date);
        return super.save(person);

    }
}
