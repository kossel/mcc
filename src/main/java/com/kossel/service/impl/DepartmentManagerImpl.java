package com.kossel.service.impl;

import com.kossel.dao.DepartmentDao;
import com.kossel.model.Department;
import com.kossel.service.DepartmentManager;
import com.kossel.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("departmentManager")
@WebService(serviceName = "DepartmentService", endpointInterface = "com.kossel.service.DepartmentManager")
public class DepartmentManagerImpl extends GenericManagerImpl<Department, Long> implements DepartmentManager {
    DepartmentDao departmentDao;

    @Autowired
    public DepartmentManagerImpl(DepartmentDao departmentDao) {
        super(departmentDao);
        this.departmentDao = departmentDao;
    }
}