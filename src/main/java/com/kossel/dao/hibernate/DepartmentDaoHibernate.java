package com.kossel.dao.hibernate;

import com.kossel.model.Department;
import com.kossel.dao.DepartmentDao;
import com.kossel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("departmentDao")
public class DepartmentDaoHibernate extends GenericDaoHibernate<Department, Long> implements DepartmentDao {

    public DepartmentDaoHibernate() {
        super(Department.class);
    }
}
