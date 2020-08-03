package com.example.law.dao;

import com.example.law.pojo.Law;

import java.util.List;

/**
 * 法律接口
 *
 * @author 林书浩
 * @date 2020/07/29
 * @lastDate 2020/08/03
 */
public interface LawDao {
    /**
     * 查询所有法律
     *
     * @param codeId 所属法典id
     * @return 返回法律集合
     */
    public List<Law> selectLaw(int codeId);


}
