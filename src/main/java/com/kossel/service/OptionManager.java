package com.kossel.service;

import com.kossel.model.Option;

import javax.jws.WebService;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Yichao
 * Date: 12/9/13
 * Time: 9:30 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService
public interface OptionManager extends GenericManager<Option,Long>{

          public void updateLastEditInfo(String name, Date date);

}
