package com.stylefeng.guns.modular.memberMGR.service;

import com.stylefeng.guns.modular.system.model.Member;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-09
 */
public interface IMemberService extends IService<Member> {
    /**
     * 新增用户
     *
     * @param stringObjectMap
     * @param userName
     * @param password
     * @return
     */
    Member createMember(String userName, String password, Map<String, Object> extendParams);

    /**
     * 生成用户名
     *
     * @return
     */
    String generateUserName();

    /**
     * 创建用户
     *  @param userName
     * @param extraParams
     */
    Member createMember(String userName, Map<String, Object> extraParams);

    /**
     * 修改密码
     * @param userName
     * @param password
     */
    void changePassword(String userName, String password);
}
