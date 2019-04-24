package com.stylefeng.guns.modular.examineMGR.achievement.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.memberMGR.service.IScoreService;
import com.stylefeng.guns.modular.system.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/17 13:49
 * @Version 1.0
 */
@Controller
@RequestMapping("/examine/achievement/score")
public class ScoreController extends BaseController {

    private static final String PREFIX = "/examineMGR/achievement/";

    @Autowired
    private IScoreService scoreService;


    /**
     * 跳转到试卷首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "score.html";
    }

    /**
     * 获取成绩列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam Map<String, Object> conditionMap) {
        //分页查詢
        Map<String, Object> queryParams = buildOurQueryArguments(conditionMap);
        Page<Score> page = new PageFactory<Score>().defaultPage();

        Page<Score> scorePage = scoreService.selectPage(queryParams, page);
        //包装数据
        //包装数据
        return super.packForBT(scorePage);
    }

    @RequestMapping("/template")
    public ModelAndView downloadTemplate(){
        return new ModelAndView("forward:/attachment/download?masterName=SYS_TEMPLATE&masterCode=SCORE_TEMPLATE");
    }


    private Map<String, Object> buildOurQueryArguments(Map<String, Object> queryParams) {

        Map<String, Object> arguments = super.buildQueryArguments(queryParams);

        Iterator<String> queryKeyIter = queryParams.keySet().iterator();

        List<String> studentList = new ArrayList<>();
        arguments.put("studentList", studentList);

        while(queryKeyIter.hasNext()){
            String key = queryKeyIter.next();
            Object value = queryParams.get(key);

            if ("student".equals(key)){
                studentList.add((String) value);

                arguments.remove("student");
            }
        }
        return arguments;
    }
}
