package com.system.dao.impl;
import com.system.dao.ConnectorDB;
import com.system.domain.Configuration;
import com.system.domain.PromptType;
import com.system.domain.VariantQuestion;
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

public class VariantQuestionCrudDaoImpl extends AbstractCrudDaoImpl<Configuration> {

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


    @Override
    public List<VariantQuestion> findAll() {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_ALL)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<VariantQuestion> variantQuestions = new ArrayList<>();
                while (resultSet.next()) {
                    final VariantQuestion optionalVariantQuestion = mapResultSetToEntity(resultSet);
                    variantQuestions.add(optionalVariantQuestion);
                }
                return variantQuestions;
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE,"FindAll operation is failed: ", e);
            throw new DataBaseSqlRuntimeException("", e);
        }
    }



    protected VariantQuestion mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return VariantQuestion.builder()
                .withQuestion_id(resultSet.getLong("question_id"))
                .withRight_answer(resultSet.getString("question"))
                .withRight_answer((resultSet.getString("right_answer"))
                .withRight_answer_procent(resultSet.getDouble("right_answer_procent"))
                .withWrong_answer1(resultSet.getString("prompt_type"))
                .withWrong_answer1_procent(resultSet.getInt("question_count"))
                .withWrong_answer2(resultSet.getInt("question_count"))
                .withWrong_answer2_procent(resultSet.getInt("question_count"))
                .withWrong_answer3(resultSet.getInt("question_count"))
                .withWrong_answer3_procent(resultSet.getInt("question_count"))
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
