package com.system.dao.impl;

import com.system.domain.*;
import org.apache.log4j.Logger;
import com.system.dao.ConnectorDB;
import com.system.exceptions.DataBaseSqlRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VariantQuestionCrudDaoImpl extends AbstractCrudDaoImpl<VariantQuestion> {

    protected static final Logger LOGGER = Logger.getLogger(VariantQuestionCrudDaoImpl.class);

    private static final String SAVE = "INSERT INTO variant_question (question_id, question, right_answer, right_answer_procent, wrong_answer1, wrong_answer1_procent, wrong_answer2, wrong_answer2_procent, wrong_answer3, wrong_answer3_procent) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM variant_question WHERE question_id = ?";
    private static final String FIND_ALL = "SELECT * FROM variant_question";
    private static final String UPDATE = "UPDATE variant_question SET question =?, right_answer=?, right_answer_procent=?,  wrong_answer1=?, wrong_answer1_procent=?, wrong_answer2=?, wrong_answer2_procent=?, wrong_answer3=?, wrong_answer3_procent=? WHERE question_id = ?";
    private static final String DELETE_BY_ID = "DELETE * FROM variant_question WHERE question_id = ?";

    private static final Logger log = Logger.getLogger(AbstractCrudDaoImpl.class.getName());

    private PromptType promptType;

    public VariantQuestionCrudDaoImpl(ConnectorDB connector) {
        super(connector, SAVE, FIND_BY_ID, FIND_ALL, UPDATE, DELETE_BY_ID);
    }

    protected VariantQuestion mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return VariantQuestion.builder()
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
    protected void insert(PreparedStatement preparedStatement, VariantQuestion entity) throws SQLException {
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
    protected void updateValues(PreparedStatement preparedStatement, VariantQuestion entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(10, entity.getQuestionId());
    }
}
