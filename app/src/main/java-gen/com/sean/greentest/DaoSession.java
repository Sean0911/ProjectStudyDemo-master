package com.sean.greentest;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import network.sean.com.projectstudy.bean.GankContent;

import com.sean.greentest.GankContentDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig gankContentDaoConfig;

    private final GankContentDao gankContentDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        gankContentDaoConfig = daoConfigMap.get(GankContentDao.class).clone();
        gankContentDaoConfig.initIdentityScope(type);

        gankContentDao = new GankContentDao(gankContentDaoConfig, this);

        registerDao(GankContent.class, gankContentDao);
    }
    
    public void clear() {
        gankContentDaoConfig.clearIdentityScope();
    }

    public GankContentDao getGankContentDao() {
        return gankContentDao;
    }

}