package com.zzy.xstream;

import com.thoughtworks.xstream.converters.SingleValueConverter;

/**
 * @author zhangyue
 * @create 2019/11/19
 */
 class PersionListConverter implements SingleValueConverter {
    @Override
    public String toString(Object obj) {
        System.out.println("1");
        return ((PersionList)obj).getName();

    }

    @Override
    public Object fromString(String str) {
        System.out.println("2");
        return new PersionList(str);
    }

    @Override
    public boolean canConvert(Class type) {
        System.out.println("3");
        return type.equals(PersionList.class);
    }
}
