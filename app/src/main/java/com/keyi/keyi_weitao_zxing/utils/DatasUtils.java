package com.keyi.keyi_weitao_zxing.utils;

import android.content.Context;

import com.keyi.keyi_weitao_zxing.Bean.SMSData;


/**
 * Created by Administrator on 2016/4/27.
 */
public class DatasUtils {
    public static final String strToken="&Token=";
    public static final String yanzhengUrl="http://appweb.keyierp.com/sms.aspx?m=";
    public static final String mobUrl="http://appweb.keyierp.com/ERP/Login.aspx?mobile=";
    public static final String workUrl="http://jxapp.keyierp.com/GC/GetWorker.aspx?mobile=";
    public static final String upLoadingMsg="http://jxapp.keyierp.com/GC/InsertWorkerMsg.aspx?mobile=";
    public static final String doneNumberUrl="http://jxapp.keyierp.com/GC/GetDoneNumber.aspx?mobile=";

    public String SMSData(Context context){
        ACache aCache=ACache.get(context);
        SMSData smsData= (SMSData) aCache.getAsObject("SMSData");
        return smsData.getData().toString();
    }

    public String MobilNumber(Context context){
        ACache aCache=ACache.get(context);
        return aCache.getAsString("MobilNumber").toString();
    }
}
