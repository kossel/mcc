package com.kossel.webapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kossel.model.Pet;
import com.kossel.service.PersonManager;
import com.kossel.service.PetManager;

@Controller
@RequestMapping("/petform*")
public class PetFormController extends BaseFormController {
    private PetManager petManager = null;
    private PersonManager personManager = null;

    @Autowired
    public void setPetManager(PetManager petManager) {
        this.petManager = petManager;
    }
    
    @Autowired
    public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	public PetFormController() {
        setCancelView("redirect:pets");
        setSuccessView("redirect:pets");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Pet showForm(HttpServletRequest request, ModelMap modelMap)
    throws Exception {
        String id = request.getParameter("id");
        modelMap.addAttribute("personlist",this.personManager.getAll());
        if (!StringUtils.isBlank(id)) {
        	Pet p = petManager.get(new Long(id));
        	//System.out.println(p.getPerson());
            return p;
        }
       // modelMap.addAttribute("personlist",this.personManager.getAll());
        return new Pet();
    }
    
  /*  @ModelAttribute("personlist")
    protected ArrayList<Person> popPerson(){
    	return (ArrayList<Person>) this.personManager.getAll();
    	
    }*/
    

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Pet pet, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(pet, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "petform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (pet.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            petManager.remove(pet.getId());
            saveMessage(request, getText("pet.deleted", locale));
        } else {
            petManager.save(pet);
            String key = (isNew) ? "pet.added" : "pet.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:petform?id=" + pet.getId();
            }
        }

        return success;
    }
}
