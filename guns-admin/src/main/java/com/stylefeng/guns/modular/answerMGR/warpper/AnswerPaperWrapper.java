package com.stylefeng.guns.modular.answerMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

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
        map.put("gradeName", ConstantFactory.me().getGradeName((Integer) map.get("grade")));
        map.put("statusName", ConstantFactory.me().getStatusName(Integer.parseInt( map.get("status").toString())));
        map.put("studentName", ConstantFactory.me().getStudentName(map.get("student").toString()));
        map.put("examName", ConstantFactory.me().getStudyTimeTypeName(Integer.parseInt( map.get("examCode").toString())));
    }
}