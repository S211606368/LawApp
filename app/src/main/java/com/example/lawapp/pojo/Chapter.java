package com.example.lawapp.pojo;

/**
 * @author 林书浩
 * @date 2020/7/31
 * @lastDate 2020/7/31
 */
public class Chapter {
    private int chapterId;
    private int lawId;
    private String chapterName;
    private String chapterContent;

    /**
     *
     * @param chapterId 章节id
     * @param chapterName 章节名字
     * @param chapterContent 章节内容
     */
    public Chapter(int chapterId,String chapterName,String chapterContent){
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.chapterContent = chapterContent;
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
