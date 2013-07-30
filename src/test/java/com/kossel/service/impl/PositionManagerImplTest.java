package com.kossel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.kossel.dao.PositionDao;
import com.kossel.model.Position;
import com.kossel.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class PositionManagerImplTest extends BaseManagerMockTestCase {
    private PositionManagerImpl manager = null;
    private PositionDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(PositionDao.class);
        manager = new PositionManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetPosition() {
        log.debug("testing get...");

        final Long id = 7L;
        final Position position = new Position();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(position));
        }});

        Position result = manager.get(id);
        assertSame(position, result);
    }

    @Test
    public void testGetPositions() {
        log.debug("testing getAll...");

        final List positions = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(positions));
        }});

        List result = manager.getAll();
        assertSame(positions, result);
    }

    @Test
    public void testSavePosition() {
        log.debug("testing save...");

        final Position position = new Position();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(position)));
        }});

        manager.save(position);
    }

    @Test
    public void testRemovePosition() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}