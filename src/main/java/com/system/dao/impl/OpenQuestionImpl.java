package com.system.dao.impl;

import com.system.dao.ConnectorDB;
import com.system.entity.OpenQuestionEntity;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OpenQuestionImpl extends AbstractCrudDaoImpl<OpenQuestionEntity> {

    protected static final Logger LOGGER = Logger.getLogger(OpenQuestionImpl.class);

    private static final String SAVE_QUERY = "INSERT INTO open_question (questionId, question, answer, prompt) values(?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM open_question WHERE questionId = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM open_question";
    private static final String UPDATE_QUERY = "UPDATE open_question SET question =?, answer=?, prompt=? WHERE questionId = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE * FROM open_question WHERE questionId = ?";

    public OpenQuestionImpl(ConnectorDB connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    protected OpenQuestionEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return OpenQuestionEntity.builder()
                .withQuestionId(resultSet.getLong("questionId"))
                .withQuestion(resultSet.getString("question"))
                .withAnswer(resultSet.getString("answer"))
                .withPrompt(resultSet.getString("prompt"))
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, OpenQuestionEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getQuestion());
        preparedStatement.setString(2, entity.getAnswer());
        preparedStatement.setString(3, entity.getPrompt());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, OpenQuestionEntity entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(4, entity.getQuestionId());
    }
}
