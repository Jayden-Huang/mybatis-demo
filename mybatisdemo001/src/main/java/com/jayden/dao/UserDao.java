package com.jayden.dao;

import com.jayden.entity.User;

public interface UserDao {

    User findById(String id);

}
