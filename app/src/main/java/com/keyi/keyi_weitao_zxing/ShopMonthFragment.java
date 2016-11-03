package com.keyi.keyi_weitao_zxing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.keyi.keyi_weitao_zxing.Bean.Worker;
import com.keyi.keyi_weitao_zxing.utils.ACache;
import com.keyi.keyi_weitao_zxing.utils.DatasUtils;
import com.keyi.keyi_weitao_zxing.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public class ShopMonthFragment extends Fragment implements AdapterView.OnItemClickListener {
    private int i;
    private View view;
    private ListView listView;
    private MyAdapter adapter;
    private List<String> list;
    private List<String> list1;

    public ShopMonthFragment(int i) {
        this.i = i;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_worker, container, false);
        listView = (ListView) view.findViewById(R.id.lv_day);
        listView.setOnItemClickListener(this);
        loading();
        return view;
    }

    //下载工人名单，设置到自定义的下拉控件
    private void loading() {
        DatasUtils datasUtils = new DatasUtils();
        final ACache aCache = ACache.get(getActivity());
        final String url = DatasUtils.workUrl + datasUtils.MobilNumber(getActivity()) + DatasUtils.strToken + datasUtils.SMSData(getActivity());
        Log.e("url", url.toString());
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
                    @Override
                    public void onSusscess(String data) {
                        Log.i("JSON", data.toString());
                        Gson gson = new Gson();
                        Worker worker = gson.fromJson(data, Worker.class);
                        aCache.put("workerSelect", worker);
                        list = new ArrayList<String>();
                        list1 = new ArrayList<String>();
                        for (int i = 0; i < worker.getData().size(); i++) {
                            list1.add(worker.getData().get(i).getRealName().toString());
                            if (worker.getData().get(i).getDefProcName() == null) {

                            } else {
                                if (worker.getData().get(i).getDefProcName().toString().equals(aCache.getAsString("工序").toString())) {
                                    list.add(worker.getData().get(i).getRealName().toString());
                                } else {

                                }
                            }
                        }
                        if (i == 1) {
                            adapter = new MyAdapter(list1);
                        } else {
                            adapter = new MyAdapter(list);
                        }
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(String meg) {
                        super.onError(meg);
                    }
                }
        );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getActivity().findViewById(R.id.ll_select).setVisibility(View.GONE);
        getActivity().findViewById(R.id.ll_select1).setVisibility(View.GONE);
        Button button = (Button) getActivity().findViewById(R.id.sp_test);
        Button button1 = (Button) getActivity().findViewById(R.id.bt_position);
        EditText editText= (EditText) getActivity().findViewById(R.id.editText1);
        editText.setEnabled(true);
        button1.setClickable(true);
        if (i==1){
            button.setText(list1.get(position));
        }else {
            button.setText(list.get(position));
        }
    }

    private class MyAdapter extends BaseAdapter {
        List<String> list;

        public MyAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convert, ViewGroup parent) {
            ViewHolder holder = null;
            if (convert == null) {
                convert = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.listview_a, parent, false);
                holder = new ViewHolder();
                holder.tv_title = (TextView) convert.findViewById(R.id.a_tv1);
                convert.setTag(holder);
            } else {
                holder = (ViewHolder) convert.getTag();
            }
            holder.tv_title.setText(list.get(position));

            return convert;
        }

        class ViewHolder {
            private TextView tv_title;

        }
    }
}
