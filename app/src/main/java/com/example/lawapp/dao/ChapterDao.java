package com.example.lawapp.dao;

import com.example.lawapp.pojo.Chapter;

import java.util.List;

/**
 * 章节接口
 *
 * @author 林书浩
 * @date 2020/7/31
 * @lastDate 2020/7/31
 */
public interface ChapterDao {
    /**
     * 查询章节
     *
     * @return 返回章节集合（目录）
     */
    public List<Chapter> selectChapter();
}
