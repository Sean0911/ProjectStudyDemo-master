package network.sean.com.projectstudy.manager;

import android.content.Context;
import android.content.ContextWrapper;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

import network.sean.com.projectstudy.util.CommonUtils;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class GreenDaoContext extends ContextWrapper {

    private String currentUserId = "";//一般用来针对一个用户一个数据库，以免数据混乱问题

    public GreenDaoContext(Context base) {
        super(base);
    }

    @Override
    public File getDatabasePath(String name) {
        String dbDir = CommonUtils.getDBPath();
        if(TextUtils.isEmpty(dbDir)){

        }
        return super.getDatabasePath(name);
    }
}
