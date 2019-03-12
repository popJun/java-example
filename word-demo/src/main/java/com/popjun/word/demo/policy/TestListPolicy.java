package com.popjun.word.demo.policy;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.popjun.word.demo.domain.TestList;
import com.popjun.word.demo.policy.utils.PolicyUtils;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;

public class TestListPolicy extends DynamicTableRenderPolicy {
    @Override
    public void render(XWPFTable table, Object data) {
        if (null == data) return;
        TestList rows = (TestList) data;
        List<RowRenderData> testList = rows.getTestList();
        if (null != rows) {
            PolicyUtils.insertList(table,testList,5);
        }

    }
}
