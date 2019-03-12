package com.popjun.word.demo.domain.DeviceDetails;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.style.TableStyle;

import java.util.Arrays;
import java.util.List;

public class UnriskList {
    private List<RowRenderData> unriskList;

    public List<RowRenderData> getUnriskList() {
        return unriskList;
    }

    public void setUnriskList(List<RowRenderData> unriskList) {
        this.unriskList = unriskList;
    }

    public static UnriskList createUnriskList(TableStyle rowStyle){
        UnriskList unriskList = new UnriskList();
        RowRenderData labor = RowRenderData.build("1", "前端bug", "无法弹出提示", "去除提示");
        labor.setStyle(rowStyle);
        List<RowRenderData> labors = Arrays.asList(labor);
        unriskList.setUnriskList(labors);
        return unriskList;
    }
}
