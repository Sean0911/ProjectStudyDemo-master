package network.sean.com.projectstudy.manager;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;

import com.sean.greentest.DaoMaster;
import com.sean.greentest.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public abstract class GreenDaoManager<T, K> implements IOperateDB<T, K> {

    private static final String DB_NAME = "project_db";
    private static MySQLiteOpenHelper mHelp;
    protected static DaoSession mDaoSession;

    /*
    *初始化Helper
    *
    */
    public static void init(Context context) {
        mHelp = new MySQLiteOpenHelper(new GreenDaoContext(context), DB_NAME, null);
        mDaoSession = new DaoMaster(mHelp.getWritableDb()).newSession();
    }

    @Override
    public boolean insert(@NotNull T entity) {
        try {
            if (entity != null) {
                getDao().insert(entity);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insertOrReplace(@NotNull T entity) {
        try {
            if (entity != null) {
                getDao().insertOrReplace(entity);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insertList(@NotNull List<T> mList) {
        try {
            if (mList != null && mList.size() > 0) {
                getDao().insertInTx(mList);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insertOrReplaceList(@NotNull List<T> mList) {
        try {
            if (mList != null && mList.size() > 0) {
                getDao().insertOrReplaceInTx(mList);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(@NotNull T entity) {
        try {
            if (entity != null) {
                getDao().delete(entity);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(K key) {
        try {
            if (!TextUtils.isEmpty(key.toString())) {
                getDao().deleteByKey(key);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteList(List<T> mList) {
        try {
            if (mList != null && mList.size() > 0) {
                getDao().deleteInTx(mList);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @SafeVarargs
    public final boolean deleteByKeyInTx(K... keys) {
        try {
            if (keys != null && keys.length > 0) {
                getDao().deleteByKeyInTx(keys);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try {
            getDao().deleteAll();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(T entity) {
        try {
            if (entity != null) {
                getDao().update(entity);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @SafeVarargs
    public final boolean updateInTx(T... entity) {
        try {
            if (entity != null && entity.length > 0) {
                getDao().updateInTx(entity);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateList(List<T> mList) {

        try {
            if (mList != null && mList.size() > 0) {
                getDao().updateInTx(mList);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public T selectByPrimaryKey(@NotNull K key) {
        try {
            return getDao().load(key);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> loadAll() {
        try {
            return getDao().loadAll();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean refresh(T entity) {
        try {
            if(entity != null) {
                getDao().refresh(entity);
                return true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void clearDaoSession() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    @Override
    public boolean dropDatabase() {
        return false;
    }

    @Override
    public void runInTx(Runnable runnable) {
        try {
            mDaoSession.runInTx(runnable);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    @Override
        public QueryBuilder<T> getQueryBuilder() {
        return getDao().queryBuilder();
    }

    @Override
        public List<T> queryRaw(String where, String... selectionArg) {
        return getDao().queryRaw(where,selectionArg);
    }

    abstract AbstractDao<T, K> getDao();
}
