package com.system.dao.impl;

import com.system.dao.ConnectorDB;
import com.system.domain.UserAccount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserAccountDaoImpl extends AbstractCrudDaoImpl<UserAccount> implements CrudPageableDao<UserAccount> {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM accounts WHERE id=?";

    public UserAccountDaoImpl(ConnectorDB connector) {
        super(connector, FIND_BY_ID_QUERY);
    }

    @Override
    public void save(UserAccount entity) {

    }

    @Override
    public List<UserAccount> findAll(int page, int itemsPerPage) {
        return Collections.emptyList();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void update(UserAccount entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    protected UserAccount mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new UserAccount(resultSet.getInt("id"), null);

    }
}