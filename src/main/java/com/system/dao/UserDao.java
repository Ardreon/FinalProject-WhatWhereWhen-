package com.system.dao;

import com.system.entity.UserEntity;

import java.util.Optional;

public interface UserDao {

    Optional<UserEntity> findByEmail(String email);
}