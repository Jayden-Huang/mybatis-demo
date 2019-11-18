package com.jayden.config;

import com.jayden.utils.GenericTokenParser;
import com.jayden.utils.ParameterMappingTokenHandler;

/**
 * Author: Jayden
 * Date: 2019-11-16 12:06
 * Content:
 */
public class SqlSource {

    private String sqlText;

    public SqlSource(String sqlText) {
        this.sqlText = sqlText;
    }

    public BoundSql getBoundSql(){
        // 解析sql文本
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{","}",tokenHandler);
        String sql = genericTokenParser.parse(sqlText);
        return new BoundSql(sql,tokenHandler.getParameterMappings());
    }

}
