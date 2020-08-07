package com.example.law.dao.impl;

import com.example.law.dao.LawDao;
import com.example.law.pojo.Law;
import com.example.law.service.function.SqlStatementFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * 法律接口的实现类
 *
 * @author 林书浩
 * @date 2020/07/29
 * @lastDate 2020/08/07
 */
public class LawDaoImpl implements LawDao {


    SqlStatementFunction sqlStatementFunction;

    public LawDaoImpl() {
        sqlStatementFunction = new SqlStatementFunction();
    }

    @Override
    public List<Law> selectLaw() {
        List<Law> lawList = new ArrayList<>();
        String row = "law_id,law_name";
        String table = "LAWS";

        List<String[]> stringList = sqlStatementFunction.selectView(row, table);
        for (String[] str : stringList) {
            Law law = new Law(Integer.parseInt(str[0]), str[1]);
            lawList.add(law);
        }

        return lawList;
    }

    @Override
    public List<Law> selectLaw(long lawId) {
        List<Law> lawList = new ArrayList<>();
        String row = "law_id,law_name";
        String table = "LAWS where law_id = " + lawId;

        List<String[]> stringList = sqlStatementFunction.selectView(row, table);
        for (String[] str : stringList) {
            Law law = new Law(Integer.parseInt(str[0]), str[1]);
            lawList.add(law);
        }

        return lawList;
    }
}
