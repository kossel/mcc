package com.kossel.webapp.controller;

import com.kossel.webapp.controller.BaseControllerTestCase;
import com.kossel.model.Pet;
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

public class PetFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private PetFormController form;
    private Pet pet;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/petform");
        request.addParameter("id", "-1");

        pet = form.showForm(request);
        assertNotNull(pet);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/petform");
        request.addParameter("id", "-1");

        pet = form.showForm(request);
        assertNotNull(pet);

        request = newPost("/petform");

        pet = form.showForm(request);
        // update required fields

        BindingResult errors = new DataBinder(pet).getBindingResult();
        form.onSubmit(pet, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/petform");
        request.addParameter("delete", "");
        pet = new Pet();
        pet.setId(-2L);

        BindingResult errors = new DataBinder(pet).getBindingResult();
        form.onSubmit(pet, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
