package com.kossel.service;

import com.kossel.service.GenericManager;
import com.kossel.model.Pet;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface PetManager extends GenericManager<Pet, Long> {
    
}