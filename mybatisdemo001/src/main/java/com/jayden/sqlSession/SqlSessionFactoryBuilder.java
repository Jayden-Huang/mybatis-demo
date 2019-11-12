package com.jayden.sqlSession;

import com.jayden.config.Configuration;
import com.jayden.config.XMLConfigParser;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

/**
 * Author: Jayden
 * Date: 2019-11-11 17:03
 * Content:
 */
public class SqlSessionFactoryBuilder {

    /**
     *  封装了全局配置文件信息和所有映射文件的信息
     */
    private Configuration configuration;

    public SqlSessionFactoryBuilder() {
        this.configuration = new Configuration();
    }

    public SqlSessionFactory build(InputStream inputStream){
        // 解析全局配置文件，封装Configuration对象
        // 通过InputStream流对象，去创建Document对像(demo4j) -- 此时没有针对xml文件中的语义进行解析
        // DocumentReader -- 加载InputStream流，创建document对象
        Document document = DocumentReader.createDocument(inputStream);
        // 进行mybatis语义解析（全局配置文件语义解析，映射文件语义解析）
        XMLConfigParser xmlConfigParser = new XMLConfigParser(configuration);
        configuration = xmlConfigParser.parseConfiguration(document.getRootElement());
        return build();
    }

    public SqlSessionFactory build(Reader reader){
        return build();
    }

    public SqlSessionFactory build(){
        return new DefaultSqlSessionFactory(configuration);
    }
}
