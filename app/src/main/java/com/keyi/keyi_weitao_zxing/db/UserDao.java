package com.keyi.keyi_weitao_zxing.db;

/**
 * Created by Administrator on 2016/6/6.
 */
import android.content.Context;

import java.sql.SQLException;


public class UserDao
{
    private Context context;

    public UserDao(Context context)
    {
        this.context = context;
    }

    public void add(User user)
    {
        try
        {
            DBHelper.getHelper(context).getUserDao().create(user);
        } catch (SQLException e)
        {
        }
    }
    public void updateUser(User user)
    {
        DBHelper helper = DBHelper.getHelper(context);
        try
        {
            helper.getUserDao().update(user);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void deleteUser(int id)
    {
        DBHelper helper = DBHelper.getHelper(context);
        try
        {
            helper.getUserDao().deleteById(id);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
   //List<User> users = DBHelper.getHelper(context).getUserDao().queryForAll();
}
