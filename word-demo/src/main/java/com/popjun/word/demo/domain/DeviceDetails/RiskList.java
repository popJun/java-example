package com.popjun.word.demo.domain.DeviceDetails;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.style.TableStyle;

import java.util.Arrays;
import java.util.List;

public class RiskList {
    private List<RowRenderData> riskList;

    public List<RowRenderData> getRiskList() {
        return riskList;
    }

    public void setRiskList(List<RowRenderData> riskList) {
        this.riskList = riskList;
    }

    public static RiskList createReskList(TableStyle rowStyle){
        RiskList riskList = new RiskList();
        RowRenderData labor = RowRenderData.build("1", "前端bug", "无法弹出提示", "去除提示","10","未落实");
        labor.setStyle(rowStyle);
        List<RowRenderData> labors = Arrays.asList(labor);
        riskList.setRiskList(labors);
        return riskList;
    }
}
