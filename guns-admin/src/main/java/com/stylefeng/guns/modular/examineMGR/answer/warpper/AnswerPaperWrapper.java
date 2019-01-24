package com.stylefeng.guns.modular.examineMGR.answer.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.modular.system.model.ExamineAnswerStateEnum;

import java.util.Map;

/**
 * 学生信息包装类
 * @author: simple.song
 * Date: 2018/10/7 Time: 10:55
 */
public class AnswerPaperWrapper extends BaseControllerWarpper{

    public AnswerPaperWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("gradeName", ConstantFactory.me().getGradeName(Integer.parseInt((String) map.get("grades"))));
        map.put("subjectName", ConstantFactory.me().getsubjectName(Integer.parseInt((String) map.get("subject"))));
        map.put("stateName", ExamineAnswerStateEnum.instanceOf(Integer.parseInt(map.get("status").toString())).text);
        map.put("studentName", ConstantFactory.me().getStudentName(map.get("student_code").toString()));
    }
}