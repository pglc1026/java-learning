package com.liuchang.easyexcel.excel.processor;

import com.alibaba.excel.EasyExcel;
import com.liuchang.easyexcel.excel.domain.ReportInfo;
import com.liuchang.easyexcel.excel.domain.StudentInfo;
import com.liuchang.easyexcel.excel.listener.DataListener;
import com.liuchang.easyexcel.excel.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Will Liu
 * @date 2022/4/13
 */
public class ExcelProcessor {

    public static void main(String[] args) {
        List<ReportInfo> reportInfoList = new ArrayList<>();
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
//        String fileName = FileUtil.getPath() + "高二.xlsx";
        String fileName = FileUtil.getPath() + "【共同居住者统计】高一.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, StudentInfo.class, new DataListener(reportInfoList)).sheet().doRead();
        EasyExcel.read(fileName, StudentInfo.class, new DataListener(reportInfoList)).sheet("Sheet2").doRead();

        // 写法1
        String writeFileName = FileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(writeFileName, ReportInfo.class).sheet("sheet1").doWrite(reportInfoList);
    }

}
