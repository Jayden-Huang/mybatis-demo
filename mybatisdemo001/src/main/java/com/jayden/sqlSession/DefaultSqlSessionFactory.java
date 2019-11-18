package com.jayden.sqlSession;

import com.jayden.config.Configuration;

/**
 * Author: Jayden
 * Date: 2019-11-11 17:04
 * Content:
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    // 引入配置
    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
