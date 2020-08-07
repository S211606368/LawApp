package com.example.law.dao;

import com.example.law.pojo.Law;

import java.util.List;

/**
 * 法律接口
 *
 * @author 林书浩
 * @date 2020/07/29
 * @lastDate 2020/08/07
 */
public interface LawDao {
    /**
     * 查询所有法律
     *
     * @return 返回法律集合
     */
    public List<Law> selectLaw();

    /**
     * 查询指定法律
     *
     * @param lawId 所需要查询的法律id
     * @return 这条法律的信息
     */
    public List<Law> selectLaw(long lawId);


}
