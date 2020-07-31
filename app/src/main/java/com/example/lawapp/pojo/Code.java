package com.example.lawapp.pojo;

/**
 * 法典对象
 *
 * @author 林书浩
 * @date 2020/7/29
 * @lastdate 2020/7/29
 */
public class Code {
    private int codeId;
    private String codeName;

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
