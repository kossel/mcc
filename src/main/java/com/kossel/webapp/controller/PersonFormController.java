package com.kossel.webapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kossel.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kossel.model.Person;

@Controller
@RequestMapping("/personform*")
public class PersonFormController extends BaseFormController {
    private PersonManager personManager = null;
    private PositionManager positionManager= null;
    private DepartmentManager departmentManager= null;


    @Autowired
    public void setPersonManager(@Qualifier("personManager") PersonManager personManager) {
        this.personManager = personManager;
    }
    @Autowired
    public void setPositionManager(PositionManager positionManager) {
        this.positionManager = positionManager;
    }

    @Autowired
    public void setDepartmentManager(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    public PersonFormController() {
        setCancelView("redirect:persons");
        setSuccessView("redirect:persons");
    }
 
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Person showForm(HttpServletRequest request,ModelMap modelMap)
    throws Exception {
        String id = request.getParameter("id");
        modelMap.addAttribute("departmentlist",this.departmentManager.getAll());
        modelMap.addAttribute("positionlist",this.positionManager.getAll());
        if (!StringUtils.isBlank(id)) {
            Person person = personManager.get(new Long(id));

            return person;
        }
 
        return new Person();
    }
 
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Person person, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(person, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "personform";
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (person.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
            personManager.remove(person.getId());
            saveMessage(request, getText("person.deleted", locale));
        } else {
            personManager.save(request.getRemoteUser(),person);
            String key = (isNew) ? "person.added" : "person.updated";
            saveMessage(request, getText(key, locale));
            if (!isNew) {
                //success = "redirect:personform?id=" + person.getId();
                success = "redirect:persons";
            }else{
                success = "redirect:personform";
            }

        }
 
        return success;
    }
}