package com.an.java.dom4j;

import com.an.java.XmlEntity;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 使用Dom4j读取XML文件
 */
public class ReadXMLByDom4j {
    private List<XmlEntity> XmlEntityList = null;
    private XmlEntity XmlEntity = null;

    public List<XmlEntity> getXmlEntitys(File file){
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element XmlEntitystore = document.getRootElement();
            Iterator storeit = XmlEntitystore.elementIterator();

            XmlEntityList = new ArrayList<XmlEntity>();
            while(storeit.hasNext()){/*此为解析第一层*/
//                XmlEntity = new XmlEntity();
                Element XmlEntityElement = (Element) storeit.next();
                //遍历XmlEntityElement的属性
                List<Attribute> attributes = XmlEntityElement.attributes();

                //获取到表名
                String tableName = null;
                if (attributes.get(0).getName().equals("name")){
                    tableName = attributes.get(0).getValue();
                }
//                for(Attribute attribute : attributes){
//                    if(attribute.getName().equals("name")){
//                        String tableName = attribute.getValue();//System.out.println(id);
//                        XmlEntity.setTableName(tableName);
//                    }
//                }
                Iterator XmlEntityit = XmlEntityElement.elementIterator();
                while(XmlEntityit.hasNext()){/*此为解析第二层*/
                    XmlEntity = new XmlEntity();//每次循环要清空实体类值
                    XmlEntity.setTableName(tableName);
                    Element child = (Element) XmlEntityit.next();
                    List<Attribute> attributes_child= child.attributes();
                    for(Attribute attribute : attributes_child){
                        if(attribute.getName().equals("name")){
                            String name = attribute.getValue();//System.out.println(id);
                            XmlEntity.setName(name);
                        }else if(attribute.getName().equals("type")){
                            String type = attribute.getValue();//System.out.println(id);
                            XmlEntity.setType(type);
                        }else if(attribute.getName().equals("length")){
                            String length = attribute.getValue();//System.out.println(id);
                            XmlEntity.setLength(length);
                        }
                    }
                    XmlEntityList.add(XmlEntity);
                    XmlEntity = null;
                }

                //子
//                Iterator XmlEntityit = XmlEntityElement.elementIterator();
//                while(XmlEntityit.hasNext()){
//                    Element child = (Element) XmlEntityit.next();
//                    String nodeName = child.getName();
//                    if(nodeName.equals(“name”)){
//                        //System.out.println(child.getStringValue());
//                        String name = child.getStringValue();
//                        XmlEntity.setName(name);
//                    }else if(nodeName.equals(“author”)){
//                        String author = child.getStringValue();
//                        XmlEntity.setAuthor(author);
//                    }else if(nodeName.equals(“year”)){
//                        String year = child.getStringValue();
//                        XmlEntity.setYear(Integer.parseInt(year));
//                    }else if(nodeName.equals(“price”)){
//                        String price = child.getStringValue();
//                        XmlEntity.setPrice(Double.parseDouble(price));
//                    }
//                }
//                XmlEntityList.add(XmlEntity);
//                XmlEntity = null;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return XmlEntityList;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        File file = new File("java_5_xml/src/main/java/com/an/java/C00100_20190725_0005_TB_CON_GRT_RELATION.meta");
        List<XmlEntity> XmlEntityList = new ReadXMLByDom4j().getXmlEntitys(file);
        for(com.an.java.XmlEntity XmlEntity : XmlEntityList){
            System.out.println(XmlEntity);
        }
    }
}
