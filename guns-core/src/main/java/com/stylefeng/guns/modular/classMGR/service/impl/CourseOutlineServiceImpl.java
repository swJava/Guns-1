package com.stylefeng.guns.modular.classMGR.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.enums.StatusEnum;
import com.stylefeng.guns.core.support.StrKit;
import com.stylefeng.guns.modular.classMGR.service.ICourseOutlineService;
import com.stylefeng.guns.modular.system.dao.CourseOutlineMapper;
import com.stylefeng.guns.modular.system.model.CourseOutline;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.util.ToolUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 课程大纲 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-11-17
 */
@Service("iCourseOutlineService")
public class CourseOutlineServiceImpl extends ServiceImpl<CourseOutlineMapper, CourseOutline> implements ICourseOutlineService {

    @Resource
    private CourseOutlineMapper courseOutlineMapper;


    /**
     * 每个条目之间的分隔符
     */
    public static final String ITEM_SPLIT = ";";

    /**
     * 属性之间的分隔符
     */
    public static final String ATTR_SPLIT = ":";



    @Override
    public void addCourseOutline(String classCode, String courseCode, String courseValues) {

        String[] items = StrKit.split(StrKit.removeSuffix(courseValues, ITEM_SPLIT), ITEM_SPLIT);
        for (String item : items) {
            CourseOutline courseOutline = new CourseOutline();
            String[] attrs = item.split(ATTR_SPLIT);
            courseOutline.setCode(courseCode);
/*            courseOutline.setClassCode(classCode);
            courseOutline.setClassDate(attrs[0]);
            courseOutline.setClassTime(attrs[1]);*/
            courseOutline.setOutline(attrs[2]);
            courseOutline.setSort(Integer.valueOf(attrs[3]));
            courseOutline.setStatus(StatusEnum.STATUS_VALID.getCode());
            courseOutlineMapper.insert(courseOutline);
        }
    }

    @Override
    public CourseOutline get(String code) {
        if (null == code)
            return null;

        return selectOne(new EntityWrapper<CourseOutline>().eq("code", code));
    }

}
