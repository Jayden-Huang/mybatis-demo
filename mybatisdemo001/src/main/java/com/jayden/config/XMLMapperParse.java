package com.jayden.config;

import org.dom4j.Element;

import java.util.List;

/**
 * Author: Jayden
 * Date: 2019-11-11 18:37
 * Content: 解析mappper文件
 */
public class XMLMapperParse {

    private Configuration configuration;

    private String namespace;

    public XMLMapperParse(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * <mapper namespace = "test"
     * @param element
     */
    public void parse(Element rootElement){
        // 将select标签解析为MappedStatement对象
        namespace = rootElement.attributeValue("namespace");
        // 将解析出来的MappedStatement对象放入Configuration对象中的map集合
        parseStatement(rootElement.elements("select"));
    }

    private void parseStatement(List<Element> elements){
        for (Element e:elements) {
            parseStatement(e);
        }
    }

    private void parseStatement(Element selectEle){
        String id = selectEle.attributeValue("id");
        id = namespace +"."+id;

        String parameterType = selectEle.attributeValue("parameterType");
        Class<?> parameterTypeClass = getClassType(parameterType);

        String resultType = selectEle.attributeValue("resultType");
        Class<?> resultTypeClass = getClassType(resultType);

        String statementType = selectEle.attributeValue("statementType");

        // 还包含#{id}占位符的SQL语句
        // 此时拿到未解析的SQL语句，还需要进行特殊解析
        // 使用面向对象思想，创建SqlSource对象（提供获取SQL语句和SQL语句中的参数这个功能）

        // 获取的SQL语句：select * from user where id = #{id}
        // 需要的SQL语句：select * from user where id = ?
        String sqlText = selectEle.getTextTrim();
        SqlSource sqlSource = new SqlSource(sqlText);
        // 封装MappedStatement对象
        // 可以使用构建者模式去创建MappedStatement对象
        MappedStatement mappedStatement = new MappedStatement(id, parameterTypeClass, resultTypeClass, statementType,
                sqlSource);

        configuration.addMappedStatements(id,mappedStatement);
    }

    private Class<?> getClassType(String parameterType){
        if (parameterType == null || parameterType.equals("")){
            return null;
        }
        try{
            Class<?> clazz = Class.forName(parameterType);
            return clazz;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
