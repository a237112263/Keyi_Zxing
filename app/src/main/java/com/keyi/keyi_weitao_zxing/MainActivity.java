package com.keyi.keyi_weitao_zxing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.keyi.keyi_weitao_zxing.Bean.DoneNumber;
import com.keyi.keyi_weitao_zxing.Bean.ModelJson;
import com.keyi.keyi_weitao_zxing.Bean.SelectData;
import com.keyi.keyi_weitao_zxing.db.DBHelper;
import com.keyi.keyi_weitao_zxing.db.LoadFalse;
import com.keyi.keyi_weitao_zxing.db.User;
import com.keyi.keyi_weitao_zxing.db.UserDao;
import com.keyi.keyi_weitao_zxing.utils.ACache;
import com.keyi.keyi_weitao_zxing.utils.DatasUtils;
import com.keyi.keyi_weitao_zxing.utils.HttpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import scanner.CaptureActivity;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewInject(value = R.id.bt_sms1)
    private Button button;
    @ViewInject(value = R.id.layout_main)
    private LinearLayout linearLayout;
    @ViewInject(value = R.id.layout_main1)
    private LinearLayout linearLayout1;
    @ViewInject(value = R.id.iv_main)
    private ImageView imageView;
    @ViewInject(value = R.id.tv_main)
    private TextView textView;
    @ViewInject(value = R.id.tv_main1)
    private TextView textView1;
    @ViewInject(value = R.id.tv_main2)
    private TextView textView2;
    @ViewInject(value = R.id.tv_main3)
    private TextView textView3;
    @ViewInject(value = R.id.tv_main4)
    private TextView textView4;
    @ViewInject(value = R.id.bt_main)
    private Button button1;
    @ViewInject(value = R.id.bt_select)
    private Button button2;
    @ViewInject(value = R.id.tv_main6)
    private TextView textView6;
    @ViewInject(value = R.id.tv_main7)
    private TextView textView7;
    @ViewInject(value = R.id.tab_bar)
    private FrameLayout frameLayout;
    @ViewInject(value = R.id.iv_collection)
    private ImageView imageView2;
    @ViewInject(value = R.id.iv_main1)
    private ImageView imageView3;
    private long exitTime = 0;
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private ACache aCache;
    private DatasUtils datasUtils;
    private StringBuffer stringBuffer;
    private int sum = 0;
    private DoneNumber doneNumber;
    List<String> workname;
    List<String> worknumber;
    private TelephonyManager telephonyManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        aCache = ACache.get(this);
        telephonyManager= (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        datasUtils = new DatasUtils();
        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        Intent intent = getIntent();
        stringBuffer=new StringBuffer();
        stringBuffer.append(" ");
        if (intent.getBooleanExtra("intent", false)) {
            workname=new ArrayList<>();
            worknumber=new ArrayList<>();
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.GONE);
            frameLayout.setVisibility(View.GONE);
            textView6.setText("工序单号：" + aCache.getAsString("工序单号"));
            textView1.setText("订单号：" + aCache.getAsString("订单号"));
            textView4.setText("总数量：" + aCache.getAsString("总数量"));
            textView3.setText("工序：" + aCache.getAsString("工序"));
            textView2.setText("排产单号：" + aCache.getAsString("排产单号"));
            textView.setText( aCache.getAsString("商品名"));
            textView7.setText("剩余数量：" + aCache.getAsString("剩余数量"));
            SelectData selectData= (SelectData) intent.getSerializableExtra("workmsg");
            for (int i = 0; i < selectData.getWorks().size(); i++) {
                   stringBuffer.append(selectData.getWorks().get(i).getName() + " ");
                   stringBuffer.append(selectData.getWorks().get(i).getNum() + ",");
                   workname.add(selectData.getWorks().get(i).getName());
                   worknumber.add(selectData.getWorks().get(i).getNum()+"");
            }
            Log.e("stringBuffer", stringBuffer.toString());
            if (stringBuffer.toString().equals(" ")){
                button2.setText("选择工人,分配数量");
            }else {
                button2.setTextSize(12);
                button2.setText(stringBuffer);
            }
        }
    }


    @Override
    public void onClick(View v) {
        //扫一扫按钮
        switch (v.getId()) {
            case R.id.bt_sms1:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                break;
            //返回到扫描界面
            case R.id.iv_main:
                aCache.remove("upname");
                Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            //上传数据按钮
            case R.id.bt_main:
                upLoading();
                break;
            case R.id.iv_collection:
                Intent intent2 = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt_select:
                Log.e("剩余数量",aCache.getAsString("剩余数量"));
                if (aCache.getAsString("剩余数量").toString().equals("0")){
                    Toast.makeText(MainActivity.this, "剩余数量为0，不能选工人！", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent3 = new Intent(MainActivity.this, SelectWorkerActivity.class);
                    startActivity(intent3);
                    finish();
                }
                break;
            case R.id.iv_main1:
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this, SMSActivity.class);
                startActivity(intent3);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.GONE);
                    Bundle bundle = data.getExtras();
                    Log.e("asda", bundle.getString("result").toString());
                    String string[] = bundle.getString("result").split("\\$");
                    for (String str : string
                            ) {
                        if (str.equals("KYSOFT")) {
                            loadingDoneNumber(string[2]);
                            for (int i = 0; i < string.length; i++) {
                                if (i == 2) {
                                    textView6.setText("工序单号：" + string[2]);
                                    aCache.put("工序单号", string[i]);
                                } else if (i == 0) {

                                } else if (i == 1) {

                                } else if (i == 3) {
                                    textView.setText(string[i]);
                                    aCache.put("商品名", string[i]);
                                } else if (i == 4) {
                                    textView3.setText(getString(R.string.item4) + string[i]);
                                    aCache.put("工序", string[i]);
                                } else if (i == 5) {
                                    textView4.setText(getString(R.string.item5) + string[i]);
                                    aCache.put("总数量", string[i]);
                                }
                            }
                        } else {
                            textView1.setText("没有该订单号！");
                        }
                    }
                }
                break;
        }
    }

    //通过工序单号下载剩余的数量
    private void loadingDoneNumber(String string) {
        String url = DatasUtils.doneNumberUrl + datasUtils.MobilNumber(this) + DatasUtils.strToken + datasUtils.SMSData(this) + "&GSNo=" + string;
        Log.e("url1", url.toString());
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                doneNumber = gson.fromJson(data, DoneNumber.class);
                if (doneNumber.isIsOK() == false) {
                    textView7.setText(getString(R.string.item7) + "没有数据");
                    button1.setClickable(false);
                    button2.setClickable(false);
                } else {
                    textView7.setText(getString(R.string.item7) + String.valueOf(doneNumber.getData().get(0).getRemainNum()));
                    textView1.setText("订单号："+doneNumber.getData().get(0).getSysTradeNo());
                    textView2.setText("排产单号："+doneNumber.getData().get(0).getPrdNo());
                    aCache.put("排产单号", doneNumber.getData().get(0).getPrdNo());
                    aCache.put("订单号", doneNumber.getData().get(0).getSysTradeNo());
                    aCache.put("剩余数量", String.valueOf(doneNumber.getData().get(0).getRemainNum()));
                    if (doneNumber.getData().get(0).getRemainNum() == 0) {
                        button1.setClickable(false);
                        button2.setText("剩余数量0,无法操作！");
                    }
                }
            }
            @Override
            public void onError(String meg) {
                super.onError(meg);
                textView7.setText(getString(R.string.item7) + "没有数据");
                button1.setClickable(false);
                button2.setClickable(false);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
    private void upLoading() {
        if (stringBuffer.toString().equals(" ")){
            Toast.makeText(MainActivity.this,"没有分配工人与数量！",Toast.LENGTH_SHORT).show();
        }else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                    this);
            alertDialog.setTitle(this
                    .getString(R.string.app_close));
            alertDialog.setMessage("分配信息：" + stringBuffer + "\n电话：" + datasUtils.MobilNumber(this) + "\n工序：" + aCache.getAsString("工序单号"));
            alertDialog.setPositiveButton(this
                            .getString(R.string.btn_ok),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            HttpUtils httpUtils = new HttpUtils();
                            ModelJson modelJson = new ModelJson();
                            modelJson.setMobile(datasUtils.MobilNumber(MainActivity.this));
                            modelJson.setProcNo(aCache.getAsString("工序单号"));
                            modelJson.setDeviceId(telephonyManager.getDeviceId() + "");
                            List<ModelJson.WorksBean> list = new ArrayList<ModelJson.WorksBean>();
                            for (int i = 0; i < workname.size(); i++) {
                                ModelJson.WorksBean worksBean = new ModelJson.WorksBean();
                                worksBean.setRealName(workname.get(i));
                                worksBean.setNum(Integer.parseInt(worknumber.get(i)));
                                list.add(worksBean);
                                sum = sum + Integer.parseInt(worknumber.get(i));
                                Log.e("getRealName", workname.get(i));
                            }
                            for (int j = 0; j < list.size(); j++) {
                                Log.e("getRealName", list.get(j).getRealName());
                            }
                            modelJson.setWorks(list);
                            final String json = new Gson().toJson(modelJson);
                            if (sum > Integer.parseInt(aCache.getAsString("剩余数量"))) {
                                Toast.makeText(MainActivity.this, "工人分配的总数量大于剩余的数量，请重新选择上传！", Toast.LENGTH_SHORT).show();
                                sum = 0;
                            } else {
                                String url = DatasUtils.upLoadingMsg + datasUtils.MobilNumber(MainActivity.this) + DatasUtils.strToken + datasUtils.SMSData(MainActivity.this) + "&ModelJson=" + json;
                                Log.e("upLoadingMsg", url.toString());
                                httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
                                    @Override
                                    public void onSusscess(String data) {
                                        UserDao dao=new UserDao(MainActivity.this);
                                        Gson gson = new Gson();
                                        LoadFalse loadFalse = gson.fromJson(data, LoadFalse.class);
                                        if (loadFalse.isIsOK()) {
                                            new AlertDialog.Builder(MainActivity.this).setMessage("信息上传成功!")
                                                    .setPositiveButton("确定", null).show();
                                            loadingDoneNumber(aCache.getAsString("工序单号"));
                                            button2.setText("选择工人,分配数量");
                                            List<User> user = queryAll();
                                            for (int i = 0; i < user.size(); i++) {
                                                if (user.get(i).getGongXuDan().equals(aCache.getAsString("工序单号"))) {
                                                    dao.deleteUser(user.get(i).getId());
                                                }
                                            }
                                        } else {
                                            new AlertDialog.Builder(MainActivity.this).setTitle("信息上传失败！").setMessage(loadFalse.getData().get(0).getRemark())
                                                    .setPositiveButton("确定", null).show();
                                            button1.setClickable(false);
                                        }
                                    }

                                    @Override
                                    public void onError(String meg) {
                                        super.onError(meg);
                                        Toast.makeText(MainActivity.this, "上传失败！要上传信息已收藏！", Toast.LENGTH_LONG).show();
                                        UserDao dao = new UserDao(MainActivity.this);
                                        User user = new User(aCache.getAsString("工序单号"), String.valueOf(stringBuffer), json);
                                        dao.add(user);
                                        button1.setClickable(false);
                                    }
                                });
                                sum = 0;
                            }
                        }
                    });
            alertDialog.setNegativeButton(this
                            .getString(R.string.btn_cancel),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {

                        }
                    });
            alertDialog.show();
        }
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
}
