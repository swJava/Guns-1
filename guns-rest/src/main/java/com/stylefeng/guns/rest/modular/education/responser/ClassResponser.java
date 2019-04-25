package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.classMGR.transfer.ClassPlan;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.ScheduleClass;
import com.stylefeng.guns.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/16 21:40
 * @Version 1.0
 */
@ApiModel(value = "ClassResponser", description = "班级扩展")
public class ClassResponser extends com.stylefeng.guns.modular.system.model.Class {

    private static final BigDecimal TRANSFORM = new BigDecimal(100);

    @ApiModelProperty(name = "classTimeDesp", value = "上课时间描述", example = "每周五、周六 09:00 ~ 10:30")
    private String classTimeDesp;

    @ApiModelProperty(name = "formatPrice", value = "格式化的金额，保留小数点后两位, 单位： 元", example = "200。00")
    private String formatPrice;

    private String amount;

    @ApiModelProperty(name = "student", value = "学员名称", example = "小明")
    private String student;

    @ApiModelProperty(name = "canAdjust", value = "能否调课", example = "false")
    boolean canAdjust;

    @ApiModelProperty(name = "canChange", value = "能否转班", example = "true")
    boolean canChange;

    @ApiModelProperty(name = "planList", value = "排班计划")
    private List<ClassPlan> planList = new ArrayList<ClassPlan>();

    public String getClassTimeDesp() {
        return classTimeDesp;
    }

    public void setClassTimeDesp(String classTimeDesp) {
        this.classTimeDesp = classTimeDesp;
    }

    public String getFormatPrice() {
        return formatPrice;
    }

    public void setFormatPrice(String formatPrice) {
        this.formatPrice = formatPrice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setAmount(Long price){
        if (null == price)
            this.formatPrice = "0.00";

        BigDecimal bigPrice = new BigDecimal(price);

        this.formatPrice = bigPrice.divide(TRANSFORM).setScale(2).toString();
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public boolean isCanAdjust() {
        return canAdjust;
    }

    public void setCanAdjust(boolean canAdjust) {
        this.canAdjust = canAdjust;
    }

    public boolean isCanChange() {
        return canChange;
    }

    public void setCanChange(boolean canChange) {
        this.canChange = canChange;
    }

    public List<ClassPlan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<ClassPlan> planList) {
        this.planList = planList;
    }

    public static ClassResponser me(Class classInfo) {
        ClassResponser dto = new ClassResponser();
        BeanUtils.copyProperties(classInfo, dto);
        dto.setFormatPrice(dto.getPrice());
        dto.setAmount(dto.getPrice());

        // 格式化开课时间描述
        formatClassTime(dto);
        // 判断是否能调课
        judgementAdjust(dto);
        // 判断是否能转班
        judgementChange(dto);

        return dto;
    }

    public static ClassResponser me(Class classInfo, List<ClassPlan> classPlanList) {
        ClassResponser response = me(classInfo);
        response.setPlanList(classPlanList);

        return response;
    }

    private void setFormatPrice(Long price) {
        if (null == price)
            this.formatPrice = "0.00";

        BigDecimal bigPrice = new BigDecimal(price);

        this.formatPrice = bigPrice.divide(TRANSFORM).setScale(2).toString();
    }

    /**
     * 判断能否转班
     *
     * 在班级第一课时上课前，都是可以申请转班的
     * @param dto
     */
    private static void judgementChange(ClassResponser dto) {
        Date beginDate = dto.getBeginDate();
        Date now = new Date();

        dto.setCanChange(false);
        if (DateUtil.compareDate(now, beginDate, Calendar.DAY_OF_MONTH) > 0)
            dto.setCanChange(true);
    }

    /**
     * 判断能否调课
     *
     * 在最后一次课时上课前都该班级都是可以调课的
     * @param dto
     */
    private static void judgementAdjust(ClassResponser dto) {
        Date endDate = dto.getEndDate();
        Date now = new Date();

        dto.setCanAdjust(false);
        if (DateUtil.compareDate(now, endDate, Calendar.DAY_OF_MONTH) > 0)
            dto.setCanAdjust(true);
    }

    private static void formatClassTime(ClassResponser dto) {
        dto.setClassTimeDesp(dto.getStudyTimeDesp());
    }
}
