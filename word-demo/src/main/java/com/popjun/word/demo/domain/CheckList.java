package com.popjun.word.demo.domain;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.style.TableStyle;

import java.util.Arrays;
import java.util.List;

public class CheckList {
    List<RowRenderData> checkList;

    public List<RowRenderData> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<RowRenderData> checkList) {
        this.checkList = checkList;
    }
    public static CheckList createCheckList(TableStyle rowStyle){
        CheckList checkList = new CheckList();
        RowRenderData labor = RowRenderData.build("1", "RG-IDP@root", "得分", "90");
        labor.setStyle(rowStyle);
        List<RowRenderData> labors = Arrays.asList(labor, labor, labor, labor, labor, labor, labor, labor, labor, labor, labor, labor,labor, labor, labor, labor, labor, labor,labor, labor, labor, labor, labor, labor,labor, labor, labor, labor, labor, labor, labor, labor, labor, labor, labor, labor,labor, labor, labor, labor, labor, labor,labor, labor, labor, labor, labor, labor,labor, labor, labor, labor, labor, labor, labor, labor, labor, labor, labor, labor,labor, labor, labor, labor, labor, labor,labor, labor, labor, labor, labor, labor);
        checkList.setCheckList(labors);
        return checkList;
    }
}
