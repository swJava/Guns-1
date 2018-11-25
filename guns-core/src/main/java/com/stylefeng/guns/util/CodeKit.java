package com.stylefeng.guns.util;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.system.model.Sequence;
import com.stylefeng.guns.modular.system.service.ISequenceService;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/25 16:50
 * @Version 1.0
 */
public final class CodeKit {

    private static enum CODE_DEFINE {
        STUDENT ("XY", 8)  // 学员
        ;

        String name;
        int length;

        CODE_DEFINE(String name, int len){
            this.name = name;
            this.length = len;
        }
    }
    private CodeKit(){

    }

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
        if (null == sequence)
            sequence = createSequence(codeName, length);

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

        for(String prefix : prefixies){
            buff.append(prefix);
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

    public static String generateStudent() {
        Date now = new Date();
        String datePrefix = DateUtil.formatDate(now, "yyMMdd");

        return generate(CODE_DEFINE.STUDENT.name, CODE_DEFINE.STUDENT.length, new String[]{datePrefix});
    }
}
