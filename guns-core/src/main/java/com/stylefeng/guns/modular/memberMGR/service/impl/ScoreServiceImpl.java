package com.stylefeng.guns.modular.memberMGR.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.modular.memberMGR.service.IScoreService;
import com.stylefeng.guns.modular.system.dao.ScoreMapper;
import com.stylefeng.guns.modular.system.model.Score;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查分
 *
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/16 14:29
 * @Version 1.0
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public Page<Score> selectPage(Map<String, Object> queryParams, Page<Score> page) {

        if (null == page)
            page = new PageFactory<Score>().defaultPage();

        List<Score> resultList = scoreMapper.selectPageList(page, queryParams);

        page.setRecords(resultList);

        return page;
    }

    @Override
    public Page<Map<String, Object>> selectMapsPage(Map<String, Object> conditionMap, Page<Map<String, Object>> pageInfo) {

        Page<Score> queryPageInfo = new PageFactory<Score>().defaultPage();
        Page<Score> resultPage = selectPage(conditionMap, queryPageInfo);

        List<Map<String, Object>> resultList = new ArrayList<>();
        for(Score result : resultPage.getRecords()){
            Map<String, Object> resultMap = new HashMap<>();
            try {
                BeanUtils.populate(result, resultMap);
            } catch (Exception e) {
                continue;
            }

            resultList.add(resultMap);
        }

        pageInfo.setRecords(resultList);
        pageInfo.setTotal(queryPageInfo.getTotal());
        pageInfo.setCurrent(queryPageInfo.getCurrent());
        pageInfo.setSize(queryPageInfo.getSize());
        return pageInfo;
    }
}
