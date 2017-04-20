package network.sean.com.projectstudy.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sean.greentest.DaoMaster;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
