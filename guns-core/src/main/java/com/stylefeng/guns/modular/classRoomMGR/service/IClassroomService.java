package com.stylefeng.guns.modular.classRoomMGR.service;

import com.stylefeng.guns.modular.system.model.Classroom;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 教室表 服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-11-05
 */
public interface IClassroomService extends IService<Classroom> {
    /**
     * 获取教室信息
     *
     * @param classRoomCode
     * @return
     */
    Classroom get(String classRoomCode);
}
