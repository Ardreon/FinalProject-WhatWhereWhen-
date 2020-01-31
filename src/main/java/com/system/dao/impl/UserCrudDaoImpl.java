package com.system.dao.impl;

import org.apache.log4j.Logger;
import com.system.dao.ConnectorDB;
import com.system.exceptions.DataBaseSqlRuntimeException;
import com.system.dao.UserDao;
import com.system.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCrudDaoImpl extends AbstractCrudDaoImpl<User> implements UserDao {

    protected static final Logger LOGGER = Logger.getLogger(UserCrudDaoImpl.class);

    private static final String SAVE_QUERY = "INSERT INTO user (id, name, email, password) values(?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM user WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM user";
    private static final String UPDATE_QUERY = "UPDATE user SET name =?, email=?, password=? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE * FROM user WHERE id = ?";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?";

    public UserCrudDaoImpl(ConnectorDB connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_BY_EMAIL_QUERY)) {
            preparedStatement.setString(2, email);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.warn(String.format(MESSAGE_ERROR, FIND_BY_EMAIL_QUERY, e));
            throw new DataBaseSqlRuntimeException("Operation findByEmail in UserCrudDaoImpl failed", e);
        }
        return Optional.empty();
    }

    protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .withId(resultSet.getLong("id"))
                .withName(resultSet.getString("name"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getEmail());
        preparedStatement.setString(3, entity.getPassword());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, User entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(4, entity.getId());
    }
}