package com.popjun.word.demo.policy.utils;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public class PolicyUtils {
      public static void insertList(XWPFTable table, List<RowRenderData> list,int col){
          int insertPos = 1;
          for (int i = 0; i < list.size(); i++){
              XWPFTableRow insertNewTableRow = table.insertNewTableRow(insertPos);
              for (int j = 0; j < col; j++) insertNewTableRow.createCell();
              MiniTableRenderPolicy.renderRow(table, insertPos, list.get(i));
          }
      }

}
