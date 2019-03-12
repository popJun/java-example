package com.popjun.word.demo.domain.DeviceDetails;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.style.TableStyle;

import java.util.Arrays;
import java.util.List;

public class ConfirmList {
    private List<RowRenderData> confirmList;

    public List<RowRenderData> getConfirmList() {
        return confirmList;
    }

    public void setConfirmList(List<RowRenderData> confirmList) {
        this.confirmList = confirmList;
    }

    public static ConfirmList creatConfirmList(TableStyle rowStyle){
        ConfirmList confirmList = new ConfirmList();
        RowRenderData labor = RowRenderData.build("1", "前端bug", "无法弹出提示", "去除提示","10");
        labor.setStyle(rowStyle);
        List<RowRenderData> labors = Arrays.asList(labor);
        confirmList.setConfirmList(labors);
        return confirmList;
    }
}
