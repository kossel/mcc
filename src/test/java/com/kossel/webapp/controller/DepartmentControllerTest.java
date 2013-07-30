package com.kossel.webapp.controller;

import com.kossel.service.DepartmentManager;
import com.kossel.model.Department;

import com.kossel.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class DepartmentControllerTest extends BaseControllerTestCase {
    @Autowired
    private DepartmentController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("departmentList"));
        assertTrue(((List) m.get("departmentList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        DepartmentManager departmentManager = (DepartmentManager) applicationContext.getBean("departmentManager");
        departmentManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("departmentList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}