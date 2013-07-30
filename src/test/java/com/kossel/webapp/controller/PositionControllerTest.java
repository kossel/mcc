package com.kossel.webapp.controller;

import com.kossel.service.PositionManager;
import com.kossel.model.Position;

import com.kossel.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class PositionControllerTest extends BaseControllerTestCase {
    @Autowired
    private PositionController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("positionList"));
        assertTrue(((List) m.get("positionList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        PositionManager positionManager = (PositionManager) applicationContext.getBean("positionManager");
        positionManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("positionList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}