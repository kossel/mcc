package com.kossel.webapp.controller;

import com.kossel.dao.SearchException;
import com.kossel.service.PetManager;
import com.kossel.model.Pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pets*")
public class PetController {
    private PetManager petManager;

    @Autowired
    public void setPetManager(PetManager petManager) {
        this.petManager = petManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(petManager.search(query, Pet.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(petManager.getAll());
        }
        return model;
    }
}
