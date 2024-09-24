package com.ism.core.Repository.impl;

import com.ism.core.Database.impl.DatabaseImpl;
import com.ism.core.Repository.Repository;
import java.sql.*;

public abstract class RepositoryBDImpl<T> extends DatabaseImpl implements Repository<T> {
    protected String tableName;
    public abstract T convertToObject(ResultSet rs) throws SQLException;
}
