package com.keyi.keyi_weitao_zxing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.keyi.keyi_weitao_zxing.db.DBHelper;
import com.keyi.keyi_weitao_zxing.db.LoadFalse;
import com.keyi.keyi_weitao_zxing.db.User;
import com.keyi.keyi_weitao_zxing.db.UserDao;
import com.keyi.keyi_weitao_zxing.utils.DatasUtils;
import com.keyi.keyi_weitao_zxing.utils.HttpUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/6/6.
 */
public class CollectionActivity extends Activity{
    private ListView listView;
    private CollectionAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        listView= (ListView) findViewById(R.id.lv_collection);
        init();
    }

    private void init() {
        List<User> user=queryAll();
        adapter=new CollectionAdapter(user,CollectionActivity.this);
        listView.setAdapter(adapter);
        TextView textView= (TextView) findViewById(R.id.no_data);
        listView.setEmptyView(textView);
    }

    private List<User> queryAll(){
        try {
            List<User> users = DBHelper.getHelper(this).getUserDao().queryForAll();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class CollectionAdapter extends BaseAdapter implements View.OnClickListener {
        List<User> user;
        private Context context;
        public CollectionAdapter(List<User> user,Context context){
            this.user=user;
            this.context=context;
        }
        @Override
        public int getCount() {
            return user.size();
        }

        @Override
        public Object getItem(int position) {
            return user.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_collect,parent, false);
            TextView textView= (TextView) convertView.findViewById(R.id.tv_list_collet);
            TextView textView1= (TextView) convertView.findViewById(R.id.tv_list_workers);
            Button button= (Button) convertView.findViewById(R.id.bt_listview_collection);
            button.setTag(position);
            button.setOnClickListener(this);
            textView.setText("工序单号："+user.get(position).getGongXuDan());
            textView1.setText(user.get(position).getName());
            return convertView;
        }

        @Override
        public void onClick(final View v) {
            final UserDao dao=new UserDao(context);
            DatasUtils datasUtils=new DatasUtils();
            Log.e("sdasds", Integer.parseInt(v.getTag().toString()) + "");
            final String url= DatasUtils.upLoadingMsg+datasUtils.MobilNumber(context)+DatasUtils.strToken+datasUtils.SMSData(context)+"&ModelJson="+user.get(Integer.parseInt(v.getTag().toString())).getDesc();
            HttpUtils httpUtils=new HttpUtils();
            Log.e("sdasds",url);
            httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
                @Override
                public void onSusscess(String data) {
                    Gson gson=new Gson();
                    LoadFalse loadFalse = gson.fromJson(data, LoadFalse.class);
                    if (loadFalse.isIsOK()) {
                        new AlertDialog.Builder(CollectionActivity.this).setMessage("信息上传成功!")
                                .setPositiveButton("确定", null).show();
                        dao.deleteUser(user.get(Integer.parseInt(v.getTag().toString())).getId());
                        user.remove(Integer.parseInt(v.getTag().toString()));
                        adapter.notifyDataSetChanged();
                    } else {
                        new AlertDialog.Builder(CollectionActivity.this).setTitle("信息上传失败！").setMessage(loadFalse.getData().get(0).getRemark())
                                .setPositiveButton("确定", null).show();
                    }
                }
            });
        }
    }

}
