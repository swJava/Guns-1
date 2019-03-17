package com.stylefeng.guns.modular.examineMGR.achievement.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.examineMGR.achievement.warpper.ScoreWrapper;
import com.stylefeng.guns.modular.examineMGR.answer.warpper.AnswerPaperWrapper;
import com.stylefeng.guns.modular.memberMGR.service.IScoreService;
import com.stylefeng.guns.modular.system.model.ExamineAnswerStateEnum;
import com.stylefeng.guns.modular.system.model.Score;
import com.stylefeng.guns.util.ToolUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/17 13:49
 * @Version 1.0
 */
@Controller
@RequestMapping("/education/achievement/score")
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
        Page<Map<String, Object>> pageInfo = new PageFactory<Map<String, Object>>().defaultPage();
        Page<Map<String, Object>> pageMap = scoreService.selectMapsPage(conditionMap, pageInfo);
        //包装数据
        new ScoreWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    @RequestMapping("/template")
    public ModelAndView downloadTemplate(){
        return new ModelAndView("forward:/attachment/download?masterName=SYS_TEMPLATE&masterCode=SCORE_TEMPLATE");
    }
}
