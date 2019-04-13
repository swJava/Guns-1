package com.stylefeng.guns.task.cross;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.system.model.Class;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.bouncycastle.jcajce.provider.symmetric.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 预报
 *
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/4/11 02:15
 * @Version 1.0
 */
@Component
public class PreSignTask {

    @Autowired
    private IClassService classService;

    public void presign(){
        Wrapper<Class> queryWrapper = new EntityWrapper<Class>();
        Date now = new Date();

        queryWrapper.eq("status", GenericState.Valid.code);
        queryWrapper.eq("crossable", GenericState.Valid.code);
        queryWrapper.eq("presign_status", GenericState.Invalid.code);
        queryWrapper.le("presign_start_date", now);

        List<Class> presignClassQueue = classService.selectList(queryWrapper);
        
    }
}
