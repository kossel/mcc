package com.kossel.dao;

import com.kossel.model.Option;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Yichao
 * Date: 12/9/13
 * Time: 9:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OptionDao extends GenericDao<Option, Long>{

    public void updateOptions(Option option);

}
