package com.system.dao.impl;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl extends AbstractCrudDaoImpl<User> implements UserDao {

    private static final String SAVE = "INSERT INTO user (id, name, email, password) values(?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM user";
    private static final String UPDATE = "UPDATE user SET name =?, email=?, password=? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE * FROM user WHERE id = ?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    private static final Logger log = Logger.getLogger(AbstractCrudDaoImpl.class.getName());


    public UserDaoImpl(ConnectorDB connector) {
        super(connector, SAVE, FIND_BY_ID, FIND_ALL, UPDATE, DELETE_BY_ID);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToEntity(resultSet));
                }
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE,"FindById operation is failed: ", e);
            throw new DataBaseSqlRuntimeException("", e);
        }

        return Optional.empty();
    }


    @Override
    public List<User> findAll() {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_ALL)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    final User optionalUser = mapResultSetToEntity(resultSet);
                    users.add(optionalUser);
                }
                return users;
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE,"FindAll operation is failed: ", e);
            throw new DataBaseSqlRuntimeException("", e);
        }
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