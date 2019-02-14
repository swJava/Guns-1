package com.stylefeng.guns.core.tag;

import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.util.ToolUtil;
import org.beetl.core.Tag;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/2/15 00:18
 * @Version 1.0
 */
@Component
@Scope("prototype")
public class SerialNumber extends Tag {

    private static final String[] LETTER_SERIAL_NUMBER = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    private static final Map<String, String[]> SERIAL_NUMBER_MAP = new HashMap<String, String[]>(){
        {
            put("LETTER", LETTER_SERIAL_NUMBER);
        }
    };

    @Override
    public void render() {
        Map<String, Object> attrs = (Map) args[1];
        if(ToolUtil.isEmpty(attrs.get("type"))){
            throw new GunsException(BizExceptionEnum.ERROR_CODE_EMPTY);
        }
        if(ToolUtil.isEmpty(attrs.get("index"))){
            throw new GunsException(BizExceptionEnum.ERROR_CODE_EMPTY);
        }
        String type = attrs.get("type").toString();

        String[] serialNumbers = SERIAL_NUMBER_MAP.get(type.toUpperCase());

        String html = "";
        if (null != serialNumbers){
            try {
                int index = Integer.parseInt(attrs.get("index").toString());
                html = serialNumbers[index];
            }catch(Exception e){e.printStackTrace();}
        }

        try{
            this.ctx.byteWriter.writeString(html);
        }catch (IOException e){
            throw new RuntimeException("输出序列号标签错误");
        }
    }
}
