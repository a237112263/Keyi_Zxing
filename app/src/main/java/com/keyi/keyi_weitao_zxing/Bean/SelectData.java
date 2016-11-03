package com.keyi.keyi_weitao_zxing.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public class SelectData implements Serializable{
    private static final long serialVersionUID = -145854466L;
    private List<WorksBean> Works;

    public List<WorksBean> getWorks() {
        return Works;
    }

    public void setWorks(List<WorksBean> Works) {
        this.Works = Works;
    }

    public static class WorksBean implements Serializable{
        private static final long serialVersionUID = -1974448L;
        private String Name;
        private int Num;

        public String getName() {
            return Name;
        }

        public int getNum() {
            return Num;
        }

        public void setName(String name) {
            Name = name;
        }

        public void setNum(int num) {
            Num = num;
        }
    }
}
