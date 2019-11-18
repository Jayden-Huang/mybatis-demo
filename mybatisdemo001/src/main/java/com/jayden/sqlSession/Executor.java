package com.jayden.sqlSession;

import com.jayden.config.Configuration;
import com.jayden.config.MappedStatement;

import java.util.List;

/**
 * Author: Jayden
 * Date: 2019-11-16 15:06
 * Content:
 */
public interface Executor {

    <T>List<T> query(Configuration configuration, MappedStatement mappedStatement,Object param);
}
