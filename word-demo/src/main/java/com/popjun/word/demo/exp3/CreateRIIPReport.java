package com.popjun.word.demo.exp3;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.style.TableStyle;
import com.popjun.word.demo.domain.CheckList;
import com.popjun.word.demo.domain.DeviceDetails.*;
import com.popjun.word.demo.domain.ReportData;
import com.popjun.word.demo.domain.TestList;
import com.popjun.word.demo.policy.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateRIIPReport {
    static TableStyle rowStyle;

    static {
        rowStyle = new TableStyle();
        rowStyle.setAlign(STJc.CENTER);
    }

    private static ReportData createReportData() {
        ReportData reportData = new ReportData();
        //生成日期
        reportData.setDate("2019年02月21日");
        //生成设备总览
        CheckList checkList = CheckList.createCheckList(rowStyle);
        TestList testList = TestList.creatTestList(rowStyle);
        reportData.setCheckList(checkList);
        reportData.setTestList(testList);
        return reportData;
    }

    private static List<DeviceDetails> createDeviceDetails() {
        List<DeviceDetails> deviceDetailsList = new ArrayList<>();
        DeviceDetails deviceDetails = new DeviceDetails();
        deviceDetails.setNum(1);
        deviceDetails.setDeviceName("RG-IDP@root");
        QuestionList questionList = QuestionList.creatQuestionList(rowStyle);
        deviceDetails.setQuestionList(questionList);
        RiskList riskList = RiskList.createReskList(rowStyle);
        deviceDetails.setRiskList(riskList);
        ConfirmList confirmList = ConfirmList.creatConfirmList(rowStyle);
        deviceDetails.setComfirmList(confirmList);
        UnriskList unriskList = UnriskList.createUnriskList(rowStyle);
        deviceDetails.setUnRiskList(unriskList);
        DeviceDetails deviceDetails2 = new DeviceDetails();
        deviceDetails2.setNum(2);
        deviceDetails2.setDeviceName("RG-IDP@root11");
        deviceDetails2.setQuestionList(questionList);
        deviceDetails2.setRiskList(riskList);
        deviceDetails2.setComfirmList(confirmList);
        deviceDetails2.setUnRiskList(unriskList);
        deviceDetailsList.add(deviceDetails);
        deviceDetailsList.add(deviceDetails2);
        return deviceDetailsList;
    }

    public static Configure createConfigure() {
        Configure config = Configure.newBuilder().customPolicy("check_list", new CheckListPolicy()).build();
        config.customPolicy("question_list", new QuestionListPolicy());
        config.customPolicy("risk_list", new RiskListPolicy());
        config.customPolicy("confirm_list", new ConfirmListPolicy());
        config.customPolicy("unrisk_list", new UnriskPolicy());
        config.customPolicy("test_list", new TestListPolicy());
        return config;
    }

    public static void main(String[] args) throws IOException {
        //生成设备条目明细
        ReportData reportData = createReportData();
        List<DeviceDetails> deviceDetails = createDeviceDetails();
        DocxRenderData deviceDetailsList = new DocxRenderData(new File("F://RIIP工具巡检报告设备明细部分.docx"), deviceDetails);
        reportData.setDeviceDetails(deviceDetailsList);
        Configure configure = createConfigure();
        XWPFTemplate template = XWPFTemplate.compile("F://RIIP工具巡检报告生成模板.docx", configure).render(reportData);

        FileOutputStream out = new FileOutputStream("F://out_RIIP工具巡检报告生成模板.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }
}