package com.jayden.sqlSession;

import com.jayden.config.*;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jayden
 * Date: 2019-11-16 15:14
 * Content:
 */
public class SimpleExecutor implements Executor {

    @Override
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object param) {
        // 获取数据源对象
        Connection connection = null;
        List<Object> results = new ArrayList<>();
        try {
            DataSource dataSource = configuration.getDataSource();
            connection = dataSource.getConnection();
            // 获取sql语句
            SqlSource sqlSource = mappedStatement.getSqlSource();
            BoundSql boundSql = sqlSource.getBoundSql();
            // 在这里解析sql
            String sql = boundSql.getSql();

            // 获取statementType
            String statementType = mappedStatement.getStatementType();
            if ("prepared".equals(statementType)){
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // 设置参数
                List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
                // 获取入参的类型
                Class<?> parameterTypeClass = mappedStatement.getParameterTypeClass();
                // 八种基本类型可以处理
                if (parameterTypeClass == Integer.class){
                    preparedStatement.setObject(1,param);
                }else {
                    // 此处主要解决POJO类型，暂不处理Map和List类型
                    for (int i = 0;i<parameterMappings.size();i++){
                        ParameterMapping parameterMapping = parameterMappings.get(i);
                        // 得到属性的名称
                        String name = parameterMapping.getName();
                        // 通过反射获取入参对象中执行名称的属性值
                        Field field = parameterTypeClass.getDeclaredField(name);
                        field.setAccessible(true);
                        Object value = field.get(param);
                        preparedStatement.setObject(i + 1,value);
                    }
                }
                ResultSet resultSet = preparedStatement.executeQuery();
                Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
                while (resultSet.next()){
                    Object returnObj = resultTypeClass.newInstance();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    int count = resultSetMetaData.getColumnCount();
                    for (int i = 1;i<= count;i++){
                        String columnName = resultSetMetaData.getColumnName(i);
                        Field field = resultTypeClass.getDeclaredField(columnName);
                        field.setAccessible(true);
                        field.set(returnObj,resultSet.getObject(columnName));
                    }
                    results.add(returnObj);
                }
            }

        }catch (Exception e){

        }
        return (List<T>) results;
    }
}
