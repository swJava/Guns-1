package com.stylefeng.guns.modular.system.model;

import java.util.EventObject;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/28 23:39
 * @Version 1.0
 */
public class AdjustStudentApprovalEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public AdjustStudentApprovalEvent(Object source, Object target, AdjustStudentApproveStateEnum action) {
        super(source);
    }
}
