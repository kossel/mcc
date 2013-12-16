package com.kossel.webapp.controller;

import com.kossel.service.OptionManager;
import com.kossel.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kossel.model.Person;
import com.kossel.service.GenericManager;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/persons*")
public class PersonController {
	private PersonManager personManager;
    private OptionManager optionManager;

	
	@Autowired
	public void setPersonManager(@Qualifier("personManager")PersonManager personManager){
		this.personManager=personManager;
	}

    @Autowired
    public void setOptionManager(@Qualifier("optionManager")OptionManager optionManager){
        this.optionManager=optionManager;
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request) throws Exception{
		request.getSession().setAttribute("opt",optionManager.get(1L));

        return new ModelAndView().addObject(personManager.getByDept());
	}


}
