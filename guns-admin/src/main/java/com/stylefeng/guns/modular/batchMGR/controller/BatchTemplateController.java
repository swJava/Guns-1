package com.stylefeng.guns.modular.batchMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.modular.classMGR.service.ICourseService;
import com.stylefeng.guns.modular.classRoomMGR.service.IClassroomService;
import com.stylefeng.guns.modular.system.model.Classroom;
import com.stylefeng.guns.modular.system.model.Course;
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.modular.system.service.IDictService;
import com.stylefeng.guns.modular.teacherMGR.service.TeacherService;
import com.stylefeng.guns.util.DateUtil;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/26 00:58
 * @Version 1.0
 */
@Controller
@RequestMapping("/batch/template")
public class BatchTemplateController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private IClassroomService classroomService;

    @Autowired
    private IDictService dictService;

    @RequestMapping("/class")
    public void downloadClassTemplate(HttpServletResponse response){

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("字典页");
        // 课程
        String[][] courseCodeList = generateCourseDictionary();
        // 教师辅导员
        String[][] teacherList = generateTeacherDictionary();
        // 教室
        String[][] roomList = generateClassroomDictionary();
        // 学年
        String[] academicYearList = generateAcademicYearDictionary();
        // 学期
        String[][] cycleList = generateCycleDictionary();
        // 班次
        String[][] abilityList = generateAbilityDictionary();
        // YesOrNo
        String[] yesOrNo = {"是", "否"};


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

    private String[][] generateAbilityDictionary() {

        List<Dict> dictList = dictService.selectByParentCode("ability");

        if (null == dictList || dictList.isEmpty()){
            return new String[0][0];
        }

        List<String> codeList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        for(Dict dict : dictList){
            codeList.add(dict.getCode());
            nameList.add(dict.getName());
        }

        return new String[][] {
                (String[]) codeList.toArray(),
                (String[]) nameList.toArray(),
        };
    }

    private String[][] generateCycleDictionary() {

        List<Dict> dictList = dictService.selectByParentCode("cycle");

        if (null == dictList || dictList.isEmpty()){
            return new String[0][0];
        }

        List<String> codeList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        for(Dict dict : dictList){
            codeList.add(dict.getCode());
            nameList.add(dict.getName());
        }

        return new String[][] {
                (String[]) codeList.toArray(),
                (String[]) nameList.toArray(),
        };
    }

    private String[] generateAcademicYearDictionary() {
        Date now = new Date();
        return new String[]{
                DateUtil.format(now, "yyyy"),
                DateUtil.format(DateUtil.add(now, Calendar.YEAR, 1), "yyyy"),
                DateUtil.format(DateUtil.add(now, Calendar.YEAR, 2), "yyyy"),
        };
    }

    private String[][] generateClassroomDictionary() {
        List<Classroom> rootmList = classroomService.selectList(new EntityWrapper<Classroom>(){
            {
                eq("status", GenericState.Valid.code);
            }
        });

        if (null == rootmList || rootmList.isEmpty()){
            return new String[0][0];
        }

        List<String> codeList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        for(Classroom room : rootmList){
            codeList.add(room.getCode());
            nameList.add(room.getName());
        }

        return new String[][] {
                (String[]) codeList.toArray(),
                (String[]) nameList.toArray(),
        };
    }

    private String[][] generateTeacherDictionary() {
        List<Teacher> teacherList = teacherService.selectList(new EntityWrapper<Teacher>(){
            {
                eq("status", GenericState.Valid.code);
            }
        });

        if (null == teacherList || teacherList.isEmpty()){
            return new String[0][0];
        }

        List<String> codeList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        for(Teacher teacher : teacherList){
            codeList.add(teacher.getCode());
            nameList.add(teacher.getName());
        }

        return new String[][] {
                (String[]) codeList.toArray(),
                (String[]) nameList.toArray(),
        };
    }

    private String[][] generateCourseDictionary() {
        List<Course> courseList = courseService.selectList(new EntityWrapper<Course>(){
            {
                eq("status", GenericState.Valid.code);
            }
        });

        if (null == courseList || courseList.isEmpty()){
            return new String[0][0];
        }

        List<String> codeList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        for(Course course : courseList){
            codeList.add(course.getCode());
            nameList.add(course.getName());
        }

        return new String[][] {
                (String[]) codeList.toArray(),
                (String[]) nameList.toArray(),
        };
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
