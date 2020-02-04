package com.system.dao.impl;

import com.system.entity.RoleEntity;
import org.apache.log4j.Logger;
import com.system.dao.ConnectorDB;
import com.system.exceptions.DataBaseSqlRuntimeException;
import com.system.dao.UserDao;
import com.system.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class UserCrudDaoImpl extends AbstractCrudDaoImpl<UserEntity> implements UserDao {

    protected static final Logger LOGGER = Logger.getLogger(UserCrudDaoImpl.class);

    private static final String SAVE_QUERY = "INSERT INTO user (id, name, email, password, score, role) values(?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM user WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM user";
    private static final String UPDATE_QUERY = "UPDATE user SET name =?, email=?, password=?, score=?, role=? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE * FROM user WHERE id = ?";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?";

    public UserCrudDaoImpl(ConnectorDB connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
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

    protected UserEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .withId(resultSet.getLong("id"))
                .withName(resultSet.getString("name"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withScore(resultSet.getInt("score"))
                .withRoles((Set<RoleEntity>) resultSet.getObject("role"))
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, UserEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getEmail());
        preparedStatement.setString(3, entity.getPassword());
        preparedStatement.setInt(4, entity.getScore());
        preparedStatement.setObject(5, entity.getRoleEntities());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, UserEntity entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(6, entity.getId());
    }
}