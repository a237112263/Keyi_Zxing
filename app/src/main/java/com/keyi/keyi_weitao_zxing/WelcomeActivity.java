package com.keyi.keyi_weitao_zxing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.keyi.keyi_weitao_zxing.Bean.WeiTaoGC;
import com.keyi.keyi_weitao_zxing.utils.ACache;
import com.keyi.keyi_weitao_zxing.utils.UpdateManager;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    @Override
    protected void onStart() {
        super.onStart();
        BmobQuery<WeiTaoGC> bmobQuery=new BmobQuery<WeiTaoGC>();
        bmobQuery.getObject(this, "LQJg1112", new GetListener<WeiTaoGC>() {
            @Override
            public void onSuccess(WeiTaoGC person) {
                final UpdateManager manager = new UpdateManager(WelcomeActivity.this);
                Log.e("ASDASDD", person.getVersioncode().toString());
                // 获取当前软件版本
                int versionCode = getVersionCode(WelcomeActivity.this);
                Log.e("versionCode", versionCode + "");
                if (versionCode < Integer.parseInt(person.getVersioncode().toString())) {
                    manager.checkUpdate();
                } else {

                }
            }
            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(getApplicationContext(), "网络连接错误,请检查网络！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        imageView= (ImageView) findViewById(R.id.iv_welcome);
        imageView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        final ACache aCache=ACache.get(this);
        SharedPreferences setting = getSharedPreferences("SHARE_APP_TAG", 0);
        Boolean user_first = setting.getBoolean("FIRST", true);
        if(user_first){//第一次
            setting.edit().putBoolean("FIRST", false).commit();
            Intent intent=new Intent(WelcomeActivity.this,SMSActivity.class);
            startActivity(intent);
            finish();
        }else{
            try {
                Log.e("sadasd", aCache.getAsString("yanzhengma").toString());
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                Intent intent1 = new Intent(WelcomeActivity.this, YanZhengMaAtivity.class);
                startActivity(intent1);
                finish();
            }
        }
    }
    private int getVersionCode(Context context)
    {
        int versionCode = 0;
        try
        {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = context.getPackageManager().getPackageInfo("com.keyi.keyi_weitao_zxing", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return versionCode;
    }
}
