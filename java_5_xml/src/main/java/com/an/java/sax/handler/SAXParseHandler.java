package com.an.java.sax.handler;

import com.an.java.XmlEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.Attribute;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 使用SAX解析xml文件时需要的handler
 */
public class SAXParseHandler extends DefaultHandler {

    //存放解析到的XmlEntity数组
    private List<XmlEntity> list;
    //存放当前解析的XmlEntity
    private XmlEntity XmlEntity;
    //存放当前节点值
    private String content = null;

    /**
     * 开始解析xml文档时调用此方法
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("开始解析xml文件");
        list = new ArrayList<XmlEntity>();
    }

    /**
     * 文档解析完成后调用此方法
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("xml文件解析完毕");
    }

    /**
     * 开始解析节点时调用此方法
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //当节点名为XmlEntity时,获取XmlEntity的属性id
//        XmlEntity = new XmlEntity();
//        if(qName.equals("meta-def")){
//            String tableName = attributes.getValue("name");
//            XmlEntity.setTableName(tableName);
//        }else
        if(qName.equals("field")){/*qName表示标签名，如config、meta-def、field等，暂时没想出来怎么同时取到不同标签的值*/
            XmlEntity = new XmlEntity();
            String name = attributes.getValue("name");//System.out.println("id值为"+id);
            XmlEntity.setName(name);
            String type = attributes.getValue("type");//System.out.println("id值为"+id);
            XmlEntity.setType(type);
            String length = attributes.getValue("length");//System.out.println("id值为"+id);
            XmlEntity.setLength(length);
        }
        list.add(XmlEntity);
    }


    /**
     *节点解析完毕时调用此方法
     *
     *@param qName 节点名
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
//        if(qName.equals("field")){
//            XmlEntity.setName(content);
//            //System.out.println("书名"+content);
//        }
//        else if(qName.equals("author")){
//            XmlEntity.setAuthor(content);
//            //  System.out.println("作者"+content);
//        }else if(qName.equals("year")){
//            XmlEntity.setYear(Integer.parseInt(content));
//            //  System.out.println("年份"+content);
//        }else if(qName.equals("price")){
//            XmlEntity.setPrice(Double.parseDouble(content));
//            //  System.out.println("价格"+content);
//        }
//        else if(qName.equals("XmlEntity")){         //当结束当前XmlEntity解析时,将该XmlEntity添加到数组后置为空，方便下一次XmlEntity赋值
//            list.add(XmlEntity);
//            XmlEntity = null;
//        }
    }


    /**
     * 此方法用来获取节点的值
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        content = new String(ch, start, length);
        //收集不为空白的节点值
//      if(!content.trim().equals("")){
//          System.out.println("节点值为："+content);
//      }
    }
    public List<XmlEntity> getXmlEntitys(){
        return list;
    }
}
