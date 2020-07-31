package com.example.lawapp.dao.impl;

import com.example.lawapp.dao.ChapterDao;
import com.example.lawapp.pojo.Chapter;
import com.example.lawapp.pojo.Law;
import com.example.lawapp.service.function.SqlStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节接口的实现类
 *
 * @author 林书浩
 * @date 2020/7/31
 * @lastDate 2020/7/31
 */
public class ChapterDaoImpl implements ChapterDao {

    SqlStatement sqlStatement;

    public ChapterDaoImpl() {
        sqlStatement = new SqlStatement();
    }

    @Override
    public List<Chapter> selectChapter(int lawId) {
        List<Chapter> chapterList = new ArrayList<>();
        String row = "chapter_id,chapter_name,chapter_content";
        String table = "CHAPTERS where law_id = " + lawId;

        List<String[]> stringList = sqlStatement.selectView(row, table);
        for (String[] str : stringList) {
            Chapter chapter = new Chapter(Integer.parseInt(str[0]), str[1], str[2]);
            chapterList.add(chapter);
        }

        return chapterList;
    }
}
