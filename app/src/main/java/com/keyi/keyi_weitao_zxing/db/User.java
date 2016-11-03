package com.keyi.keyi_weitao_zxing.db;

/**
 * Created by Administrator on 2016/6/6.
 */
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "updata_user")
public class User
{
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "gongxudan")
    private String gongXuDan;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "desc")
    private String desc;

    public User()
    {
    }

    public User(String gongXuDan, String name, String desc)
    {
        this.gongXuDan=gongXuDan;
        this.name = name;
        this.desc = desc;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public String getGongXuDan()
    {
        return gongXuDan;
    }

    public void setGongXuDan(String gongXuDan)
    {
        this.gongXuDan = gongXuDan;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

}
