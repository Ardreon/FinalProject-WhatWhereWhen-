package com.system.dao.impl;
import com.system.dao.ConnectorDB;
import com.system.domain.Configuration;
import com.system.domain.PromptType;
import com.system.exceptions.DataBaseSqlRuntimeException;
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

public class ConfigCrudDaoImpl extends AbstractCrudDaoImpl<Configuration> {

    private static final String SAVE = "INSERT INTO configuration (configuration_id, time, players_count, prompt_type, question_count) values(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM configuration WHERE configuration_id = ?";
    private static final String FIND_ALL = "SELECT * FROM configuration";
    private static final String UPDATE = "UPDATE configuration SET time =?, players_count=?, prompt_type=?, question_count=? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE * FROM configuration WHERE configuration_id = ?";

    private static final Logger log = Logger.getLogger(AbstractCrudDaoImpl.class.getName());

    private PromptType promptType;


    public ConfigCrudDaoImpl(ConnectorDB connector) {
        super(connector, SAVE, FIND_BY_ID, FIND_ALL, UPDATE, DELETE_BY_ID);
    }


    @Override
    public List<Configuration> findAll() {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_ALL)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Configuration> configurations = new ArrayList<>();
                while (resultSet.next()) {
                    final Configuration optionalConfiguration = mapResultSetToEntity(resultSet);
                    configurations.add(optionalConfiguration);
                }
                return configurations;
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE,"FindAll operation is failed: ", e);
            throw new DataBaseSqlRuntimeException("", e);
        }
    }



    protected Configuration mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Configuration.configurationBuilder()
                .withConfigId(resultSet.getLong("configuration_id"))
                .withTime(resultSet.getInt("time"))
                .withPlayersCount(resultSet.getInt("players_count"))
                .withPromptTypes(resultSet.getString("prompt_type"))
                .withQuestionCount(resultSet.getInt("question_count"))
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Configuration entity) throws SQLException {
        preparedStatement.setInt(1, entity.getTime());
        preparedStatement.setInt(2, entity.getPlayersCount());
        preparedStatement.setString(3, entity.getPromptTypes());
        preparedStatement.setInt(4, entity.getQuestionCount());

    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Configuration entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(5, entity.getConfigId());
    }

}