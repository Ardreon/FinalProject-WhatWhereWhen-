package com.system.dao.impl;

import com.system.entity.*;
import org.apache.log4j.Logger;
import com.system.dao.ConnectorDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VariantQuestionCrudDaoImpl extends AbstractCrudDaoImpl<VariantQuestionEntity> {

    protected static final Logger LOGGER = Logger.getLogger(VariantQuestionCrudDaoImpl.class);

    private static final String SAVE_QUERY = "INSERT INTO open_question (question_id, question, answer, prompt) values(?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM open_question WHERE question_id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM open_question";
    private static final String UPDATE_QUERY = "UPDATE open_question SET question =?, answer=?, prompt=? WHERE question_id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE * FROM open_question WHERE question_id = ?";

    private static final Logger log = Logger.getLogger(AbstractCrudDaoImpl.class.getName());

    private PromptTypeEntity promptTypeEntity;

    public VariantQuestionCrudDaoImpl(ConnectorDB connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    protected VariantQuestionEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return VariantQuestionEntity.builder()
                .withQuestionId(resultSet.getLong("questionId"))
                .withQuestion(resultSet.getString("question"))
                .withRightAnswer(resultSet.getString("rightAnswer"))
                .withRightAnswerProcent(resultSet.getDouble("rightAnswerProcent"))
                .withWrongAnswer1(resultSet.getString("WrongAnswer1"))
                .withWrongAnswer1Procent(resultSet.getDouble("WrongAnswer1Procent"))
                .withWrongAnswer2(resultSet.getString("WrongAnswer2"))
                .withWrongAnswer2Procent(resultSet.getDouble("WrongAnswer2Procent"))
                .withWrongAnswer3(resultSet.getString("WrongAnswer3"))
                .withWrongAnswer3Procent(resultSet.getDouble("WrongAnswer3Procent"))
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, VariantQuestionEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getQuestion());
        preparedStatement.setString(2, entity.getRightAnswer());
        preparedStatement.setDouble(3, entity.getRightAnswerProcent());
        preparedStatement.setString(4, entity.getWrongAnswer1());
        preparedStatement.setDouble(5, entity.getWrongAnswer1Procent());
        preparedStatement.setString(6, entity.getWrongAnswer2());
        preparedStatement.setDouble(7, entity.getWrongAnswer2Procent());
        preparedStatement.setString(8, entity.getWrongAnswer3());
        preparedStatement.setDouble(9, entity.getWrongAnswer3Procent());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, VariantQuestionEntity entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(10, entity.getQuestionId());
    }
}
