package com.popjun.word.demo.domain.DeviceDetails;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.style.TableStyle;

import java.util.Arrays;
import java.util.List;

public class QuestionList {
    private List<RowRenderData> questionList;

    public List<RowRenderData> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<RowRenderData> questionList) {
        this.questionList = questionList;
    }

    public static QuestionList creatQuestionList(TableStyle rowStyle){
        QuestionList questionList = new QuestionList();
        RowRenderData labor = RowRenderData.build("1", "前端bug", "无法弹出提示", "去除提示","10","未落实");
        labor.setStyle(rowStyle);
        List<RowRenderData> labors = Arrays.asList(labor);
        questionList.setQuestionList(labors);
        return questionList;
    }
}
