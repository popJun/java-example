package com.popjun.word.demo.policy;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.popjun.word.demo.domain.DeviceDetails.UnriskList;
import com.popjun.word.demo.policy.utils.PolicyUtils;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;

public class UnriskPolicy extends DynamicTableRenderPolicy {
    @Override
    public void render(XWPFTable table, Object data) {
        if (null == data) return;
        UnriskList rows = (UnriskList) data;
        List<RowRenderData> unriskList = rows.getUnriskList();
        if (null != rows) {
            PolicyUtils.insertList(table,unriskList,4);
        }
    }
}
