package com.stylefeng.guns.modular.classMGR.controller;

import com.stylefeng.guns.core.base.tips.ErrorTip;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/14 23:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/class/batch")
public class BatchController {

    @RequestMapping("/template")
    public void downloadTemplate(HttpServletResponse response){

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("工作表");
        String[] textlist = { "列表1", "列表2", "列表3", "列表4", "列表5" };
        String[] valuelist = { "1", "2", "3", "4", "5" };

        sheet = setHSSFValidation(sheet, valuelist, 0, 1, 0, 0);// 第一列的前501行都设置为选择列表形式.
        sheet = setHSSFValidation(sheet, textlist, 0, 1, 0, 1);// 第一列的前501行都设置为选择列表形式.
        // sheetlist = setHSSFPrompt(sheetlist, "promt Title", "prompt Content",
        // 0, 500, 1, 1);// 第二列的前501行都设置提示.

        sheet.setColumnHidden(0, true);
        try {
            workbook.write(new FileOutputStream(new File("/Users/huahua/text.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet
     *            要设置的sheet.
     * @param textlist
     *            下拉框显示的内容
     * @param firstRow
     *            开始行
     * @param endRow
     *            结束行
     * @param firstCol
     *            开始列
     * @param endCol
     *            结束列
     * @return 设置好的sheet.
     */
    public static XSSFSheet setHSSFValidation(XSSFSheet sheet,
                                              String[] textlist, int firstRow, int endRow, int firstCol,
                                              int endCol) {
//        // 加载下拉列表内容
//        DVConstraint constraint = DVConstraint
//                .createExplicitListConstraint(textlist);
//        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
//        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
//                endRow, firstCol, endCol);
//        // 数据有效性对象
//        HSSFDataValidation data_validation_list = new HSSFDataValidation(
//                regions, constraint);
//
//        XSSFDataValidation data_validation_list = new XSSFDataValidation(constraint,
//                regions, constraint);
//        sheet.addValidationData(data_validation_list);
//        return sheet;

        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                .createExplicitListConstraint(textlist);
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
        validation.setSuppressDropDownArrow(true);
        validation.setShowErrorBox(true);
        sheet.addValidationData(validation);

        return sheet;
    }
}
