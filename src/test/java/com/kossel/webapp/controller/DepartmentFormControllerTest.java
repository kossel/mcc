package com.kossel.webapp.controller;

import com.kossel.webapp.controller.BaseControllerTestCase;
import com.kossel.model.Department;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DepartmentFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private DepartmentFormController form;
    private Department department;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/departmentform");
        request.addParameter("id", "-1");

        department = form.showForm(request);
        assertNotNull(department);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/departmentform");
        request.addParameter("id", "-1");

        department = form.showForm(request);
        assertNotNull(department);

        request = newPost("/departmentform");

        department = form.showForm(request);
        // update required fields

        BindingResult errors = new DataBinder(department).getBindingResult();
        form.onSubmit(department, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/departmentform");
        request.addParameter("delete", "");
        department = new Department();
        department.setId(-2L);

        BindingResult errors = new DataBinder(department).getBindingResult();
        form.onSubmit(department, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
