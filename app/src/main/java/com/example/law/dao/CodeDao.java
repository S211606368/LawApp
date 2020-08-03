package com.example.law.dao;

import com.example.law.pojo.Code;

import java.util.List;

/**
 * 法典接口
 *
 * @author 林书浩
 * @date 2020/07/29
 * @lastDate 2020/08/03
 */
public interface CodeDao {
    /**
     * 查询法典
     *
     * @return 法典集合
     */
    public List<Code> selectCode();
}
