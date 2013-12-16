package com.kossel.service.impl;

import com.kossel.dao.OptionDao;
import com.kossel.model.Option;
import com.kossel.service.OptionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Yichao
 * Date: 12/9/13
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */

@Service("optionManager")
public class OptionManagerImpl extends  GenericManagerImpl<Option,Long> implements OptionManager{

    OptionDao optionDao;

    @Autowired
    public OptionManagerImpl(OptionDao optionDao){
        super(optionDao);
        this.optionDao = optionDao;
    }

    public Option getCurrentOption(){
        return optionDao.getAll().get(0);
    }

    public void updateLastEditInfo(String name, Date date){
        Option op = new Option();
        op.setLastEditPersonsTime(date);
        op.setWhoLastEditPersons(name);
        optionDao.updateOptions(op);

    }

}
