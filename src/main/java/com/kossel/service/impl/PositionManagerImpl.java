package com.kossel.service.impl;

import com.kossel.dao.PositionDao;
import com.kossel.model.Position;
import com.kossel.service.PositionManager;
import com.kossel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("positionManager")
@WebService(serviceName = "PositionService", endpointInterface = "com.kossel.service.PositionManager")
public class PositionManagerImpl extends GenericManagerImpl<Position, Long> implements PositionManager {
    PositionDao positionDao;

    @Autowired
    public PositionManagerImpl(PositionDao positionDao) {
        super(positionDao);
        this.positionDao = positionDao;
    }
}