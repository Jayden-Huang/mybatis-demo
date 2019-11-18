package com.jayden.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * datasourse 配置文件
 */

public class Configuration {

    private DataSource dataSource;

    private Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void addMappedStatements(String statementId,MappedStatement mappedStatement) {
        this.mappedStatements.put(statementId,mappedStatement);
    }
}
