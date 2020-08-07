package com.example.law.dao;

import com.example.law.pojo.Chapter;

import java.util.List;

/**
 * 章节接口
 *
 * @author 林书浩
 * @date 2020/07/31
 * @lastDate 2020/08/07
 */
public interface ChapterDao {

    /**
     * 查询所有章节
     *
     * @return 返回章节集合
     */
    public List<Chapter> selectChapter();

    /**
     * 查询指定法律下的章节
     *
     * @param lawId 所属法律的id
     * @return 返回章节集合（目录）
     */
    public List<Chapter> selectChapter(long lawId);
}
