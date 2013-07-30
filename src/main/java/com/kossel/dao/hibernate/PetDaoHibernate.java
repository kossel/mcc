package com.kossel.dao.hibernate;

import com.kossel.model.Pet;
import com.kossel.dao.PetDao;
import com.kossel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("petDao")
public class PetDaoHibernate extends GenericDaoHibernate<Pet, Long> implements PetDao {

    public PetDaoHibernate() {
        super(Pet.class);
    }
}
