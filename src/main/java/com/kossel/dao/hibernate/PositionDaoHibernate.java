package com.kossel.dao.hibernate;

import com.kossel.model.Position;
import com.kossel.dao.PositionDao;
import com.kossel.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("positionDao")
public class PositionDaoHibernate extends GenericDaoHibernate<Position, Long> implements PositionDao {

    public PositionDaoHibernate() {
        super(Position.class);
    }
}
