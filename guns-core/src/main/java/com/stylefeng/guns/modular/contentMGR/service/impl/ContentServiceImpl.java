package com.stylefeng.guns.modular.contentMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.modular.system.dao.ContentMapper;
import com.stylefeng.guns.modular.contentMGR.service.IContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.util.CodeKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 内容 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-25
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public Content get(String code) {
        if (null == code)
            return null;

        Wrapper<Content> queryContent = new EntityWrapper<Content>();
        queryContent.eq("code", code);

        return selectOne(queryContent);
    }

    @Override
    public void create(Content content) {

        content.setCode(CodeKit.generateContent());

        insert(content);

    }

    @Override
    public List<Content> findArticle(String column) {

        Map<String, Object> queryMap = new HashMap<String , Object>();
        queryMap.put("column", column);

        List<Content> resultList = contentMapper.selectByColumn(queryMap);

        return resultList;
    }

    @Override
    public List<Content> findArticleOutline(String column) {
        Map<String, Object> queryMap = new HashMap<String , Object>();
        queryMap.put("column", column);

        List<Content> resultList = contentMapper.selectOutlineByColumn(queryMap);

        return resultList;
    }
}
