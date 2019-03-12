package com.popjun.word.demo.policy;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.popjun.word.demo.domain.DeviceDetails.QuestionList;
import com.popjun.word.demo.policy.utils.PolicyUtils;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;

public class QuestionListPolicy extends DynamicTableRenderPolicy {
    int insertPos = 1;
    @Override
    public void render(XWPFTable table, Object data) {
        if (null == data) return;
        QuestionList rows = (QuestionList) data;
        List<RowRenderData> questionList = rows.getQuestionList();
        if (null != rows) {
            PolicyUtils.insertList(table,questionList,6);
        }
    }
}
