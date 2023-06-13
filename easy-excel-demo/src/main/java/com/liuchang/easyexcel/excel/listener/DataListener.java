package com.liuchang.easyexcel.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.liuchang.easyexcel.excel.domain.RegResult;
import com.liuchang.easyexcel.excel.domain.ReportInfo;
import com.liuchang.easyexcel.excel.domain.StudentInfo;
import com.liuchang.easyexcel.excel.util.RegUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * @author Will Liu
 * @date 2022/4/13
 */
@NoArgsConstructor
@AllArgsConstructor
public class DataListener extends AnalysisEventListener<StudentInfo> {

    private List<ReportInfo> reportInfoList;

    @Override
    public void invoke(StudentInfo data, AnalysisContext context) {
        String studentName = data.getStudentName();
        String studentIdCard = data.getStudentIdCard();
        String personInfo1 = data.getPersonInfo1();
        Optional<RegResult> result1 = RegUtil.getResult(personInfo1, studentName);
        processPerson(studentName, studentIdCard, result1);
        String personInfo2 = data.getPersonInfo2();
        Optional<RegResult> result2 = RegUtil.getResult(personInfo2, studentName);
        processPerson(studentName, studentIdCard, result2);
        String personInfo3 = data.getPersonInfo3();
        Optional<RegResult> result3 = RegUtil.getResult(personInfo3, studentName);
        processPerson(studentName, studentIdCard, result3);
        String personInfo4 = data.getPersonInfo4();
        Optional<RegResult> result4 = RegUtil.getResult(personInfo4, studentName);
        processPerson(studentName, studentIdCard, result4);
        String personInfo5 = data.getPersonInfo5();
        Optional<RegResult> result5 = RegUtil.getResult(personInfo5, studentName);
        processPerson(studentName, studentIdCard, result5);
        String personInfo6 = data.getPersonInfo6();
        Optional<RegResult> result6 = RegUtil.getResult(personInfo6, studentName);
        processPerson(studentName, studentIdCard, result6);
    }

    private void processPerson(String studentName, String studentIdCard, Optional<RegResult> result) {
        if (result.isPresent()) {
            RegResult person = result.get();
            if (!studentName.equals(person.getName())) {
                reportInfoList.add(new ReportInfo(studentName, studentIdCard, person.getName(), person.getIdCard()));
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
