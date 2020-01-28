package com.system.dao.impl;

import com.system.dao.ConnectorDB;
import com.system.exceptions.DataBaseSqlRuntimeException;
import com.system.dao.UserDao;
import com.system.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCrudDaoImpl extends AbstractCrudDaoImpl<User> implements UserDao {

    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email=?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM users";

    public UserCrudDaoImpl(ConnectorDB connector) {
        super(connector,FIND_BY_ID_QUERY);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_BY_EMAIL_QUERY)) {
            preparedStatement.setString(1, email);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToEntity(resultSet));
                }
            }

        } catch (SQLException e) {
            //log
            throw new DataBaseSqlRuntimeException("", e);
        }

        return Optional.empty();
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public List<User> findAll(int page, int itemPerPage) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_ALL_QUERY)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    final User optionalUser = mapResultSetToEntity(resultSet);
                    users.add(optionalUser);
                }
                return users;
            }

        } catch (SQLException e) {
            //log
            throw new DataBaseSqlRuntimeException("", e);
        }
    }

    @Override
    public long count() {
        return 0;
    }

    protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .withId(resultSet.getInt("id"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withAccountID(resultSet.getInt("account_ID"))
                .build();
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}