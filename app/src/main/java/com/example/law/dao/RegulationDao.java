package com.example.law.dao;

import com.example.law.pojo.Regulation;

import java.util.List;

/**
 * 条例接口
 *
 * @author LIN
 * @date 2020/07/31
 * @lastDate 2020/08/03
 */
public interface RegulationDao {
    /**
     * 查询条例
     *
     * @param chapterId 传入所属章节的id
     * @return 返回条例集合（正文）
     */
    public List<Regulation> selectRegulation(int chapterId);
}