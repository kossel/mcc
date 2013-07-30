package com.kossel.dao;

import com.kossel.dao.BaseDaoTestCase;
import com.kossel.model.Position;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class PositionDaoTest extends BaseDaoTestCase {
    @Autowired
    private PositionDao positionDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemovePosition() {
        Position position = new Position();

        // enter all required fields

        log.debug("adding position...");
        position = positionDao.save(position);

        position = positionDao.get(position.getId());

        assertNotNull(position.getId());

        log.debug("removing position...");

        positionDao.remove(position.getId());

        // should throw DataAccessException 
        positionDao.get(position.getId());
    }
}