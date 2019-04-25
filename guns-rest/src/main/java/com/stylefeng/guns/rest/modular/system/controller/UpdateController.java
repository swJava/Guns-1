package com.stylefeng.guns.rest.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.service.IDictService;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.system.responser.AttachmentUploadResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/30 23:24
 * @Version 1.0
 */
@Api(tags = "升级接口")
@RestController
@RequestMapping("/update")
public class UpdateController {

    private static final int default_version = 0;
    @Autowired
    private IDictService dictService;

    @RequestMapping(value = "/forcheck", method = RequestMethod.POST)
    @ApiOperation(value = "获取版本号", response = SimpleResponser.class)
    public Responser checkUpdator(){

        Wrapper<Dict> queryWrapper = new EntityWrapper<Dict>();
        queryWrapper.eq("code", "app_version");
        Dict dict = dictService.selectOne(queryWrapper);

        int version = default_version;

        if (null != dict){
            Wrapper<Dict> wrapper = new EntityWrapper<>();
            wrapper = wrapper.eq("pid", dict.getId());
            Dict versionDict = dictService.selectOne(wrapper);

            if (null != versionDict)
                try {
                    version = Integer.parseInt(versionDict.getCode());
                }catch(Exception e){}
        }
        SimpleResponser response = SimpleResponser.success();
        response.setMessage(String.valueOf(version));
        return response;
    }
}
