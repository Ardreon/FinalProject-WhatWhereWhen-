package com.system.dao.impl;

import com.system.dao.ConnectorDB;
import com.system.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameCrudDaoImpl extends AbstractCrudDaoImpl<GameEntity> {

    private static final String SAVE_QUERY = "INSERT INTO game (time, openQuestionCount, variantQuestionCount, promptTypeId, userId, openQuestionId, variantQuestionId) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM game WHERE gameId = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM game";
    private static final String UPDATE_QUERY = "UPDATE game SET time =?, openQuestionCount=?, variantQuestionCount=?, promptTypeId=?, userId=?, openQuestionId=?, variantQuestionId=? WHERE gameId = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE * FROM game WHERE gameId = ?";

    public GameCrudDaoImpl(ConnectorDB connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    protected GameEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return GameEntity.builder()
                .withGameId(resultSet.getLong("gameId"))
                .withTime(resultSet.getInt("time"))
                .withOpenQuestionCount(resultSet.getInt("openQuestionCount"))
                .withVariantQuestionCount(resultSet.getInt("variantQuestionCount"))
                .withPromptTypeEntity((PromptTypeEntity) resultSet.getObject("promptTypeId"))
                .withUserEntity((UserEntity) resultSet.getObject("userId"))
                .withOpenQuestionEntity((OpenQuestionEntity) resultSet.getObject("openQuestionId"))
                .withVariantQuestionEntity((VariantQuestionEntity) resultSet.getObject("variantQuestionId"))
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, GameEntity entity) throws SQLException {
        preparedStatement.setInt(1, entity.getTime());
        preparedStatement.setInt(2, entity.getOpenQuestionCount());
        preparedStatement.setInt(3, entity.getVariantQuestionCount());
        preparedStatement.setObject(4, entity.getPromptTypeEntity());
        preparedStatement.setObject(5, entity.getUserEntity());
        preparedStatement.setObject(6, entity.getOpenQuestionEntity());
        preparedStatement.setObject(7, entity.getVariantQuestionEntity());

    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, GameEntity entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(8, entity.getGameId());
    }

}