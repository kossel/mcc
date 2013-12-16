package com.kossel.dao.hibernate;

import com.kossel.dao.OptionDao;
import com.kossel.model.Option;
import com.kossel.util.MyBeanUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yichao
 * Date: 12/9/13
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("optionDao")
public class OptionDaoHibernate extends GenericDaoHibernate<Option, Long> implements OptionDao {

    public OptionDaoHibernate(){
        super(Option.class);
    }


    @Override
    public void updateOptions(Option option) {
        List<Option> options;
        options = getSession().createCriteria(Option.class).list();
        Option dbOption = options.get(0);
        dbOption.setChanges(dbOption.getChanges()+1);
        MyBeanUtils.copyBeanPropertiesIgnoreNull(option,dbOption);
      //  dbOption.setLastEditPersonsTime(new Date());
        getSession().merge(dbOption);
    }
}
