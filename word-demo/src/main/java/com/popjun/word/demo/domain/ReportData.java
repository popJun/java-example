package com.popjun.word.demo.domain;

import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.DocxRenderData;

public class ReportData {
    private String date;
    @Name("check_list")
    private CheckList checkList;
    @Name("device_details")
    private DocxRenderData deviceDetails;
    @Name("test_list")
    private TestList testList;
    public CheckList getCheckList() {
        return checkList;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DocxRenderData getDeviceDetails() {
        return deviceDetails;
    }

    public void setDeviceDetails(DocxRenderData deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public TestList getTestList() {
        return testList;
    }

    public void setTestList(TestList testList) {
        this.testList = testList;
    }

}
