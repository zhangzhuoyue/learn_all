package com.zzzy.xstream;

import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * @author zhangyue
 * @create 2019/11/20
 */
public class StringConverterDemo extends StringConverter {


    @Override
    public Object fromString(String str) {
        Object o = super.fromString(str);
        return o == null ? o :((String)o).trim();
    }
}
