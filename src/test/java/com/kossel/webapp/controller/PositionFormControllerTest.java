package com.kossel.webapp.controller;

import com.kossel.webapp.controller.BaseControllerTestCase;
import com.kossel.model.Position;
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

public class PositionFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private PositionFormController form;
    private Position position;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/positionform");
        request.addParameter("id", "-1");

        position = form.showForm(request);
        assertNotNull(position);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/positionform");
        request.addParameter("id", "-1");

        position = form.showForm(request);
        assertNotNull(position);

        request = newPost("/positionform");

        position = form.showForm(request);
        // update required fields

        BindingResult errors = new DataBinder(position).getBindingResult();
        form.onSubmit(position, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/positionform");
        request.addParameter("delete", "");
        position = new Position();
        position.setId(-2L);

        BindingResult errors = new DataBinder(position).getBindingResult();
        form.onSubmit(position, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
