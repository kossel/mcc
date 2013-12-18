package com.kossel.webapp.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class PersonControllerTest extends BaseControllerTestCase{
	
	@Autowired
	private PersonController controller;
	
	@Test
	public void testHandleRequest() throws Exception{
		ModelAndView mav = controller.handleRequest(new MockHttpServletRequest());
		ModelMap m = mav.getModelMap();
		
		assertNotNull(m.get("personList"));
		assertTrue(((List)m.get("personList")).size()>0);
	}

}
