package com.dynamic.foundations.sqlite;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author snowway
 * @since 4/21/11
 */
public class DefaultSqliteTemplateFactory implements SqliteTemplateFactory {

//    private static final Logger logger = LoggerFactory.getLogger(DefaultSqliteTemplateFactory.class.getSimpleName());

    protected SQLiteOpenHelper sqliteOpenHelper;

    private SqliteTemplate sqliteTemplate;

    public DefaultSqliteTemplateFactory(SQLiteOpenHelper sqliteOpenHelper) {
        this.sqliteOpenHelper = sqliteOpenHelper;
    }

    @Override
    public SqliteTemplate getSqliteTemplate() {
        if (sqliteTemplate == null || !sqliteTemplate.isOpen()) {
            createSqliteTemplate();
        }
        return sqliteTemplate;
    }

    private void createSqliteTemplate() {
//        SQLiteDatabase database = sqliteOpenHelper.getWritableDatabase();
        sqliteTemplate = new SqliteTemplate(sqliteOpenHelper);
    }
}
