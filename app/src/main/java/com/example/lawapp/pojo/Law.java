package com.example.lawapp.pojo;

/**
 * 法律对象
 *
 * @author 林书浩
 * @date 2020/7/29
 * @lastDate 2020/7/31
 */
public class Law {

    /**
     * 法律id，每个法律对应一个id
     */
    private int lawId;

    /**
     * 法典id，指向该法律对应的法典
     */
    private int codeId;

    /**
     * 法律名字，该法律的名字
     */
    private String lawName;

    /**
     * 公布时间，该法律公布的时间
     */
    private String publicDate;

    /**
     * 实行时间，该法律实行的时间
     */
    private String implementDate;

    /**
     * 法律介绍，介绍该法律的发布情况
     */
    private String lawContent;

    /**
     *
     * @param lawId 法律id（每本法典下的子法）
     * @param lawName 法律名字
     */
    public Law(int lawId, String lawName) {
        this.lawId = lawId;
        this.lawName = lawName;
    }

    public Law(int lawId, int codeId, String lawName, String publicDate, String implementDate, String lawContent) {
        this.lawId = lawId;
        this.codeId = codeId;
        this.lawName = lawName;
        this.publicDate = publicDate;
        this.implementDate = implementDate;
        this.lawContent = lawContent;
    }


    public int getLawId() {
        return lawId;
    }

    public void setLawId(int lawId) {
        this.lawId = lawId;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getLawName() {
        return lawName;
    }

    public void setLawName(String lawName) {
        this.lawName = lawName;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public String getImplementDate() {
        return implementDate;
    }

    public void setImplementDate(String implementDate) {
        this.implementDate = implementDate;
    }

    public String getLawContent() {
        return lawContent;
    }

    public void setLawContent(String lawContent) {
        this.lawContent = lawContent;
    }
}
