package com.an.java.jdom;

import com.an.java.XmlEntity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.*;

/**
 * 使用JDOM读取XML文件
 */
public class ReadXMLByJDom {
    private List<XmlEntity> XmlEntitys = null;
    private XmlEntity XmlEntity = null;

    public List<XmlEntity> getXmlEntitys(String fileName) {
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document document = saxBuilder.build(new FileInputStream(fileName));
            //获取根节点XmlEntitystore  
            Element rootElement = document.getRootElement();
            //获取根节点的子节点，返回子节点的数组  
            List<Element> XmlEntityList = rootElement.getChildren();
            XmlEntitys = new ArrayList<XmlEntity>();
            for (Element XmlEntityElement : XmlEntityList) {
//                XmlEntity = new XmlEntity();
                //获取XmlEntityElement的属性
                List<Attribute> XmlEntityAttributes = XmlEntityElement.getAttributes();/*此为第一层解析*/

                String tableName = null;
                if (XmlEntityAttributes.get(0).getName().equals("name")) {
                    tableName = XmlEntityAttributes.get(0).getValue();
                }
//                for(Attribute attribute : XmlEntityAttributes){
//                    if(attribute.getName().equals("name")){
//                        String tableName = attribute.getValue(); //System.out.println(id);
//                        XmlEntity.setTableName(tableName);
//                    }
//                }
                //获取XmlEntityElement的子节点
                List<Element> children = XmlEntityElement.getChildren();/*此为第二层解析*/
                for (Element child : children) {
                    List<Attribute> attributes = child.getAttributes();/*获取子节点的属性*/
                    XmlEntity = new XmlEntity();
                    XmlEntity.setTableName(tableName);
                    for (Attribute attribute : attributes){
                        if (attribute.getName().equals("name")) {
                            String name = attribute.getValue();
                            XmlEntity.setName(name);
                        } else if (attribute.getName().equals("type")) {
                            String type = attribute.getValue();
                            XmlEntity.setType(type);
                        } else if (attribute.getName().equals("length")) {
                            String length = attribute.getValue();
                            XmlEntity.setLength(length);
                        }
                    }
                    XmlEntitys.add(XmlEntity);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return XmlEntitys;
    }

    public static void main(String[] args) {
        String fileName = "java_5_xml/src/main/java/com/an/java/C00100_20190725_0005_TB_CON_GRT_RELATION.meta";
        List<XmlEntity> XmlEntitys = new ReadXMLByJDom().getXmlEntitys(fileName);
        for (XmlEntity XmlEntity : XmlEntitys) {
            System.out.println(XmlEntity);
        }
    }
}
