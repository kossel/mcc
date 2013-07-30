package com.kossel.service;

import com.kossel.service.GenericManager;
import com.kossel.model.Position;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface PositionManager extends GenericManager<Position, Long> {
    
}