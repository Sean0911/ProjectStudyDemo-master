package network.sean.com.projectstudy.manager;

import org.greenrobot.greendao.AbstractDao;

import network.sean.com.projectstudy.bean.GankContent;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class GankContentManager extends GreenDaoManager<GankContent,String> {

    @Override
    AbstractDao<GankContent, String> getDao() {
        return mDaoSession.getGankContentDao();
    }
}
