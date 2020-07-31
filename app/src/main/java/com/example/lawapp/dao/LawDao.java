package com.example.lawapp.dao;

import com.example.lawapp.pojo.Law;

import java.util.List;

/**
 * 法律接口
 *
 * @author 林书浩
 * @date 2020/7/29
 * @lastDate 2020/7/29
 */
public interface LawDao {
    /**
     * 查询所有法律
     *
     * @return 返回法律集合
     */
    public List<Law> selectLaw();


}
