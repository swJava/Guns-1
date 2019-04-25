package com.stylefeng.guns.rest.modular.examine.responser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/2/19 23:59
 * @Version 1.0
 */
@ApiModel(value = "ExamineAnswerPaperResponse", description = "试卷")
public class ExamineAnswerPaperResponse implements Serializable {
    @ApiModelProperty(name = "paperCode", value = "试卷编码", example = "SJ000001")
    private String paperCode;
    @ApiModelProperty(name = "className", value = "班级描述", example = "重庆2018寒假班小学三年级语文")
    private String className;
    @ApiModelProperty(name = "classAbility", value = "可报名班型", example = "敏学班、勤思班")
    private String classAbility;
    @ApiModelProperty(name = "examTime", value = "诊断时长，分钟", example = "40")
    private Integer examTime;
    @ApiModelProperty(name = "status", value = "状态： 0 从未测试； 1 正在答题； 2 暂停答题  3 已交卷  4 已批改", example = "1")
    private Integer status;
    @ApiModelProperty(name = "answerResponse", value = "历史答卷信息")
    private AnswerResponse answerResponse;
    @ApiModelProperty(name = "canTest", value = "能否开始诊断", example = "true")
    private boolean canTest;

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassAbility() {
        return classAbility;
    }

    public void setClassAbility(String classAbility) {
        this.classAbility = classAbility;
    }

    public Integer getExamTime() {
        return examTime;
    }

    public void setExamTime(Integer examTime) {
        this.examTime = examTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AnswerResponse getAnswerResponse() {
        return answerResponse;
    }

    public void setAnswerResponse(AnswerResponse answerResponse) {
        this.answerResponse = answerResponse;
    }

    public boolean isCanTest() {
        return canTest;
    }

    public void setCanTest(boolean canTest) {
        this.canTest = canTest;
    }

    public static ExamineAnswerPaperResponse me(Map<String, Object> examineAnswerPaper) {

        ExamineAnswerPaperResponse response = new ExamineAnswerPaperResponse();

        response.setPaperCode((String)examineAnswerPaper.get("paperCode"));
        response.setClassName((String)examineAnswerPaper.get("className"));
        response.setClassAbility((String)examineAnswerPaper.get("ability"));
        response.setStatus((Integer)examineAnswerPaper.get("status"));
        response.setExamTime((Integer)examineAnswerPaper.get("examTime"));

        return response;
    }
}