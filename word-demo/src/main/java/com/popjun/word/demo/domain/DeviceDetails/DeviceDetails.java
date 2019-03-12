package com.popjun.word.demo.domain.DeviceDetails;

import com.deepoove.poi.config.Name;

public class DeviceDetails {
    private Integer num;
    @Name("device_name")
    private String deviceName;
    @Name("question_list")
    private QuestionList questionList;
    @Name("risk_list")
    private RiskList riskList;
    @Name("confirm_list")
    private ConfirmList comfirmList;
    @Name("unrisk_list")
    private UnriskList unRiskList;
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public QuestionList getQuestionList() {
        return questionList;
    }

    public void setQuestionList(QuestionList questionList) {
        this.questionList = questionList;
    }

    public RiskList getRiskList() {
        return riskList;
    }

    public void setRiskList(RiskList riskList) {
        this.riskList = riskList;
    }

    public ConfirmList getComfirmList() {
        return comfirmList;
    }

    public void setComfirmList(ConfirmList comfirmList) {
        this.comfirmList = comfirmList;
    }

    public UnriskList getUnRiskList() {
        return unRiskList;
    }

    public void setUnRiskList(UnriskList unRiskList) {
        this.unRiskList = unRiskList;
    }

}
