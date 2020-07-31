package com.example.lawapp.dao.impl;

import com.example.lawapp.dao.CodeDao;
import com.example.lawapp.pojo.Code;
import com.example.lawapp.pojo.Law;
import com.example.lawapp.service.function.SqlStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * 法典接口的实现类
 *
 * @author 林书浩
 * @date 2020/7/29
 * @lastDate 2020/7/29
 */
public class CodeDaoImpl implements CodeDao {
    SqlStatement sqlStatement = new SqlStatement();

    @Override
    public List<Code> selectCode() {
        List<Code> lawList = new ArrayList<>();
        String row = "code_id,code_name";
        String table = "CODES";

        List<String[]> stringList = sqlStatement.selectView(row, table);
        for (String[] str : stringList) {
            Code code = new Code(Integer.parseInt(str[0]), str[1]);
            lawList.add(code);
        }

        return lawList;
    }
}
