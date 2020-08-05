package com.example.law.dao.impl;

import com.example.law.dao.RegulationDao;
import com.example.law.pojo.Regulation;
import com.example.law.service.function.SqlStatementFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * 法律条例接口的实现类
 *
 * @author LIN
 * @date 2020/07/29
 * @lastDate 2020/08/05
 */
public class RegulationDaoImpl implements RegulationDao {

    SqlStatementFunction sqlStatementFunction;

    public RegulationDaoImpl() {
        sqlStatementFunction = new SqlStatementFunction();
    }

    @Override
    public List<Regulation> selectRegulation() {
        List<Regulation> regulationList = new ArrayList<>();

        String row = "regulation_id,chapter_id,regulation_name,regulation_content";
        String table = "REGULATIONS";

        List<String[]> stringList = sqlStatementFunction.selectView(row, table);
        for (String[] str : stringList) {
            Regulation regulation = new Regulation(Integer.parseInt(str[0]), Integer.parseInt(str[1]), str[2], str[3]);
            regulationList.add(regulation);
        }
        return regulationList;
    }

    @Override
    public List<Regulation> selectRegulation(int chapterId) {
        List<Regulation> regulationList = new ArrayList<>();

        String row = "regulation_id,chapter_id,regulation_name,regulation_content";
        String table = "REGULATIONS where chapter_id = " + chapterId;

        List<String[]> stringList = sqlStatementFunction.selectView(row, table);
        for (String[] str : stringList) {
            Regulation regulation = new Regulation(Integer.parseInt(str[0]), Integer.parseInt(str[1]), str[2], str[3]);
            regulation.setChapterId(chapterId);
            regulationList.add(regulation);
        }
        return regulationList;
    }
}
