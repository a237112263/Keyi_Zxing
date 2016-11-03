package com.keyi.keyi_weitao_zxing.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/6/6.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLE_NAME = "sqlite-keyi.db";
    /**
     * userDao ，每张表对于一个
     */
    private Dao<User, Integer> userDao;
    private RuntimeExceptionDao<User, Integer> userRuntimeDao = null;

    private DBHelper(Context context)
    {
        super(context, TABLE_NAME, null, 1);
    }
    public RuntimeExceptionDao<User, Integer> getUserDataDao()
    {
        if (userRuntimeDao == null)
        {
            userRuntimeDao = getRuntimeExceptionDao(User.class);
        }
        return userRuntimeDao;
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource)
    {
        try
        {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static DBHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     *
     */
    public static synchronized DBHelper getHelper(Context context)
    {
        if (instance == null)
        {
            synchronized (DBHelper.class)
            {
                if (instance == null)
                    instance = new DBHelper(context);
            }
        }

        return instance;
    }

    /**
     * 获得userDao
     *
     * @return
     * @throws SQLException
     */
    public Dao<User, Integer> getUserDao() throws SQLException
    {
        if (userDao == null)
        {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();
        userDao = null;
    }
}
