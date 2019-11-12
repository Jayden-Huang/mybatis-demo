package com.jayden.config;

import com.jayden.sqlSession.DocumentReader;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Author: Jayden
 * Date: 2019-11-11 17:26
 * Content:XML文件解析
 */
public class XMLConfigParser {

    private Configuration configuration;

    public XMLConfigParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration parseConfiguration(Element element){
         parseEnvironments(element.element("environments"));
         parseMappers(element.element("mappers"));
         return configuration;
    }

    private void parseMappers(Element element){
        List<Element> elements = element.elements("mapper");
        for (Element e:elements) {
            parseMapper(e);
        }
    }

    private void parseMapper(Element element){
        String rescource = element.attributeValue("resource");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(rescource);
        Document document = DocumentReader.createDocument(inputStream);
        //  解析Mapper文件
        XMLMapperParse xmlMapperParse = new XMLMapperParse(configuration);
        xmlMapperParse.parse(document.getRootElement());
    }



    /**
     * <environments></>解析数据源
     * @param element
     */
    private void parseEnvironments(Element element){
        String defaultId = element.attributeValue("default");
        List<Element> elements = element.elements("environment");
        for (Element e:elements) {
            String envId = e.attributeValue("id");
            // 和默认的环境id匹配，才进行解析
            if (envId != null && envId.equals(defaultId)){
                parseDataSource(e.element("dataSource"));
            }
        }
    }

    private void parseDataSource(Element element){
        String type = element.attributeValue("type");
        if (type == null || type.equals("")){
            type = "DBCP";
        }
        List<Element> elements = element.elements("property");
        Properties properties = new Properties();
        for (Element e:elements) {
            String name = e.attributeValue("name");
            String value = e.attributeValue("value");
            properties.setProperty(name,value);
        }
        BasicDataSource basicDataSource = null;
        if (type.equals("DBCP")){
            basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName(properties.getProperty("driver"));
            basicDataSource.setUrl(properties.getProperty("url"));
            basicDataSource.setUsername(properties.getProperty("username"));
            basicDataSource.setPassword(properties.getProperty("password"));
        }
        configuration.setDataSource(basicDataSource);
    }
}
