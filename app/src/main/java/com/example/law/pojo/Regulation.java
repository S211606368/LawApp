package com.example.law.pojo;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * @author 林书浩
 * @date 2020/07/31
 * @lastDate 2020/08/07
 */
public class Regulation {
    @Id(autoincrement = true)
    private long regulationId;
    @NotNull
    private long chapterId;
    private long lawId;
    @NotNull
    private String regulationName;
    @NotNull
    private String regulationContent;

    /**
     * @param regulationId      条例id
     * @param chapterId         条例所属章节的id
     * @param regulationName    条例名字
     * @param regulationContent 条例内容
     */
    public Regulation(long regulationId, long chapterId, String regulationName, String regulationContent) {
        this.regulationId = regulationId;
        this.chapterId = chapterId;
        this.regulationName = regulationName;
        this.regulationContent = regulationContent;
    }

    public long getRegulationId() {
        return regulationId;
    }

    public void setRegulationId(long regulationId) {
        this.regulationId = regulationId;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public long getLawId() {
        return lawId;
    }

    public void setLawId(long lawId) {
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
