package network.sean.com.projectstudy;

import android.app.Application;
import android.util.Log;

import com.orhanobut.logger.LogPrintStyle;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

import network.sean.com.projectstudy.manager.GreenDaoManager;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        GreenDaoManager.init(this);

        Settings settings = new Settings()
                .setStyle(new LogPrintStyle())
                .isShowMethodLink(true)
                .isShowThreadInfo(false)
                .setMethodOffset(0)
                .setLogPriority(BuildConfig.DEBUG ? Log.VERBOSE : Log.ASSERT);
        Logger.initialize(settings);
    }
}
