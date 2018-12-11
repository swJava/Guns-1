package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ClassDetailResponse", description = "班级详情")
public class ClassDetailResponse extends SimpleResponser {
    private static final long serialVersionUID = -140511623928497259L;


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

    @ApiModelProperty(name = "data", value = "班级")
    private ClassDto data;

    public Class getData() {
        return data;
    }

    public void setData(Class classInfo) {
        ClassDto dto = new ClassDto();
        BeanUtils.copyProperties(classInfo, dto);

        // 格式化开课时间描述
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

        this.data = dto;
    }

    public static Responser me(Class classInfo) {
        ClassDetailResponse response = new ClassDetailResponse();

        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setData(classInfo);
        return response;
    }

    @ApiModel
    class ClassDto extends Class {
        @ApiModelProperty(name = "classTimeDesp", value = "上课时间描述", example = "每周五、周六 09:00 ~ 10:30")
        String classTimeDesp;

        public String getClassTimeDesp() {
            return classTimeDesp;
        }

        public void setClassTimeDesp(String classTimeDesp) {
            this.classTimeDesp = classTimeDesp;
        }
    }
}
