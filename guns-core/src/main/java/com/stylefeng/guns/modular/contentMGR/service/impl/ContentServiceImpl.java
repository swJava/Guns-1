package com.stylefeng.guns.modular.contentMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.modular.system.dao.ContentMapper;
import com.stylefeng.guns.modular.contentMGR.service.IContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public Content get(String code) {
        if (null == code)
            return null;

        Wrapper<Content> queryContent = new EntityWrapper<Content>();
        queryContent.eq("code", code);

        return selectOne(queryContent);
    }
}
