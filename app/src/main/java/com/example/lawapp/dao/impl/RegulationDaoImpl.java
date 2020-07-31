package com.example.lawapp.dao.impl;

import com.example.lawapp.dao.RegulationDao;
import com.example.lawapp.pojo.Chapter;
import com.example.lawapp.pojo.Regulation;
import com.example.lawapp.service.function.SqlStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * 法律条例接口的实现类
 *
 * @author LIN
 */
public class RegulationDaoImpl implements RegulationDao {

    SqlStatement sqlStatement;

    public RegulationDaoImpl() {
        sqlStatement = new SqlStatement();
    }

    @Override
    public List<Regulation> selectRegulation(int chapterId) {
        List<Regulation> regulationList = new ArrayList<>();

        String row = "regulation_id,chapter_id,regulation_name,regulation_content";
        String table = "REGULATIONS where chapter_id = " + chapterId;

        List<String[]> stringList = sqlStatement.selectView(row, table);
        for (String[] str : stringList) {
            Regulation regulation = new Regulation(Integer.parseInt(str[0]), Integer.parseInt(str[1]), str[2], str[3]);
            regulationList.add(regulation);
        }
        return regulationList;
    }
}
