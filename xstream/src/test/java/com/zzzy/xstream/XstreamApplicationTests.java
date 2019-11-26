package com.zzzy.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.StringConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * marshal:直译为“编排”，在计算机中特指将数据按某种描述格式编排出来，通常来说一般是从非文本格式到文本格式的数据转化
 *
 * xstream 的toXNL()方法，
 * 1.将对象转化为一个 outputstream  ,经过封装->HierarchicalStreamReader.
 * 2.在字节输出流，可以设置字节流的编码集。
 * 3.
 *
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootfirstApplicationTests {

    @Test
    void contextLoads() {
        String xml = beanToXML();
        System.out.println(xml);
        Persion p = xmlToBean(xml, Persion.class);
        System.out.println(p);
    }


    //bean转换xml
    public String beanToXML() {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("persion", Persion.class); //添加别名，生成的
        xStream.alias("phone", Phone.class);
        Persion persio = new Persion("张", "河南", 10);
        persio.setPhone(new Phone("123456547", 12));
        persio.setPhone(new Phone("2346456", 76456));
        String xml = xStream.toXML(persio);

        return xml;
    }


    //xml 转换到bean
    public <T> T xmlToBean(String xml, Class<T> cls) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("persion", Persion.class);//如果没有这两个别名，将无法解析发送报文节点。
        xStream.alias("phone", Phone.class);
        //xStream.processAnnotations(cls);
        T obj = (T) xStream.fromXML(xml);
        return obj;
    }

    //xml转换为bean
    //如果不使用alias ,xml中的标签是包的全路径名。
    @Test
    public void beanToXml() {

        XStream xs = new XStream(new StaxDriver());
        xs.alias("persion", Persion.class);
        xs.alias("phone", Phone.class);
        Persion p = new Persion("里", "dizhi", 890);

        p.setPhone(new Phone("1235", 90));
        String xml = xs.toXML(p);
        System.out.println(xml);
    }

    //bean转换成xml
    //指定class中的成员变量别名，  *******************************************************************
    //alias 可以给class起别名，也可以对field起别名
    @Test
    public void aliesAndFields() {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("persion", Persion.class);
        xStream.alias("phone", Phone.class);
        xStream.aliasField("alias", Persion.class, "name");  //给成员变量起别名
        Persion persion = new Persion("文件", "机密", 12);
        persion.setPhone(new Phone("1235", 234));
        String string = xStream.toXML(persion);
        System.out.println(string);


    }
    //********************将xml -> bean 如果xml中的标签名和自己的实体类或者成员变量不相同，可以通过给给他们添加别名 来建立对应关系 ***************************************

   //在xml转换为bean时，可以将不一致的标签名和成员变量对应起来
    @Test
    public void xmlTobeanField(String string) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("persion", Persion.class); //给class起别名
        xStream.alias("phone", Phone.class);
        xStream.aliasField("alias", Persion.class, "name");   //在进行xml解析的时候，通过起别名，将xml中的标签和成员变量对应起来
        //Persion persion = new Persion("文件","机密",12);
        //persion.setPhone(new Phone("1235",234));//的
        // String string = xStream.toXML(persion);

        Persion p = (Persion) xStream.fromXML(string);
        System.out.println(p);
    }
     //bean -> xml bean 中含有list成员变量，那么xml中会有多个重复的标签
    //在entity中还有list成员变量，在生成时，会有多个重复标签生成
    @Test
    public String listbeanToxml() {
        XStream xStream = xStreamUtils("beanToxml");
        PersionList plist = new PersionList();
        plist.setName("张三");
        //
        List<Persion> list = new ArrayList<>();
        list.add(new Persion("123123", "addr", 12));
        list.add(new Persion("083", "addr1", 34));
        list.add(new Persion("12083", "addr2", 56));
        plist.setPersions(list);
        String str = xStream.toXML(plist);
        System.out.println(str);
        return str;


    }

    /****************************xstream   xml -> bean   xml中有多组相同的标签，往往 在对应的bean中含有list成员变量 ************************************************/
    //在xml中有多组重复的标签，这隐含了bean中有list属性。
    @Test
    public void listxmlTobean() {
        XStream xStream = xStreamUtils("beanToxml");
        String str = listbeanToxml();
        PersionList p = (PersionList) xStream.fromXML(str);
        System.out.println(p);
    }


   //xml中排除不需要的节点  implicit colection  <persions></persions>去除 ，里面的字节点不会删除
    @Test
    public void implicitCollectionXMLTobean() {

        PersionList plist = new PersionList();
        plist.setName("张三");
        List<Persion> list = new ArrayList<>();
        list.add(new Persion("123123", "addr", 12));
        list.add(new Persion("083", "addr1", 34));
        list.add(new Persion("12083", "addr2", 56));
        plist.setPersions(list);
        XStream xStream = xStreamUtils("beanToxml");

        xStream.addImplicitCollection(PersionList.class, "persions");    //在一个list转换成xml中，会有一个父节点(list类型的成员变量名)
        String str = xStream.toXML(plist);                                          //删除父节点，单时他的子节点暴露出来
        System.out.println(str);
    }

    //对标签名是package 进行alias
    //使用aliasPackage 替换报名xStream.aliasPackage(新,旧)
    @Test
    public void aliasPackage() {
        XStream xStream = xStreamUtils("noalias");
        PersionList plist = new PersionList();
        plist.setName("张三");
        List<Persion> list = new ArrayList<>();
        list.add(new Persion("123123", "addr", 12));
        list.add(new Persion("083", "addr1", 34));
        list.add(new Persion("12083", "addr2", 56));
        plist.setPersions(list);
        xStream.aliasPackage("com.demo", "com.springbootdemo");  //替换标签名是报名的标签。
        String s = xStream.toXML(plist);
        System.out.println(s);

    }

    // bean -> xml 将成员变量转换成一个标签的属性
    @Test
    public String attributeBeanToXml(){
        PersionList plist = new PersionList();
        plist.setName("张三");
        List<Persion> list = new ArrayList<>();
        list.add(new Persion("123123", "addr", 12));
        list.add(new Persion("083", "addr1", 34));
        list.add(new Persion("12083", "addr2", 56));
        plist.setPersions(list);

        XStream xStream = new XStream(new DomDriver());
        xStream.alias("persion", Persion.class);
        xStream.alias("phone", Phone.class);
        xStream.alias("persionList", PersionList.class);
        xStream.aliasField("persionName",PersionList.class,"name");
        xStream.addImplicitCollection(PersionList.class,"persions");
        xStream.useAttributeFor(PersionList.class,"name");         //将成员变量转换成一个标签的属性 useAttributeFor()
       // xStream.aliasField("persionName",PersionList.class,"name");
        //xStream.registerConverter(new PersionListConverter());
        String str = xStream.toXML(plist);
        System.out.println(str);
        return str;

    }


   //xml -> bean 里面涉及到对标签属性的转换 ,将标签的属性转换到bean中的成员变量中。
    @Test
    public void attributexmlTobean(){
        String kk = attributeBeanToXml();
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("persion", Persion.class);
        xStream.alias("phone", Phone.class);
        xStream.alias("persionList", PersionList.class);
        xStream.aliasField("persionName",PersionList.class,"name");
        xStream.addImplicitCollection(PersionList.class,"persions");
        xStream.useAttributeFor(PersionList.class,"name");                 //在将xml中的标签属性和类的成员变量对应起来useAttributeFor()
        //xStream.aliasField("persionName",PersionList.class,"name");
        PersionList persionList = (PersionList) xStream.fromXML(kk);
        System.out.println(persionList);

    }


    //xml->bean converter转换日期 || 字段没有值，也要转换为xml标签
    @Test
    public void datebeanToxml(){
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("persion", Persion.class);
        xStream.alias("phone", Phone.class);
        xStream.alias("persionList", PersionList.class);
        xStream.aliasField("persionName",PersionList.class,"name");
        //xStream.addImplicitCollection(PersionList.class,"persions");
        xStream.useAttributeFor(PersionList.class,"name");
        xStream.registerConverter(new DateConverter(Locale.SIMPLIFIED_CHINESE));
        PersionList plist = new PersionList();
        //plist.setName("张三");
        List<Persion> list = new ArrayList<>();
        Persion p1 = new Persion("123123", "addr", 12);
        Persion p2 = new Persion("123123", "addr", 12);
        Persion p3 = new Persion("123123", "addr", 12);
        p1.setPhone(new Phone());
        p2.setPhone(new Phone());
        p3.setPhone(new Phone());
        list.add(p1);
        list.add(p2);
        list.add(p3);
        plist.setDate(new Date());
        xStream.registerConverter(new DateConverter(Locale.SIMPLIFIED_CHINESE));
        //xStream.registerConverter(new NullConverter());   //如果使用了这个自定义的converter ,他的canConverter匹配了所有的成员，在这一个中匹配所有成员
        plist.setPersions(list);
        String str = xStream.toXML(plist);
        System.out.println(str);
        //return str;


    }

    //xml 转换为bean
    //如果xml中有多个重复的标签组出现，它应该是一个list的属性。若标签组没有父标签，在解析的时候也要指明你 忽略的是哪一个类的哪一个属性。
    @Test
    public void datexmlToBean(){
        String demo = "<persionList persionName=\"张三\">\n" +
                "  <persions>\n" +
                "    <persion>\n" +
                "      <name>            123123                 </name>\n" +
                "      <address>            addr</address>\n" +
                "      <age>12</age>\n" +
                "    </persion>\n" +
                "    <persion>\n" +
                "      <name>083</name>\n" +
                "      <address>                  addr1</address>\n" +
                "      <age>34</age>\n" +
                "    </persion>\n" +
                "    <persion>\n" +
                "      <name>12083</name>\n" +
                "      <address>addr2</address>\n" +
                "      <age>56</age>\n" +
                "    </persion>\n" +
                "  </persions>\n" +
                "  <date>2019-11-20 16:10:02</date>\n" +
                "</persionList>";
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.alias("persionList",PersionList.class);
        xStream.aliasField("persionName",PersionList.class,"name");  //如果没有这个别名，则无法见标签的属性和类中的成员变量对应起来
        xStream.useAttributeFor(PersionList.class,"name");
        //xStream.addImplicitCollection(PersionList.class,"persions");  //如果在list标签没有父标签，则在解析的时候需要忽略解析这个标签。标签名和类的字名相同可以映射上。
        xStream.alias("persion",Persion.class);
        xStream.alias("phone",Phone.class);
        xStream.registerConverter(new DateConverter(Locale.SIMPLIFIED_CHINESE));
       // xStream.registerConverter(new StringConverterDemo());  xml中遇到空字符串。去掉空字符串
        PersionList per = (PersionList)xStream.fromXML(demo);
        System.out.println(per);

    }


    //xml -> bean 去掉空字符串
    @Test
    public void trimxmlToBean(){
        String demo = "<persionList persionName=\"张三\">\n" +
                "  <persions>\n" +
                "    <persion>\n" +
                "      <name>            123123                 </name>\n" +
                "      <address>            addr</address>\n" +
                "      <age>12</age>\n" +
                "    </persion>\n" +
                "    <persion>\n" +
                "      <name>083</name>\n" +
                "      <address>                  addr1</address>\n" +
                "      <age>34</age>\n" +
                "    </persion>\n" +
                "    <persion>\n" +
                "      <name>12083</name>\n" +
                "      <address>addr2</address>\n" +
                "      <age>56</age>\n" +
                "    </persion>\n" +
                "  </persions>\n" +
                "  <date>2019-11-20 16:10:02</date>\n" +
                "</persionList>";
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.alias("persionList",PersionList.class);
        xStream.aliasField("persionName",PersionList.class,"name");  //如果没有这个别名，则无法见标签的属性和类中的成员变量对应起来
        xStream.useAttributeFor(PersionList.class,"name");
        //xStream.addImplicitCollection(PersionList.class,"persions");  //如果在list标签没有父标签，则在解析的时候需要忽略解析这个标签。标签名和类的字名相同可以映射上。
        xStream.alias("persion",Persion.class);
        xStream.alias("phone",Phone.class);
        xStream.registerConverter(new DateConverter(Locale.SIMPLIFIED_CHINESE));
        xStream.registerConverter(new StringConverterDemo());                      //通过自定义的converter,去掉空字符串
        PersionList per = (PersionList)xStream.fromXML(demo);
        xStream.toXML(per);
        System.out.println(per);
    }

    //xml-> bean  遇到空标签
    // 默认，在解析到空标签，是string类型，默认=''  .如果string成员变量对应的标签不存在，默认='null' .如果是基本数据类型的成员变量不存在，则他会有默认的值。
    //如果是一个引用数据类型不存在，=null
    // ，怎么处理，是给一个默认值还是跳过这个标签不解析***************/
    @Test
    public void ignorexmlToBean(){
        String xml = "<persionList>\n" +
               // "  <name></name>\n" +
                "  <persions>\n" +
                "    <Persion>\n" +
                "      <name>123123</name>\n" +
                "      <address>addr</address>\n" +
               // "      <age>12</age>\n" +
                "      <phone>\n" +
                "        <phoneNumber></phoneNumber>\n" +
                "        <address>0</address>\n" +
                "      </phone>\n" +
                "    </Persion>\n" +
                "    <Persion>\n" +
                "      <name>123123</name>\n" +
                "      <address>addr</address>\n" +
                "      <age>12</age>\n" +
                "      <phone>\n" +
                "        <phoneNumber></phoneNumber>\n" +
                "        <address>0</address>\n" +
                "      </phone>\n" +
                "    </Persion>\n" +
                "    <Persion>\n" +
                "      <name>123123</name>\n" +
                "      <address>addr</address>\n" +
                "      <age>12</age>\n" +
                "      <phone>\n" +
                "        <phoneNumber></phoneNumber>\n" +
                "        <address>0</address>\n" +
                "      </phone>\n" +
                "    </Persion>\n" +
                "  </persions>\n" +
               // "  <date>Mon Nov 25 18:34:14 CST 2019</date>\n" +
                "</persionList>";
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("Persion", Persion.class);
        xStream.alias("phone", Phone.class);
        xStream.alias("persionList", PersionList.class);
        //xStream.registerConverter(new DateConverter());
        //xStream.aliasField("persionName",PersionList.class,"name");
        PersionList list = (PersionList)xStream.fromXML(xml);
        System.out.println(list.toString());
    }

    //**********************************************************************************
    /**
     * beanToxml 获取beanToxml 的xstream
     * xmlTobean 获取xmlTobean 的xstream
     * @param string
     * @return
     */
    public XStream xStreamUtils(String string) {
        XStream xStream = new XStream(new DomDriver());
        if ("beanToxml".equals(string)) {
            xStream.alias("Persion", Persion.class);
            xStream.alias("phone", Phone.class);
            xStream.alias("persionList", PersionList.class);
        }

        if ("xmlTobean".equals(string)) {
            xStream.alias("persion", Persion.class);
            xStream.alias("phone", Phone.class);
            xStream.alias("persionList", PersionList.class);
        }
        if ("noalias".equals(string)) {

        }

        return xStream;
    }

}
