package com.keyi.keyi_weitao_zxing.db;

import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public class LoadFalse {

    /**
     * IsOK : false
     * ErrMsg : null
     * Data : [{"ProcNo":"GS1606070173","ImptGuid":"f817ae77-724a-4340-9ebc-61e2acf3634f","Remark":"生产单: SC160607045 正在被用户:admin 操作"},{"ProcNo":"GS1606070173","ImptGuid":"3eb49a4e-e58d-4379-9aa1-dc4d2e86f312","Remark":"生产单: SC160607045 正在被用户:admin 操作"}]
     */

    private boolean IsOK;
    private Object ErrMsg;
    /**
     * ProcNo : GS1606070173
     * ImptGuid : f817ae77-724a-4340-9ebc-61e2acf3634f
     * Remark : 生产单: SC160607045 正在被用户:admin 操作
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

    public static class DataBean {
        private String ProcNo;
        private String ImptGuid;
        private String Remark;

        public String getProcNo() {
            return ProcNo;
        }

        public void setProcNo(String ProcNo) {
            this.ProcNo = ProcNo;
        }

        public String getImptGuid() {
            return ImptGuid;
        }

        public void setImptGuid(String ImptGuid) {
            this.ImptGuid = ImptGuid;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }
    }
}
