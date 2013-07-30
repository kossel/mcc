package com.kossel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.kossel.dao.PetDao;
import com.kossel.model.Pet;
import com.kossel.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class PetManagerImplTest extends BaseManagerMockTestCase {
    private PetManagerImpl manager = null;
    private PetDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(PetDao.class);
        manager = new PetManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetPet() {
        log.debug("testing get...");

        final Long id = 7L;
        final Pet pet = new Pet();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(pet));
        }});

        Pet result = manager.get(id);
        assertSame(pet, result);
    }

    @Test
    public void testGetPets() {
        log.debug("testing getAll...");

        final List pets = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(pets));
        }});

        List result = manager.getAll();
        assertSame(pets, result);
    }

    @Test
    public void testSavePet() {
        log.debug("testing save...");

        final Pet pet = new Pet();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(pet)));
        }});

        manager.save(pet);
    }

    @Test
    public void testRemovePet() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}