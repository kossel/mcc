package com.kossel.service;

import com.kossel.service.GenericManager;
import com.kossel.model.Department;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface DepartmentManager extends GenericManager<Department, Long> {
    
}