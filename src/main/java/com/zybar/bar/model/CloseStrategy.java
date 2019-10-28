package com.zybar.bar.model;



public class CloseStrategy {
    
    private Integer closeStrategyId;
    private String closeOpera;
    private String ifLoss;
    private String closeDelegateType;
    private String closePoint;
    private String remark;
    private String closeTime;
    private Integer createStrategyId;

    public Integer getCreateStrategyId() {
        return createStrategyId;
    }

    public void setCreateStrategyId(Integer creatStrategyId) {
        this.createStrategyId = creatStrategyId;
    }

    public Integer getCloseStrategyId() {
        return closeStrategyId;
    }

    
    public void setCloseStrategyId(Integer closeStrategyId) {
        this.closeStrategyId = closeStrategyId;
    }

    
    public String getCloseOpera() {
        return closeOpera;
    }

    
    public void setCloseOpera(String closeOpera) {
        this.closeOpera = closeOpera;
    }

    
    public String getIfLoss() {
        return ifLoss;
    }

    
    public void setIfLoss(String ifLoss) {
        this.ifLoss = ifLoss;
    }

    
    public String getCloseDelegateType() {
        return closeDelegateType;
    }

    
    public void setCloseDelegateType(String closeDelegateType) {
        this.closeDelegateType = closeDelegateType;
    }

    
    public String getClosePoint() {
        return closePoint;
    }

    
    public void setClosePoint(String closePoint) {
        this.closePoint = closePoint;
    }

    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    public String getCloseTime() {
        return closeTime;
    }


    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }


}