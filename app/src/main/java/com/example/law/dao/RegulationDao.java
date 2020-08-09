package com.example.law.dao;

import com.example.law.pojo.Regulation;

import java.util.List;

/**
 * 条例接口
 *
 * @author LIN
 * @date 2020/07/31
 * @lastDate 2020/08/07
 */
public interface RegulationDao {

    /**
     * 查询所有法律条例
     *
     * @return 返回法律条例集合
     */
    public List<Regulation> selectRegulation();

    /**
     * 查询某一章节下的条例
     *
     * @param chapterId 传入所属章节的id
     * @return 返回条例集合
     */
    public List<Regulation> selectRegulation(long chapterId);

    /**
     * 查询含有关键字的条例
     *
     * @param chapterId 传入所属章节的id
     * @param context   需要查询的关键字
     * @return 返回条例集合
     */
    public List<Regulation> selectRegulation(long chapterId, String context);
}
