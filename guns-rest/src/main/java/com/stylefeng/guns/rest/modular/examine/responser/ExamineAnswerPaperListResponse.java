package com.stylefeng.guns.rest.modular.examine.responser;

import com.stylefeng.guns.rest.core.SimpleResponser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/2/20 00:01
 * @Version 1.0
 */
public class ExamineAnswerPaperListResponse extends SimpleResponser {

    private Collection<ExamineAnswerPaperResponse> datas = new ArrayList<ExamineAnswerPaperResponse>();

    public static ExamineAnswerPaperListResponse me(Set<ExamineAnswerPaperResponse> paperResponseList) {
        ExamineAnswerPaperListResponse responser = new ExamineAnswerPaperListResponse();

        responser.setCode(SUCCEED);
        responser.setMessage("查询成功");

        if (null != paperResponseList)
            responser.setDatas(paperResponseList);

        return responser;
    }

    public Collection<ExamineAnswerPaperResponse> getDatas() {
        return datas;
    }

    public void setDatas(Collection<ExamineAnswerPaperResponse> datas) {
        this.datas = datas;
    }

    public void add(ExamineAnswerPaperResponse answerPaperResponse) {
        this.datas.add(answerPaperResponse);
    }
}
