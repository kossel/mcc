package com.kossel.webapp.controller;

import com.kossel.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kossel.model.Person;
import com.kossel.service.GenericManager;

@Controller
@RequestMapping("/persons*")
public class PersonController {
	private PersonManager personManager;
	
	@Autowired
	public void setPersonManager(@Qualifier("personManager")PersonManager personManager){
		this.personManager=personManager;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() throws Exception{
		return new ModelAndView().addObject(personManager.getByDept());
	}
}
