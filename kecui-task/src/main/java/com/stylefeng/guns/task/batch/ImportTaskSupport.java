package com.stylefeng.guns.task.batch;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/28 01:23
 * @Version 1.0
 */
public abstract class ImportTaskSupport {

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
}
