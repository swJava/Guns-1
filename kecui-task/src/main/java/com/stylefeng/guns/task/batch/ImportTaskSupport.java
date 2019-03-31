package com.stylefeng.guns.task.batch;

import com.stylefeng.guns.util.DateUtil;
import com.stylefeng.guns.util.RegexUtil;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/28 01:23
 * @Version 1.0
 */
public abstract class ImportTaskSupport {


    protected String getMappingCode(String[] datas, int index) {
        if (0 > index || index >= datas.length)
            return "";

        String[] mapping = getMapping(datas, index);

        return mapping[0];
    }

    protected String[] getMapping(String[] datas, int index) {
        if (0 > index || index >= datas.length)
            return new String[]{"", ""};

        String data = getString(datas, index);
        Pattern pattern = Pattern.compile("^.*(\\(.*\\)).*$");
        Matcher m = pattern.matcher(data);
        String code = "";
        String name = "";
        if (m.find()){
            code = m.group(1);
            name = data.substring(code.length());
        }

        return new String[]{code.substring(1, code.length() - 1), name};
    }

    protected String getString(String[] datas, int index) {
        return index < 0 ? "" : (index >= datas.length ? "" : datas[index]);
    }

    protected Double getDouble(String[] datas, int index) {
        String value = getString(datas, index);
        Double result = 0d;
        try{
            result = Double.parseDouble(value);
        }catch(Exception e){}
        return result;
    }

    protected Integer getInteger(String[] datas, int index) {
        String value = getString(datas, index);
        Integer result = 0;
        try{
            result = Integer.parseInt(value);
        }catch(Exception e){}
        return result;
    }

    protected Date getDate(String[] datas, int index) {
        if (0 > index || index >= datas.length)
            return null;

        String dateStr = getString(datas, index);
        Date result = null;
        try {
            result = DateUtils.parseDate(dateStr, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm"});
        }catch(Exception e){}

        return result;
    }

}
