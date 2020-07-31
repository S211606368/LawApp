package com.example.lawapp.dao;

import com.example.lawapp.pojo.Code;

import java.util.List;

/**
 * 法典接口
 *
 * @author 林书浩
 * @date 2020/7/29
 * @lastDate 2020/7/29
 */
public interface CodeDao {
    /**
     * 查询法典
     *
     * @return 法典集合
     */
    public List<Code> selectCode();
}
