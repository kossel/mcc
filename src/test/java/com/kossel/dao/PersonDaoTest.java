package com.kossel.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.kossel.model.Person;

public class PersonDaoTest extends BaseDaoTestCase {

	@Autowired
	private PersonDao personDao;
	
	@Test
	public void testFindPersonByLastName() throws Exception{
		List<Person> people = personDao.findByLastName("Raible");
		assertTrue(people.size()>0);
	}
	
	@Test(expected = DataAccessException.class)
	public void testAddAndRemove()throws Exception {
		Person person = new Person();
		person.setFirstName("Country");
		person.setLastName("Bry");
		
		person = personDao.save(person);
		flush();
		
		person = personDao.get(person.getId());
		
		assertEquals("Country", person.getFirstName());
	    assertNotNull(person.getId());
	    
	    log.debug("removing person...");
	 
	    personDao.remove(person.getId());
	    flush();
	    personDao.get(person.getId());
	}
}
