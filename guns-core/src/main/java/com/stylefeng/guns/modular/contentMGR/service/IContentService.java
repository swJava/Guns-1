package com.stylefeng.guns.modular.contentMGR.service;

import com.stylefeng.guns.modular.system.model.Content;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 内容 服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-25
 */
public interface IContentService extends IService<Content> {

    /**
     * 根据编码获取内容
     *
     * @param contentCode
     * @return
     */
    Content get(String contentCode);
}
