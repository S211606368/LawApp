package com.example.law.service.function;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.law.service.sqlite.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * sql语句工具类
 *
 * @author 林书浩
 * @date 2020/07/29
 * @lastDate 2020/08/03
 */
public class SqlStatementFunction {

    private DatabaseOpenHelper databaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    Cursor cursor;

    public SqlStatementFunction() {
        databaseOpenHelper = DatabaseOpenHelper.DB_HELPER_INSTANCE;
    }

    public void openDatabase() {
        sqLiteDatabase = databaseOpenHelper.openDatabase();
    }

    public void closeDatabase() {
        databaseOpenHelper.close();
    }

    /**
     * 查询语句
     *
     * @param row   你所需要查找的列，每个列之间用","隔开
     * @param table 表名
     * @return String[]集合, 你查询了几行数组的大小就是几
     */
    public List<String[]> selectView(String row, String table) {
        List<String[]> list = new ArrayList<>();
        openDatabase();

        String[] rows = row.split("[,]");
        String sql = "select " + row + " from " + table;

        try {
            cursor = sqLiteDatabase.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    int rowLength = rows.length;
                    String[] str = new String[rowLength];
                    for (int i = 0; i < rowLength; i++) {
                        str[i] = cursor.getString(cursor.getColumnIndex(rows[i]));
                    }
                    list.add(str);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeDatabase();
        return list;
    }
}
