package com.jayden.test;

import com.jayden.config.XMLConfigParser;
import com.jayden.sqlSession.DefaultSqlSessionFactory;
import com.jayden.sqlSession.SqlSessionFactory;
import com.jayden.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * Author: Jayden
 * Date: 2019-11-12 10:09
 * Content:
 */
public class MybatisTest {

    @Test
    public void test(){
        String recourse = "SqlMapConfig.xml";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(recourse);
        SqlSessionFactory sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println((DefaultSqlSessionFactory)sqlSessionFactoryBuilder);
    }


}
