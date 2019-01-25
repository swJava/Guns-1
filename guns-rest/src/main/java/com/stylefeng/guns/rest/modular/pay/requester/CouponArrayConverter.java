package com.stylefeng.guns.rest.modular.pay.requester;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.ConverterMatcher;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/28 00:14
 * @Version 1.0
 */
public class CouponArrayConverter implements Converter {

    private String startWith;

    public CouponArrayConverter(){
        this.startWith = "coupon_id_";
    }

    public CouponArrayConverter(String startWith){
        this.startWith = startWith;
    }

    @Override
    public boolean canConvert(Class type) {
        return type != null && type.isArray();
    }

    @Override
    public void marshal(Object bean, HierarchicalStreamWriter writer, MarshallingContext context) {

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        while(reader.hasMoreChildren()) {
            String nodeName = reader.getNodeName();
            System.out.println("NodeName = " + nodeName);
        }
        return null;
    }
}
