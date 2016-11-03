package com.keyi.keyi_weitao_zxing.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class ModelJson {
    private String DeviceId;
    private String Mobile;
    private String ProcNo;

    private List<WorksBean> Works;

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getProcNo() {
        return ProcNo;
    }

    public void setProcNo(String ProcNo) {
        this.ProcNo = ProcNo;
    }
    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String DeviceId) {
        this.DeviceId = DeviceId;
    }

    public List<WorksBean> getWorks() {
        return Works;
    }

    public void setWorks(List<WorksBean> Works) {
        this.Works = Works;
    }

    public static class WorksBean {
        private String RealName;
        private int Num;

        public String getRealName() {
            return RealName;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }

        public int getNum() {
            return Num;
        }

        public void setNum(int Num) {
            this.Num = Num;
        }
    }
}
