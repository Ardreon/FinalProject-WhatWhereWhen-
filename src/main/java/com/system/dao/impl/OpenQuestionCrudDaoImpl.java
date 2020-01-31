package com.system.dao.impl;

import com.system.dao.ConnectorDB;
import com.system.domain.OpenQuestion;
import com.system.domain.PromptType;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OpenQuestionCrudDaoImpl extends AbstractCrudDaoImpl<OpenQuestion> {

    protected static final Logger LOGGER = Logger.getLogger(VariantQuestionCrudDaoImpl.class);

    OpenQuestion openQuestion;

    private static final String SAVE_QUERY = "INSERT INTO variant_question (question_id, question, right_answer, right_answer_procent, wrong_answer1, wrong_answer1_procent, wrong_answer2, wrong_answer2_procent, wrong_answer3, wrong_answer3_procent) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM variant_question WHERE question_id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM variant_question";
    private static final String UPDATE_QUERY = "UPDATE variant_question SET question =?, right_answer=?, right_answer_procent=?,  wrong_answer1=?, wrong_answer1_procent=?, wrong_answer2=?, wrong_answer2_procent=?, wrong_answer3=?, wrong_answer3_procent=? WHERE question_id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE * FROM variant_question WHERE question_id = ?";

    private static final Logger log = Logger.getLogger(AbstractCrudDaoImpl.class.getName());

    private PromptType promptType;

    public OpenQuestionCrudDaoImpl(ConnectorDB connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, OpenQuestion entity) throws SQLException {
        preparedStatement.setString(1, entity.getQuestion());
        preparedStatement.setString(2, entity.getAnswer());
        preparedStatement.setString(3, entity.getPrompt());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, OpenQuestion entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(4, entity.getQuestionId());
    }
}
