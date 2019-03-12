package com.popjun.word.demo.policy;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.popjun.word.demo.domain.DeviceDetails.ConfirmList;
import com.popjun.word.demo.policy.utils.PolicyUtils;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;

public class ConfirmListPolicy  extends DynamicTableRenderPolicy {
    @Override
    public void render(XWPFTable table, Object data) {
        if (null == data) return;
        ConfirmList rows = (ConfirmList) data;
        List<RowRenderData> confirmList = rows.getConfirmList();
        if (null != rows) {
            PolicyUtils.insertList(table,confirmList,5);
        }
    }
}
