package com.kossel.service.impl;

import com.kossel.dao.PetDao;
import com.kossel.model.Pet;
import com.kossel.service.PetManager;
import com.kossel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("petManager")
@WebService(serviceName = "PetService", endpointInterface = "com.kossel.service.PetManager")
public class PetManagerImpl extends GenericManagerImpl<Pet, Long> implements PetManager {
    PetDao petDao;

    @Autowired
    public PetManagerImpl(PetDao petDao) {
        super(petDao);
        this.petDao = petDao;
    }
}