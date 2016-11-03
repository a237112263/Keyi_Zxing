package com.keyi.keyi_weitao_zxing.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
public class DoneNumber implements Serializable{
    private static final long serialVersionUID = -579884646L;


    /**
     * IsOK : true
     * ErrMsg : null
     * Data : [{"GSNo":"GS1606070173","DoneNum":0,"ProcHalfNum":0,"ProcNum":1,"RemainNum":1,"PrdNo":"SC160607045","SysTradeNo":"DD160607044"}]
     */

    private boolean IsOK;
    private Object ErrMsg;
    /**
     * GSNo : GS1606070173
     * DoneNum : 0
     * ProcHalfNum : 0
     * ProcNum : 1
     * RemainNum : 1
     * PrdNo : SC160607045
     * SysTradeNo : DD160607044
     */

    private List<DataBean> Data;

    public boolean isIsOK() {
        return IsOK;
    }

    public void setIsOK(boolean IsOK) {
        this.IsOK = IsOK;
    }

    public Object getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(Object ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        private static final long serialVersionUID = -57660846L;
        private String GSNo;
        private int DoneNum;
        private int ProcHalfNum;
        private int ProcNum;
        private int RemainNum;
        private String PrdNo;
        private String SysTradeNo;

        public String getGSNo() {
            return GSNo;
        }

        public void setGSNo(String GSNo) {
            this.GSNo = GSNo;
        }

        public int getDoneNum() {
            return DoneNum;
        }

        public void setDoneNum(int DoneNum) {
            this.DoneNum = DoneNum;
        }

        public int getProcHalfNum() {
            return ProcHalfNum;
        }

        public void setProcHalfNum(int ProcHalfNum) {
            this.ProcHalfNum = ProcHalfNum;
        }

        public int getProcNum() {
            return ProcNum;
        }

        public void setProcNum(int ProcNum) {
            this.ProcNum = ProcNum;
        }

        public int getRemainNum() {
            return RemainNum;
        }

        public void setRemainNum(int RemainNum) {
            this.RemainNum = RemainNum;
        }

        public String getPrdNo() {
            return PrdNo;
        }

        public void setPrdNo(String PrdNo) {
            this.PrdNo = PrdNo;
        }

        public String getSysTradeNo() {
            return SysTradeNo;
        }

        public void setSysTradeNo(String SysTradeNo) {
            this.SysTradeNo = SysTradeNo;
        }
    }
}
