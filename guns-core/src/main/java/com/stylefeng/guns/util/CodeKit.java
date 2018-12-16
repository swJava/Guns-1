package com.stylefeng.guns.util;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.system.model.Sequence;
import com.stylefeng.guns.modular.system.service.ISequenceService;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/25 16:50
 * @Version 1.0
 */
public final class CodeKit {
    private static final String FILE_NAME_DICT = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER_RAND_DICT = "0123456789";

    private static enum CODE_DEFINE {
        STUDENT ("XY", 8),  // 学员
        COURSE ("KC", 6),  // 课程
        ROOM ("JS", 6),  // 教室
        TEACHER ("LS", 6),  // 教师
        CLASS ("BJ", 6),  // 课程
        OUTLINE ("KS", 6),  // 课时
        ORDER_ITEM ("DI", 6),  // 订单项
        QUESTION ("ST", 8),  // 试题
        COLUMN ("LM", 6),  // 栏目
        ;

        String name;
        int length;

        CODE_DEFINE(String name, int len){
            this.name = name;
            this.length = len;
        }
    }
    /**
     * 生成学员编码
     * @return
     */
    public static String generateColumn() {
        return generate(CODE_DEFINE.COLUMN.name, CODE_DEFINE.COLUMN.length, null);
    }
    /**
     * 生成学员编码
     * @return
     */
    public static String generateStudent() {
        return generate(CODE_DEFINE.STUDENT.name, CODE_DEFINE.STUDENT.length, null);
    }
    /**
     * 生成教师编码
     * @return
     */
    public static String generateTeacher() {
        return generate(CODE_DEFINE.TEACHER.name, CODE_DEFINE.TEACHER.length, null);
    }
    /**
     * 生成教室编码
     * @return
     */
    public static String generateRoom() {
        return generate(CODE_DEFINE.ROOM.name, CODE_DEFINE.ROOM.length, null);
    }
    /**
     * 生成班级编码
     * @return
     */
    public static String generateClass() {
        return generate(CODE_DEFINE.CLASS.name, CODE_DEFINE.CLASS.length, null);
    }
    /**
     * 生成课时编码
     * @return
     */
    public static String generateOutline() {
        return generate(CODE_DEFINE.OUTLINE.name, CODE_DEFINE.OUTLINE.length, null);
    }
    /**
     * 生成订单项
     * @return
     */
    public static String generateOrderItem() {
        return generate(CODE_DEFINE.ORDER_ITEM.name, CODE_DEFINE.ORDER_ITEM.length, new String[]{DateUtil.getyyMMdd()});
    }
    /**
     * 生成试题项
     * @return
     */
    public static String generateQuestion() {
        return generate(CODE_DEFINE.QUESTION.name, CODE_DEFINE.QUESTION.length, null);
    }
    /**
     * 生成试题项
     * @return
     */
    public static String generateCourse() {
        return generate(CODE_DEFINE.COURSE.name, CODE_DEFINE.COURSE.length, new String[]{DateUtil.getyyMMdd()});
    }

    private CodeKit(){}

    private static ISequenceService sequenceService;

    static{
        sequenceService = (ISequenceService) SpringContextHolder.getApplicationContext().getBean("sequenceService");
    }

    public static String generate(String codeName, int length, String... prefixies){
        Assert.notNull(codeName, "codeName is null");
        Assert.isTrue(length > 0, "length must greater than 0");

        Wrapper<Sequence> queryWrapper = new EntityWrapper<Sequence>();
        queryWrapper.eq("name", codeName);

        Sequence sequence = sequenceService.selectOne(queryWrapper);
        if (null == sequence){sequence = createSequence(codeName, length);}

        long currentVal = sequence.getCurrentVal();
        long nextVal = currentVal + 1L;
        if (String.valueOf(nextVal).length() > sequence.getLength()) {
            sequence.setCurrentVal(0L);
        } else {
            sequence.setCurrentVal(nextVal);
        }

        sequenceService.updateById(sequence);

        StringBuffer buff = new StringBuffer();
        buff.append(codeName);
        if(prefixies!=null){
            for(String prefix : prefixies){
                buff.append(prefix);
            }
        }
        buff.append(padLeft(String.valueOf(nextVal), length, '0'));

        return buff.toString();
    }

    private static Sequence createSequence(String codeName, int length) {
        Sequence sequence = new Sequence();

        sequence.setName(codeName);
        sequence.setLength(length);
        sequence.setCurrentVal(0L);

        sequenceService.insert(sequence);
        return sequence;
    }

    private static String padLeft(String value, int len, char alexin) {
        String str = "";
        int strlen = value.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        return str + value;
    }

    public static String generateOrder(){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int number = random.nextInt(FILE_NAME_DICT.length());
            sb.append(FILE_NAME_DICT.charAt(number));
        }
        sb.append(DateUtil.getyyMMddHHmmss());

        for (int i = 0; i < 4; i++) {
            int number = random.nextInt(NUMBER_RAND_DICT.length());
            sb.append(NUMBER_RAND_DICT.charAt(number));
        }

        return sb.toString();
    }
}
