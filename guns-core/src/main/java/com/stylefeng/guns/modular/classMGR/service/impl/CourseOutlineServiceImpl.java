package com.stylefeng.guns.modular.classMGR.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.ICourseOutlineService;
import com.stylefeng.guns.modular.system.dao.CourseOutlineMapper;
import com.stylefeng.guns.modular.system.model.Course;
import com.stylefeng.guns.modular.system.model.CourseOutline;
import com.stylefeng.guns.util.CodeKit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void addCourseOutline(Course course, List<CourseOutline> outlineList) {
        if (null == course)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        List<CourseOutline> courseOutlineList = selectList(new EntityWrapper<CourseOutline>().eq("course_code", course.getCode()));
        List<Long> deleteIds = new ArrayList<>();
        for(CourseOutline courseOutline : courseOutlineList){
            deleteIds.add(courseOutline.getId());
        }
        if (!deleteIds.isEmpty())
            deleteBatchIds(deleteIds);

        for(CourseOutline courseOutline : outlineList){
            CourseOutline newCourseOutline = new CourseOutline();
            newCourseOutline.setCode(CodeKit.generateOutline());
            newCourseOutline.setCourseCode(course.getCode());
            newCourseOutline.setOutline(courseOutline.getOutline());
            newCourseOutline.setDescription(courseOutline.getDescription());
            newCourseOutline.setSort(courseOutline.getSort());
            newCourseOutline.setStatus(GenericState.Valid.code);

            insert(newCourseOutline);
        }
    }

    @Override
    public CourseOutline get(String code) {
        if (null == code)
            return null;

        return selectOne(new EntityWrapper<CourseOutline>().eq("code", code));
    }

}
