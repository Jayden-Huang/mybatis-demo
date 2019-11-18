package com.jayden.sqlSession;

import com.jayden.config.Configuration;
import com.jayden.config.MappedStatement;

import java.util.List;

/**
 * Author: Jayden
 * Date: 2019-11-16 15:03
 * Content:
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<T> list = selectList(statementId,param);
        if (list != null && list.size() == 1){
            return list.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        // 真正执行CRUD
        Executor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatements().get(statementId);
        return executor.query(configuration,mappedStatement,param);
    }
}
