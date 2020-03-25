package com.example.together_demo1.Bean;

import org.litepal.crud.LitePalSupport;

public class MyCheck extends LitePalSupport {
    private String carid;
    private Integer changmoney;
    private String operator;
    private String updatetime;

    public MyCheck(String carid, Integer changmoney, String operator, String updatetime) {
        this.carid = carid;
        this.changmoney = changmoney;
        this.operator = operator;
        this.updatetime = updatetime;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public Integer getChangmoney() {
        return changmoney;
    }

    public void setChangmoney(Integer changmoney) {
        this.changmoney = changmoney;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
