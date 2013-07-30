package com.kossel.dao;

import com.kossel.dao.BaseDaoTestCase;
import com.kossel.model.Pet;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class PetDaoTest extends BaseDaoTestCase {
    @Autowired
    private PetDao petDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemovePet() {
        Pet pet = new Pet();

        // enter all required fields

        log.debug("adding pet...");
        pet = petDao.save(pet);

        pet = petDao.get(pet.getId());

        assertNotNull(pet.getId());

        log.debug("removing pet...");

        petDao.remove(pet.getId());

        // should throw DataAccessException 
        petDao.get(pet.getId());
    }
}