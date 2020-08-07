package com.shufeng.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.law.pojo.Law;

import com.shufeng.greendao.gen.LawDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig lawDaoConfig;

    private final LawDao lawDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        lawDaoConfig = daoConfigMap.get(LawDao.class).clone();
        lawDaoConfig.initIdentityScope(type);

        lawDao = new LawDao(lawDaoConfig, this);

        registerDao(Law.class, lawDao);
    }
    
    public void clear() {
        lawDaoConfig.clearIdentityScope();
    }

    public LawDao getLawDao() {
        return lawDao;
    }

}