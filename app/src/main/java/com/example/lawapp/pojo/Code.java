package com.example.lawapp.pojo;

/**
 * 法典对象
 *
 * @author 林书浩
 * @date 2020/7/29
 * @lastdate 2020/7/31
 */
public class Code {
    private int codeId;
    private String codeName;

    /**
     *
     * @param codeId 法典id
     * @param codeName 法典名字
     */
    public Code(int codeId, String codeName) {
        this.codeId = codeId;
        this.codeName = codeName;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
}
