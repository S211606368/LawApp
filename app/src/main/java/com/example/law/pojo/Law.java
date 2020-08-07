package com.example.law.pojo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 法律对象
 *
 * @author 林书浩
 * @date 2020/07/029
 * @lastDate 2020/08/07
 */
@Entity
public class Law {


    /**
     * 法律id，每个法律对应一个id
     */
    @Id(autoincrement = true)
    private long lawId;

    /**
     * 法典id，指向该法律对应的法典
     */
    private long codeId;

    /**
     * 法律名字，该法律的名字
     */
    @NotNull
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
     * @param lawId   法律id（每本法典下的子法）
     * @param lawName 法律名字
     */
    public Law(long lawId, String lawName) {
        this.lawId = lawId;
        this.lawName = lawName;
    }


    @Generated(hash = 1586998742)
    public Law(long lawId, long codeId, @NotNull String lawName, String publicDate,
               String implementDate, String lawContent) {
        this.lawId = lawId;
        this.codeId = codeId;
        this.lawName = lawName;
        this.publicDate = publicDate;
        this.implementDate = implementDate;
        this.lawContent = lawContent;
    }


    @Generated(hash = 553976986)
    public Law() {
    }


    public long getLawId() {
        return lawId;
    }

    public void setLawId(long lawId) {
        this.lawId = lawId;
    }

    public long getCodeId() {
        return codeId;
    }

    public void setCodeId(long codeId) {
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
