package com.system.dao.impl;
import com.system.dao.ConnectorDB;
import com.system.domain.Game;
import com.system.domain.PromptType;
import com.system.exceptions.DataBaseSqlRuntimeException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameCrudDaoImpl extends AbstractCrudDaoImpl<Game> {

    private static final String SAVE = "INSERT INTO configuration (configuration_id, time, players_count, prompt_type, question_count) values(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM configuration WHERE configuration_id = ?";
    private static final String FIND_ALL = "SELECT * FROM configuration";
    private static final String UPDATE = "UPDATE configuration SET time =?, players_count=?, prompt_type=?, question_count=? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE * FROM configuration WHERE configuration_id = ?";


    private PromptType promptType;


    public GameCrudDaoImpl(ConnectorDB connector) {
        super(connector, SAVE, FIND_BY_ID, FIND_ALL, UPDATE, DELETE_BY_ID);
    }

    protected Game mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Game.builder()
                .withGameId(resultSet.getLong("game_id"))
                .withTime(resultSet.getInt("time"))
                .withPlayersCount(resultSet.getInt("players_count"))
                .withPromptTypes(resultSet.getObject("prompt_type", PromptType.class))
                .withQuestionCount(resultSet.getInt("question_count"))
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Game entity) throws SQLException {
        preparedStatement.setInt(1, entity.getTime());
        preparedStatement.setInt(2, entity.getPlayersCount());
        preparedStatement.setObject(3, entity.getPromptTypes());
        preparedStatement.setInt(4, entity.getQuestionCount());

    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Game entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(5, entity.getGameId());
    }

}