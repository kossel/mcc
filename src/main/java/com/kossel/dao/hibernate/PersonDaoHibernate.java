package com.kossel.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kossel.dao.PersonDao;
import com.kossel.model.Person;

@Repository
public class PersonDaoHibernate extends GenericDaoHibernate<Person, Long> implements PersonDao{

	public PersonDaoHibernate(){
		super(Person.class);
	}
	
	public List<Person> findByLastName(String lastName){
		return getSession().createCriteria(Person.class).add(Restrictions.eq("lastName", lastName)).list();
	}

    public List<Person> getByDept(){

        Criteria c = getSession().createCriteria(Person.class);
        c.createAlias("department", "dept");
        c.addOrder(Order.asc("dept.ord"));
        return c.list();

       //  return getSession().createCriteria(Person.class).addOrder(Order.asc("department.order")).list();
    }

}
