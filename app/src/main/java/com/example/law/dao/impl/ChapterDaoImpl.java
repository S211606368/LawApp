package com.example.law.dao.impl;

import com.example.law.dao.ChapterDao;
import com.example.law.pojo.Chapter;
import com.example.law.service.function.SqlStatementFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节接口的实现类
 *
 * @author 林书浩
 * @date 2020/07/31
 * @lastDate 2020/08/03
 */
public class ChapterDaoImpl implements ChapterDao {

    SqlStatementFunction sqlStatementFunction;

    public ChapterDaoImpl() {
        sqlStatementFunction = new SqlStatementFunction();
    }

    @Override
    public List<Chapter> selectChapter(int lawId) {
        List<Chapter> chapterList = new ArrayList<>();
        String row = "chapter_id,chapter_name,chapter_content";
        String table = "CHAPTERS where law_id = " + lawId;

        List<String[]> stringList = sqlStatementFunction.selectView(row, table);
        for (String[] str : stringList) {
            Chapter chapter = new Chapter(Integer.parseInt(str[0]), str[1], str[2]);
            chapterList.add(chapter);
        }

        return chapterList;
    }
}
