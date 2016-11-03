package com.keyi.keyi_weitao_zxing.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19.
 */
public class Worker implements Serializable{

    private static final long serialVersionUID = -1465444121L;

    private boolean IsOK;
    private Object ErrMsg;

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
        private static final long serialVersionUID = -1577981156L;
        private String RealName;
        private String DefProcName;
        private Object Degree;
        private Object DepName;
        private Object JobNumber;
        private Object Mobile;

        public String getRealName() {
            return RealName;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }

        public String getDefProcName() {
            return DefProcName;
        }

        public void setDefProcName(String DefProcName) {
            this.DefProcName = DefProcName;
        }

        public Object getDegree() {
            return Degree;
        }

        public void setDegree(Object Degree) {
            this.Degree = Degree;
        }

        public Object getDepName() {
            return DepName;
        }

        public void setDepName(Object DepName) {
            this.DepName = DepName;
        }

        public Object getJobNumber() {
            return JobNumber;
        }

        public void setJobNumber(Object JobNumber) {
            this.JobNumber = JobNumber;
        }

        public Object getMobile() {
            return Mobile;
        }

        public void setMobile(Object Mobile) {
            this.Mobile = Mobile;
        }
    }
}
