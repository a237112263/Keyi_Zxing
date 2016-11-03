package com.keyi.keyi_weitao_zxing.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
public class DoneNumber1 implements Serializable{
    private static final long serialVersionUID = -579878879L;
    /**
     * IsOK : true
     * ErrMsg : null
     * Data : {"PrdNo":"SC1606300198","SysTradeNo":"DD1606300163","list":[{"GSNo":"GS1606300833","DoneNum":3,"ProcHalfNum":0,"ProcNum":5,"RemainNum":2,"ProcName":"打包装","ERP_OuterIid":"3224#","ERP_OuterSkuId":"3224#乳胶款蓝灰色1+1+3"},{"GSNo":"GS1606300834","DoneNum":3,"ProcHalfNum":0,"ProcNum":5,"RemainNum":2,"ProcName":"打包装","ERP_OuterIid":"81-2#","ERP_OuterSkuId":"81-2#邮票布1+2+3"}]}
     */

    private boolean IsOK;
    private Object ErrMsg;
    /**
     * PrdNo : SC1606300198
     * SysTradeNo : DD1606300163
     * list : [{"GSNo":"GS1606300833","DoneNum":3,"ProcHalfNum":0,"ProcNum":5,"RemainNum":2,"ProcName":"打包装","ERP_OuterIid":"3224#","ERP_OuterSkuId":"3224#乳胶款蓝灰色1+1+3"},{"GSNo":"GS1606300834","DoneNum":3,"ProcHalfNum":0,"ProcNum":5,"RemainNum":2,"ProcName":"打包装","ERP_OuterIid":"81-2#","ERP_OuterSkuId":"81-2#邮票布1+2+3"}]
     */

    private DataBean Data;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        private static final long serialVersionUID = -51234646L;
        private String PrdNo;
        private String SysTradeNo;
        /**
         * GSNo : GS1606300833
         * DoneNum : 3
         * ProcHalfNum : 0
         * ProcNum : 5
         * RemainNum : 2
         * ProcName : 打包装
         * ERP_OuterIid : 3224#
         * ERP_OuterSkuId : 3224#乳胶款蓝灰色1+1+3
         */

        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            private static final long serialVersionUID = -58125546L;
            private String GSNo;
            private int DoneNum;
            private int ProcHalfNum;
            private int ProcNum;
            private int RemainNum;
            private String ProcName;
            private String ERP_OuterIid;
            private String ERP_OuterSkuId;

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

            public String getProcName() {
                return ProcName;
            }

            public void setProcName(String ProcName) {
                this.ProcName = ProcName;
            }

            public String getERP_OuterIid() {
                return ERP_OuterIid;
            }

            public void setERP_OuterIid(String ERP_OuterIid) {
                this.ERP_OuterIid = ERP_OuterIid;
            }

            public String getERP_OuterSkuId() {
                return ERP_OuterSkuId;
            }

            public void setERP_OuterSkuId(String ERP_OuterSkuId) {
                this.ERP_OuterSkuId = ERP_OuterSkuId;
            }
        }
    }
}
