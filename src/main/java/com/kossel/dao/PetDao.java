package com.kossel.dao;

import com.kossel.dao.GenericDao;

import com.kossel.model.Pet;

/**
 * An interface that provides a data management interface to the Pet table.
 */
public interface PetDao extends GenericDao<Pet, Long> {

}