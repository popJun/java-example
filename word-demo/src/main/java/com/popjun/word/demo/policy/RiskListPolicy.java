package com.popjun.word.demo.policy;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.popjun.word.demo.domain.DeviceDetails.RiskList;
import com.popjun.word.demo.policy.utils.PolicyUtils;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;

public class RiskListPolicy extends DynamicTableRenderPolicy {

    @Override
    public void render(XWPFTable table, Object data) {
        if (null == data) return;
        RiskList rows = (RiskList) data;
        List<RowRenderData> reskList = rows.getRiskList();
        if (null != rows) {
           PolicyUtils.insertList(table,reskList,6);
        }
    }
}
