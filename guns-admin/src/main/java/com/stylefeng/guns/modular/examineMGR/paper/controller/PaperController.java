package com.stylefeng.guns.modular.examineMGR.paper.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.constant.state.YesOrNoState;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.admin.Administrator;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.examineMGR.paper.warpper.PaperWrapper;
import com.stylefeng.guns.modular.examineMGR.service.IExaminePaperService;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionService;
import com.stylefeng.guns.modular.examineMGR.service.impl.ExaminePaperServiceImpl;
import com.stylefeng.guns.modular.questionMGR.warpper.QuestionWrapper;
import com.stylefeng.guns.modular.system.model.ExaminePaper;
import com.stylefeng.guns.modular.system.model.ExaminePaperItem;
import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.modular.system.model.QuestionItem;
import com.stylefeng.guns.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static com.stylefeng.guns.common.constant.factory.MutiStrFactory.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/22 15:54
 * @Version 1.0
 */
@Controller
@RequestMapping("/examine/paper")
public class PaperController extends BaseController {
    private String PREFIX = "/examineMGR/paper/";

    @Autowired
    private IExaminePaperService examinePaperService;

    @Autowired
    private IQuestionService questionService;

    /**
     * 跳转到试卷首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "paper.html";
    }

    /**
     * 跳转到添加入学诊断
     */
    @RequestMapping("/paper_add")
    public String questionAdd( Model model ) {
        model.addAttribute("code", UUID.randomUUID().toString().replaceAll("-", ""));
        return PREFIX + "paper_add.html";
    }

    /**
     * 跳转到修改入学诊断
     */
    @RequestMapping("/paper_update/{code}")
    public String questionUpdate(@PathVariable String code, Model model) {
        ExaminePaper paper = examinePaperService.get(code);

        if (null == paper)
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);

        model.addAttribute("item", paper);

        LogObjectHolder.me().set(paper);
        return PREFIX + "paper_edit.html";
    }

    /**
     * 获取入学诊断列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam Map<String, Object> queryMap) {
        //分页查詢
        Page<ExaminePaper> page = new PageFactory<ExaminePaper>().defaultPage();
        Page<Map<String, Object>> pageMap = examinePaperService.selectMapsPage(page, new EntityWrapper<ExaminePaper>() {{
            eq("status", GenericState.Valid.code);
        }});
        //包装数据
        new PaperWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }


    /**
     * 获取入学诊断列表
     */
    @RequestMapping(value = "/question/list")
    @ResponseBody
    public Object questionList(@RequestParam Map<String, Object> conditionMap, String workingCodes) {
        Set<String> workingQuestionList = new HashSet<String>();
        if (ToolUtil.isNotEmpty(workingCodes)){
            StringTokenizer codeIter = new StringTokenizer(workingCodes, ",");
            while(codeIter.hasMoreTokens()){
                workingQuestionList.add(codeIter.nextToken());
            }
        }
        //分页查詢
        Page<Map<String, Object>> pageMap = questionService.selectMapsPage(conditionMap, workingQuestionList);
        //包装数据
        new QuestionWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 新增入学诊断
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ExaminePaper paper, String paperItems) {
        if (ToolUtil.isOneEmpty(paper, paperItems)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        //解析
        Set<ExaminePaperItem> workingQuestionList = new HashSet<ExaminePaperItem>();
        if (ToolUtil.isNotEmpty(paperItems)){
            StringTokenizer codeIter = new StringTokenizer(paperItems, ";");
            while(codeIter.hasMoreTokens()){
                ExaminePaperItem paperItem = new ExaminePaperItem();
                String[] itemMap = codeIter.nextToken().split("=");
                paperItem.setQuestionCode(itemMap[0]);
                paperItem.setScore(itemMap[1]);
                workingQuestionList.add(paperItem);
            }
        }

        Administrator currAdmin = ShiroKit.getUser();
        paper.setTeacher(currAdmin.getName());

        examinePaperService.create(paper, workingQuestionList);

        return SUCCESS_TIP;
    }

}
