package com.jayden.sqlSession;

import java.util.List;

/**
 * Author: Jayden
 * Date: 2019-11-11 16:53
 * Content:
 */
public interface SqlSession {

    <T> T selectOne(String statementId,Object param);

    <T> List<T> selectList(String statementId, Object param);
}
