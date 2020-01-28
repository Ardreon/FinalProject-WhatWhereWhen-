package com.system.dao;

import com.system.domain.User;

import java.util.Optional;

public interface UserDao extends CrudDao {

    Optional<User> findByEmail(String email);

}