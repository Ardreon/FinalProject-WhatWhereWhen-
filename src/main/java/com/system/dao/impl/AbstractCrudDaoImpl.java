package com.system.dao.impl;

import org.apache.log4j.Logger;
import com.system.dao.ConnectorDB;
import com.system.dao.CrudDao;
import com.system.exceptions.DataBaseSqlRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudDaoImpl<E> implements CrudDao<E> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractCrudDaoImpl.class);
    protected static final String MESSAGE_ERROR = "SQL Error";

    protected final ConnectorDB connector;
    private final String findById;
    private final String save;
    private final String findAll;
    private final String update;
    private final String deleteById;

    protected AbstractCrudDaoImpl(ConnectorDB connector, String findById, String save,
                                  String findAll, String update, String deleteById) {
        this.connector = connector;
        this.findById = findById;
        this.save = save;
        this.findAll = findAll;
        this.update = update;
        this.deleteById = deleteById;
    }

    @Override
    public void save(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(save)) {
            insert(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(String.format(MESSAGE_ERROR, save, e));
            throw new DataBaseSqlRuntimeException("Insertion failed in save method", e);
        }
    }

    @Override
    public Optional<E> findById(Integer id) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(findById)) {
            preparedStatement.setInt(1, id);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.warn(String.format(MESSAGE_ERROR, findById, e));
            throw new DataBaseSqlRuntimeException("Operation failed in findById method", e);
        }
        return Optional.empty();
    }

    @Override
    public List<E> findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAll)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<E> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
            LOGGER.warn(String.format(MESSAGE_ERROR, findAll, e));
            throw new DataBaseSqlRuntimeException("Operation failed in findAll method", e);
        }
    }

    @Override
    public void update(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            updateValues(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(String.format(MESSAGE_ERROR, update, e));
            throw new DataBaseSqlRuntimeException("Update failed in update method", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(deleteById)) {
            preparedStatement.setInt(1, id);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.deleteRow();
                }
            }
        } catch (SQLException e) {
            LOGGER.warn(String.format(MESSAGE_ERROR, update, e));
            throw new DataBaseSqlRuntimeException("Delete failed in deleteById method", e);
        }
    }

    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected abstract void insert(PreparedStatement preparedStatement, E entity) throws SQLException;

    protected abstract void updateValues(PreparedStatement preparedStatement, E entity) throws SQLException;
}