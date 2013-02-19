package com.kossel.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kossel.dao.PersonDao;
import com.kossel.model.Person;
import com.kossel.service.impl.BaseManagerMockTestCase;
import com.kossel.service.impl.PersonManagerImpl;

public class PersonManagerImplTest extends BaseManagerMockTestCase{
	
	private PersonManagerImpl manager = null;
    private PersonDao dao = null;
    
    @Before
    public void setup(){
    	dao = context.mock(PersonDao.class);
        manager = new PersonManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }
    
    @Test
    public void testGetPerson(){
    	log.debug("testing get...");
    	
    	final Long id=7L;
    	final Person person = new Person();
    	
    	context.checking(new Expectations() {{
    		one(dao).get(with(equal(id)));
    		will(returnValue(person));
    	}});
    	
    	Person result = manager.get(id);
    	assertSame(person, result);
    }
    
    @Test
    public void testGetPersons(){
    	log.debug("testing getAll...");
    	 
        final List persons = new ArrayList();
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(persons));
        }});
 
        List result = manager.getAll();
 
        assertSame(persons, result);
    }
    
    @Test
    public void testSavePerson() {
        log.debug("testing save...");
 
        final Person person = new Person();
        // enter all required fields
         
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(person)));
        }});
 
        manager.save(person);
    }
 
    @Test
    public void testRemovePerson() {
        log.debug("testing remove...");
 
        final Long id = -11L;
 
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});
 
        manager.remove(id);
    }
}
