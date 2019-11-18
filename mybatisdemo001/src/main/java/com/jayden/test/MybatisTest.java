package com.jayden.test;

import com.jayden.config.XMLConfigParser;
import com.jayden.entity.User;
import com.jayden.sqlSession.DefaultSqlSessionFactory;
import com.jayden.sqlSession.SqlSession;
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
        DefaultSqlSessionFactory sqlSessionFactory = (DefaultSqlSessionFactory)new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println((DefaultSqlSessionFactory)sqlSessionFactory);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(1);
        user = sqlSession.selectOne("test.findUserById", user);

		System.out.println(user);
    }


}
