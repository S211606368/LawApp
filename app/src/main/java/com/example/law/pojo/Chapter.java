package com.example.law.pojo;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * @author 林书浩
 * @date 2020/07/31
 * @lastDate 2020/08/07
 */
public class Chapter {
    @Id(autoincrement = true)
    private long chapterId;
    @NotNull
    private long lawId;
    @NotNull
    private String chapterName;
    @NotNull
    private String chapterContent;

    /**
     * @param chapterId      章节id
     * @param chapterName    章节名字
     * @param chapterContent 章节内容
     */
    public Chapter(long chapterId, String chapterName, String chapterContent) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.chapterContent = chapterContent;
    }

    /**
     * @param chapterId      章节id
     * @param lawId          所属法律id
     * @param chapterName    章节名字
     * @param chapterContent 章节内容
     */
    public Chapter(long chapterId, long lawId, String chapterName, String chapterContent) {
        this.chapterId = chapterId;
        this.lawId = lawId;
        this.chapterName = chapterName;
        this.chapterContent = chapterContent;
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

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent;
    }
}
