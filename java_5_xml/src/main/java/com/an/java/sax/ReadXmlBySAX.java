package com.an.java.sax;

import com.an.java.XmlEntity;
import com.an.java.sax.handler.SAXParseHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

/**
 * 使用SAX方式读取xml文件
 */
public class ReadXmlBySAX {
    private static List<XmlEntity> XmlEntitys = null;

    private SAXParserFactory sParserFactory = null;
    private SAXParser parser = null;


    public List<XmlEntity> getXmlEntitys(String fileName) throws Exception{
        SAXParserFactory sParserFactory = SAXParserFactory.newInstance();
        SAXParser parser = sParserFactory.newSAXParser();

        SAXParseHandler handler = new SAXParseHandler();
        parser.parse(fileName, handler);
        return handler.getXmlEntitys();

    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            XmlEntitys = new ReadXmlBySAX().getXmlEntitys("java_5_xml/src/main/java/com/an/java/C00100_20190725_0005_TB_CON_GRT_RELATION.meta");
            for(XmlEntity XmlEntity:XmlEntitys){
                System.out.println(XmlEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
