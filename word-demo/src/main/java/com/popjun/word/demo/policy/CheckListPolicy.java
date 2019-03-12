package com.popjun.word.demo.policy;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import com.popjun.word.demo.domain.CheckList;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public  class CheckListPolicy  extends DynamicTableRenderPolicy {
    int insertPos = 1;
    @Override
    public void render(XWPFTable table, Object data) {
        if (null == data) return;
        CheckList rows = (CheckList) data;
        List<RowRenderData> checkData = rows.getCheckList();
        if (null != rows) {
            table.removeRow(insertPos);
            for (int i = 0; i < checkData.size(); i++){
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(insertPos);
                for (int j = 0; j < 4; j++) insertNewTableRow.createCell();
                MiniTableRenderPolicy.renderRow(table, insertPos, checkData.get(i));
                if ((i+1)%6==0){
                    //合并列
                    TableTools.mergeCellsVertically(table,0,1,6 );
                    TableTools.mergeCellsVertically(table,1, 1,6 );
                }
            }
        }
    }
}
