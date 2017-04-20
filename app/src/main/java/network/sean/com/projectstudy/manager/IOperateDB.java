package network.sean.com.projectstudy.manager;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


/**
 * Created by Administrator on 2017/4/14 0014.
 */

public interface IOperateDB<T,K> {

    boolean insert(T entity);
    boolean insertOrReplace(T entity);
    boolean insertList(List<T> mList);
    boolean insertOrReplaceList(List<T> mList);

    boolean delete(T entity);
    boolean deleteByKey(K key);
    boolean deleteList(List<T> mList);
    boolean deleteByKeyInTx(K... keys);
    boolean deleteAll();

    //修改
    boolean update(T entity);
    boolean updateInTx(T... entity);
    boolean updateList(List<T> mList);

    //查询
    T selectByPrimaryKey(K key);
    List<T> loadAll();
    boolean refresh(T entity);

    /**
     * 清理缓存
     */
    void clearDaoSession();

    /**
     * Delete all tables and content from our database
     */
    boolean dropDatabase();

    /**
     * 事务
     */
    void runInTx(Runnable runnable);

    /**
     * 自定义查询
     * @return
     */
    QueryBuilder<T> getQueryBuilder();

    /**
     * @param where
     * @param selectionArg
     * @return
     */
    List<T> queryRaw(String where, String... selectionArg);
}
