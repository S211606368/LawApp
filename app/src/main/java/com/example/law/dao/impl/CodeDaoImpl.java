package com.example.law.dao.impl;

import com.example.law.dao.CodeDao;
import com.example.law.pojo.Code;
import com.example.law.service.function.SqlStatementFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * 法典接口的实现类
 *
 * @author 林书浩
 * @date 2020/07/29
 * @lastDate 2020/08/03
 */
public class CodeDaoImpl implements CodeDao {
    SqlStatementFunction sqlStatementFunction = new SqlStatementFunction();

    @Override
    public List<Code> selectCode() {
        List<Code> lawList = new ArrayList<>();
        String row = "code_id,code_name";
        String table = "CODES";

        List<String[]> stringList = sqlStatementFunction.selectView(row, table);
        for (String[] str : stringList) {
            Code code = new Code(Integer.parseInt(str[0]), str[1]);
            lawList.add(code);
        }

        return lawList;
    }
}
