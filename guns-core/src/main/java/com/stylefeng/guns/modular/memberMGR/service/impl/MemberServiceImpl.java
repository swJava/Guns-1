package com.stylefeng.guns.modular.memberMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.memberMGR.service.IMemberService;
import com.stylefeng.guns.modular.system.dao.MemberMapper;
import com.stylefeng.guns.modular.system.model.Member;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-09
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Override
    public Member createMember(String userName, String password, Map<String, Object> extraParams) {

        if (null == userName)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        Member existMember = (Member) selectObj(new EntityWrapper<Member>().eq("username", userName));

        if (null != existMember){
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_DUPLICATE);
        }

        Member member = new Member();

        member.setUserName(userName);
        member.setPassword(Sha256Hash.toString(password.getBytes()));

        buildMemberInfo(member, extraParams);

        insert(member);

        return member;
    }

    private void buildMemberInfo(Member member, Map<String, Object> extraParams) {
        Iterator<String> keyIter = extraParams.keySet().iterator();
        member.setMobileNumber(member.getUserName());
        while(keyIter.hasNext()){
            String key = keyIter.next();
            if ("address".equals(key)){
                member.setAddress(getString(extraParams, key));
            }

            if ("email".equals(key)) {
                member.setEmail(getString(extraParams, key));
            }

            if ("gender".equals(key)) {
                member.setGender(getInteger(extraParams, key));
            }

            if ("name".equals(key)){
                member.setName(getString(extraParams, key));
            }

            if ("nickname".equals(key)){
                member.setNickname(getString(extraParams, key));
            }

            if ("qq".equals(key)){
                member.setQq(getString(extraParams, key));
            }

            if ("weixin".equals(key)){
                member.setWeixin(getString(extraParams, key));
            }
        }
    }

    private Integer getInteger(Map<String, Object> extraParams, String key) {
        return null;
    }

    private String getString(Map<String, Object> extraParams, String key) {
        return null;
    }
}
