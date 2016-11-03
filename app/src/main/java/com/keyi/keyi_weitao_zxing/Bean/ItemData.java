package com.keyi.keyi_weitao_zxing.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/2.
 */
public class ItemData implements Serializable {
    private static final long serialVersionUID = -468746461L;
    private String keyName;
    private String number;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
