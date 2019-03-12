package com.popjun.word.demo.domain;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.style.TableStyle;

import java.util.Arrays;
import java.util.List;

public class TestList {
    List<RowRenderData> testList;

    public List<RowRenderData> getTestList() {
        return testList;
    }

    public void setTestList(List<RowRenderData> testList) {
        this.testList = testList;
    }

    public static TestList creatTestList(TableStyle rowStyle){
        TestList testList = new TestList();
        RowRenderData labor = RowRenderData.build("1", "前端bug", "无法弹出提示", "去除提示","10","未落实");
        labor.setStyle(rowStyle);
        List<RowRenderData> labors = Arrays.asList(labor,labor,labor,labor,labor,labor,labor,labor,labor,labor,labor);
        testList.setTestList(labors);
        return testList;
    }
}
