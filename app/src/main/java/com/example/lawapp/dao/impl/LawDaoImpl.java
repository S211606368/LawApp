package com.example.lawapp.dao.impl;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lawapp.dao.LawDao;
import com.example.lawapp.pojo.Law;
import com.example.lawapp.service.function.SqlStatement;
import com.example.lawapp.service.sqlite.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 法律接口的实现类
 *
 * @author 林书浩
 * @date 2020/7/29
 * @lastDate 2020/7/29
 */
public class LawDaoImpl implements LawDao {


    SqlStatement sqlStatement;

    public LawDaoImpl() {
        sqlStatement = new SqlStatement();
    }

    @Override
    public List<Law> selectLaw(int codeId) {
        List<Law> lawList = new ArrayList<>();
        String row = "law_id,law_name";
        String table = "LAWS where code_id = " + codeId;

        List<String[]> stringList = sqlStatement.selectView(row, table);
        for (String[] str : stringList) {
            Law law = new Law(Integer.parseInt(str[0]), str[1]);
            lawList.add(law);
        }

        return lawList;
    }
}
