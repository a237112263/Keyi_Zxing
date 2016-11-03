package com.keyi.keyi_weitao_zxing;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.keyi.keyi_weitao_zxing.Bean.SelectData;
import com.keyi.keyi_weitao_zxing.utils.ACache;
import com.keyi.keyi_weitao_zxing.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/22.
 */
public class SelectWorkerActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private Button button, button1;
    private ACache aCache;
    private long exitTime = 0;
    private TextView header;
    private EditText editText;
    private LinearLayout linearLayout, linearLayout1;
    private RadioGroup radio;
    private ArrayList<Fragment> mFragment;
    private FragmentPagerAdapter mAdapter;
    private CustomViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectworker);
        button = (Button) findViewById(R.id.sp_test);
        button1 = (Button) findViewById(R.id.bt_position);
        header = (TextView) findViewById(R.id.tv_header);
        editText = (EditText) findViewById(R.id.editText1);
        linearLayout = (LinearLayout) findViewById(R.id.ll_select);
        linearLayout1 = (LinearLayout) findViewById(R.id.ll_select1);

        initViewPage();
        aCache = ACache.get(this);
        header.setText("剩余数量(" + Integer.parseInt(aCache.getAsString("剩余数量")) + ")");
        editText.setText(aCache.getAsString("剩余数量").toString());
        editText.setSelection(editText.getText().length());
        button.setOnClickListener(this);
        button1.setOnClickListener(this);

    }

    private void initViewPage() {
        radio = (RadioGroup) findViewById(R.id.rg_main);
        radio.setOnCheckedChangeListener(this);
        viewpager = (CustomViewPager) findViewById(R.id.view_pager);
        viewpager.setOnPageChangeListener(this);
        mFragment = new ArrayList<Fragment>();
        ShopMonthFragment month = new ShopMonthFragment(1);
        ShopMonthFragment day = new ShopMonthFragment(2);
        mFragment.add(day);
        mFragment.add(month);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragment.get(arg0);
            }
        };
        viewpager.setAdapter(mAdapter);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sp_test:
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.VISIBLE);
                editText.setEnabled(false);
                button1.setClickable(false);
                break;
            case R.id.bt_position:
                SelectData selectData = new SelectData();
                List<SelectData.WorksBean> list = new ArrayList<>();
                if (button.getText().toString().equals("点击选择工人")) {
                    Toast.makeText(this, "请先选择工人！", Toast.LENGTH_SHORT).show();
                } else {
                    if (editText.getText().toString().isEmpty()) {
                        Toast.makeText(this, "数量不能为空！", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Integer.parseInt(editText.getText().toString()) > Integer.parseInt(aCache.getAsString("剩余数量"))) {
                            new AlertDialog.Builder(SelectWorkerActivity.this).setMessage("分配数量大于剩余数量，请重新分配为!")
                                    .setPositiveButton("确定", null).show();
                        } else {
                            if (Integer.parseInt(editText.getText().toString())==0) {
                                Toast.makeText(this, "数量不能为0！", Toast.LENGTH_SHORT).show();
                            } else {
                                SelectData.WorksBean worksBean = new SelectData.WorksBean();
                                worksBean.setName(button.getText().toString());
                                worksBean.setNum(Integer.parseInt(editText.getText().toString()));
                                list.add(worksBean);
                                selectData.setWorks(list);
                                Intent intent = new Intent(SelectWorkerActivity.this, MainActivity.class);
                                intent.putExtra("intent", true);
                                intent.putExtra("workmsg", selectData);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                }

                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_main_day:
                viewpager.setCurrentItem(0);
                break;
            case R.id.rb_main_month:
                viewpager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (position) {
            case 0:
                radio.check(R.id.rb_main_day);
                break;
            case 1:
                radio.check(R.id.rb_main_month);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
