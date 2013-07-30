package com.kossel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.kossel.dao.DepartmentDao;
import com.kossel.model.Department;
import com.kossel.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class DepartmentManagerImplTest extends BaseManagerMockTestCase {
    private DepartmentManagerImpl manager = null;
    private DepartmentDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(DepartmentDao.class);
        manager = new DepartmentManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetDepartment() {
        log.debug("testing get...");

        final Long id = 7L;
        final Department department = new Department();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(department));
        }});

        Department result = manager.get(id);
        assertSame(department, result);
    }

    @Test
    public void testGetDepartments() {
        log.debug("testing getAll...");

        final List departments = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(departments));
        }});

        List result = manager.getAll();
        assertSame(departments, result);
    }

    @Test
    public void testSaveDepartment() {
        log.debug("testing save...");

        final Department department = new Department();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(department)));
        }});

        manager.save(department);
    }

    @Test
    public void testRemoveDepartment() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}