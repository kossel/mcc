package com.kossel.webapp.controller;

import com.kossel.service.PetManager;
import com.kossel.model.Pet;

import com.kossel.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class PetControllerTest extends BaseControllerTestCase {
    @Autowired
    private PetController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("petList"));
        assertTrue(((List) m.get("petList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        PetManager petManager = (PetManager) applicationContext.getBean("petManager");
        petManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("petList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}