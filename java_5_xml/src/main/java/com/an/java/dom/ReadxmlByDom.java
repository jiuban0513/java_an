package com.an.java.dom;

import com.an.java.XmlEntity;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用DOM方式读取xml文件
 */
public class ReadxmlByDom {

    private static DocumentBuilderFactory dbFactory = null;
    private static DocumentBuilder db = null;
    private static Document document = null;
    private static List<XmlEntity> XmlEntitys = null;

    static{
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            db = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static List<XmlEntity> getXmlEntitys(String fileName) throws Exception{
        //将给定 URI 的内容解析为一个 XML 文档,并返回Document对象  
        document = db.parse(fileName);
        //按文档顺序返回包含在文档中且具有给定标记名称的所有 Element 的 NodeList  
        NodeList XmlEntityList = document.getElementsByTagName("field");
        XmlEntitys = new ArrayList<XmlEntity>();
        //遍历XmlEntitys  
        for(int i=0;i<XmlEntityList.getLength();i++){
            XmlEntity XmlEntity = new XmlEntity();
            //获取第i个XmlEntity结点  
            org.w3c.dom.Node node = XmlEntityList.item(i);
            //获取第i个XmlEntity的所有属性  
            NamedNodeMap namedNodeMap = node.getAttributes();
            //获取已知名为name的属性值
            String name = namedNodeMap.getNamedItem("name").getTextContent();
            XmlEntity.setName(name);
            String type = namedNodeMap.getNamedItem("type").getTextContent();
            XmlEntity.setType(type);
            String length = namedNodeMap.getNamedItem("length").getTextContent();
            XmlEntity.setLength(length);

//            //获取XmlEntity结点的子节点,包含了Test类型的换行
//            NodeList cList = node.getChildNodes();//System.out.println(cList.getLength());9
//
//            //将一个XmlEntity里面的属性加入数组
//            ArrayList<String> contents = new ArrayList<>();
//            for(int j=1;j<cList.getLength();j+=2){
//
//                org.w3c.dom.Node cNode = cList.item(j);
//                String content = cNode.getFirstChild().getTextContent();
//                contents.add(content);
//                //System.out.println(contents);
//            }
//            XmlEntity.setName(contents.get(0));
//            XmlEntity.setAuthor(contents.get(1));
//            XmlEntity.setYear(Integer.parseInt(contents.get(2)));
//            XmlEntity.setPrice(Double.parseDouble(contents.get(3)));
            XmlEntitys.add(XmlEntity);
        }
        return XmlEntitys;
    }

    public static void main(String args[]){
        String fileName = "java_5_xml/src/main/java/com/an/java/C00100_20190725_0005_TB_CON_GRT_RELATION.meta";
        try {
            List<XmlEntity> list = ReadxmlByDom.getXmlEntitys(fileName);
            for(XmlEntity XmlEntity :list){
                System.out.println(XmlEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
