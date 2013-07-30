package com.kossel.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.kossel.service.PositionManager;
import com.kossel.model.Position;
import com.kossel.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/admin/positionform*")
public class PositionFormController extends BaseFormController {
    private PositionManager positionManager = null;

    @Autowired
    public void setPositionManager(PositionManager positionManager) {
        this.positionManager = positionManager;
    }

    public PositionFormController() {
        setCancelView("redirect:/admin/positions");
        setSuccessView("redirect:/admin/positions");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Position showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return positionManager.get(new Long(id));
        }

        return new Position();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Position position, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(position, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "positionform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (position.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            positionManager.remove(position.getId());
            saveMessage(request, getText("position.deleted", locale));
        } else {
            positionManager.save(position);
            String key = (isNew) ? "position.added" : "position.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:/admin/positionform?id=" + position.getId();
            }
        }

        return success;
    }
}
