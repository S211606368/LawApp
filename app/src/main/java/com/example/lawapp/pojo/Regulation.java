package com.example.lawapp.pojo;

/**
 * @author 林书浩
 * @date 2020/07/31
 * @lastDate 2020/07/31
 */
public class Regulation {
    private int regulationId;
    private int chapterId;
    private int lawId;
    private String regulationName;
    private String regulationContent;

    /**
     * @param regulationId      条例id
     * @param chapterId         条例所属章节的id
     * @param regulationName    条例名字
     * @param regulationContent 条例内容
     */
    public Regulation(int regulationId, int chapterId, String regulationName, String regulationContent) {
        this.regulationId = regulationId;
        this.chapterId = chapterId;
        this.regulationName = regulationName;
        this.regulationContent = regulationContent;
    }

    public int getRegulationId() {
        return regulationId;
    }

    public void setRegulationId(int regulationId) {
        this.regulationId = regulationId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getLawId() {
        return lawId;
    }

    public void setLawId(int lawId) {
        this.lawId = lawId;
    }

    public String getRegulationName() {
        return regulationName;
    }

    public void setRegulationName(String regulationName) {
        this.regulationName = regulationName;
    }

    public String getRegulationContent() {
        return regulationContent;
    }

    public void setRegulationContent(String regulationContent) {
        this.regulationContent = regulationContent;
    }
}
