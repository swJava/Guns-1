package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ClassListResponse", description = "班级列表")
public class ClassListResponse extends SimpleResponser {
    private static final long serialVersionUID = 4265927729370374968L;

    private static final Map<Integer, String> DayOfWeekMap = new HashMap<Integer, String>();
    private static final Map<Integer, String> DayOfMonthMap = new HashMap<Integer, String>();

    static {
        DayOfWeekMap.put(Calendar.MONDAY, "周一");
        DayOfWeekMap.put(Calendar.THURSDAY, "周二");
        DayOfWeekMap.put(Calendar.WEDNESDAY, "周三");
        DayOfWeekMap.put(Calendar.THURSDAY, "周四");
        DayOfWeekMap.put(Calendar.FRIDAY, "周五");
        DayOfWeekMap.put(Calendar.SATURDAY, "周六");
        DayOfWeekMap.put(Calendar.SUNDAY, "周日");
    }

    static {
        DayOfMonthMap.put(Calendar.MONDAY, "1号");
    }

    @ApiModelProperty(name = "data", value = "班级集合")
    private List<ClassDto> data = new ArrayList<ClassDto>();

    public List<ClassDto> getData() {
        return data;
    }

    public void setData(List<com.stylefeng.guns.modular.system.model.Class> data) {
        for(com.stylefeng.guns.modular.system.model.Class classInfo : data){
            ClassDto dto = new ClassDto();
            BeanUtils.copyProperties(classInfo, dto);

            // 格式化开课时间描述
            formatClassTime(dto);
            // 判断是否能调课
            judgementAdjust(dto);
            // 判断是否能转班
            judgementChange(dto);

            //
            this.data.add(dto);
        }
    }

    private void judgementChange(ClassDto dto) {
        Date beginDate = dto.getBeginDate();
        Date now = new Date();

        if (now.before(beginDate))
            dto.setCanAdjust(true);

        dto.setCanAdjust(false);
    }

    private void judgementAdjust(ClassDto dto) {
        Date beginDate = dto.getBeginDate();
        Date now = new Date();

        if (now.before(beginDate))
            dto.setCanAdjust(true);

        dto.setCanAdjust(false);
    }

    private void formatClassTime(ClassDto dto) {
        int scheduleType = dto.getStudyTimeType();
        StringTokenizer tokenizer = new StringTokenizer(dto.getStudyTimeValue(), ",");

        StringBuffer despBuffer = new StringBuffer();
        despBuffer.append("每");

        while(tokenizer.hasMoreTokens()){
            int scheduleDay = Integer.parseInt(tokenizer.nextToken());

            switch(scheduleType){
                case Calendar.DAY_OF_WEEK:
                    despBuffer.append(DayOfWeekMap.get(scheduleDay)).append("、");
                    break;
                case Calendar.DAY_OF_MONTH:
                    despBuffer.append(DayOfWeekMap.get(scheduleDay)).append(",");
                    break;
            }
        }

        despBuffer.append(dto.getBeginTime().substring(0, 2)).append(":").append(dto.getBeginTime().substring(2));
        despBuffer.append(" ~ ");
        despBuffer.append(dto.getEndTime().substring(0, 2)).append(":").append(dto.getEndTime().substring(2));

        dto.setClassTimeDesp(despBuffer.toString());

    }

    public static Responser me(List<com.stylefeng.guns.modular.system.model.Class> classList) {
        ClassListResponse response = new ClassListResponse();
        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setData(classList);
        return response;
    }

    @ApiModel
    class ClassDto extends com.stylefeng.guns.modular.system.model.Class {

        @ApiModelProperty(name = "classTimeDesp", value = "上课时间描述", example = "每周五、周六 09:00 ~ 10:30")
        String classTimeDesp;

        @ApiModelProperty(name = "canAdjust", value = "能否调课", example = "false")
        boolean canAdjust;

        @ApiModelProperty(name = "canChange", value = "能否转班", example = "true")
        boolean canChange;

        public String getClassTimeDesp() {
            return classTimeDesp;
        }

        public void setClassTimeDesp(String classTimeDesp) {
            this.classTimeDesp = classTimeDesp;
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
    }
}
