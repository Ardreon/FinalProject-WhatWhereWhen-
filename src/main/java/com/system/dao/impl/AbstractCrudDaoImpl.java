package com.system.dao.impl;


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
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractCrudDaoImpl<E> implements CrudDao<E> {
    private static final Logger log = Logger.getLogger(AbstractCrudDaoImpl.class.getName());

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
            log.log(Level.SEVERE,"Insertion is failed: ", e);
            throw new DataBaseSqlRuntimeException("Insertion is failed", e);
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
            log.log(Level.SEVERE,"FindById operation is failed: ", e);
            throw new DataBaseSqlRuntimeException("FindById operation failed!", e);
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
            log.log(Level.SEVERE,"FindAll operation is failed: ", e);
            throw new DataBaseSqlRuntimeException("FindAll operation failed!", e);
        }
    }

    @Override
    public void update(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            updateValues(preparedStatement, entity);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE,"Update is failed: ", e);
            throw new DataBaseSqlRuntimeException("Update failed!", e);
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
            log.log(Level.SEVERE,"DeleteById operation is failed: ", e);
            throw new DataBaseSqlRuntimeException("Delete failed!", e);
        }
    }

    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected abstract void insert(PreparedStatement preparedStatement, E entity) throws SQLException;

    protected abstract void updateValues(PreparedStatement preparedStatement, E entity) throws SQLException;
}