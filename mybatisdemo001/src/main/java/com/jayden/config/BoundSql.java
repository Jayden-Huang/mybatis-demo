package com.jayden.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jayden
 * Date: 2019-11-16 12:07
 * Content:
 */
public class BoundSql {

    /**
     * 解析之后的sql
     */
    private String sql;

    /**
     * 解析出来的参数
     */
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(ParameterMapping parameterMappings) {
        this.parameterMappings.add(parameterMappings);
    }
}
