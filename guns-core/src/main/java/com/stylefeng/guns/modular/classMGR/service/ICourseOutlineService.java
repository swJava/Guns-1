package com.stylefeng.guns.modular.classMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Course;
import com.stylefeng.guns.modular.system.model.CourseOutline;

import java.util.List;

/**
 * <p>
 * 课程大纲 服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-11-17
 */
public interface ICourseOutlineService extends IService<CourseOutline> {
    String[] DEFAULT_OUTLINES = new String[]{
            "第一课", "第二课", "第三课","第四课", "第五课", "第六课", "第七课", "第八课", "第九课", "第十课",
            "第十一课", "第十二课", "第十三课","第十四课", "第十五课", "第十六课", "第十七课", "第十八课", "第十九课", "第二十课",
            "第二十一课", "第二十二课", "第二十三课","第二十四课", "第二十五课", "第二十六课", "第二十七课", "第二十八课", "第二十九课", "第三十课",
            "第三十一课", "第三十二课", "第三十三课","第三十四课", "第三十五课", "第三十六课", "第三十七课", "第三十八课", "第三十九课", "第四十课",
            "第四十一课", "第四十二课", "第四十三课","第四十四课", "第四十五课", "第四十六课", "第四十七课", "第四十八课", "第四十九课", "第五十课",
            "第五十一课", "第五十二课", "第五十三课","第五十四课", "第五十五课", "第五十六课", "第五十七课", "第五十八课", "第五十九课", "第六十课",
            "第六十一课", "第六十二课", "第六十三课","第六十四课", "第六十五课", "第六十六课", "第六十七课", "第六十八课", "第六十九课", "第七十课",
            "第七十一课", "第七十二课", "第七十三课","第七十四课", "第七十五课", "第七十六课", "第七十七课", "第七十八课", "第七十九课", "第八十课",
            "第八十一课", "第八十二课", "第八十三课","第八十四课", "第八十五课", "第八十六课", "第八十七课", "第八十八课", "第八十九课", "第九十课",
            "第九十一课", "第九十二课", "第九十三课","第九十四课", "第九十五课", "第九十六课", "第九十七课", "第九十八课", "第九十九课"
    };

    /**
     * 新增课程大纲
     *
     * @param course        课程
     * @param outlineList      课程大纲列表
     */
    void addCourseOutline(Course course,List<CourseOutline> outlineList);

    /**
     * 新增课程大纲
     *
     * 按照默认大纲描述新增
     *
     * @param course
     */
    void addCourseOutline(Course course);

    /**
     * 获取课时信息
     *
     * @param outlineCode
     * @return
     */
    CourseOutline get(String outlineCode);

    /**
     * 获取默认课时描述
     *
     * @param index
     * @return
     */
    String getDefaultOutline(int index);

    /**
     * 更新课程大纲
     *
     * @param course
     * @param newPeriod
     */
    void refreshCourseOutline(Course course, int newPeriod);
}
