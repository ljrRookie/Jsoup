package com.ljr.jsoup.bean;

/**
 * Created by LinJiaRong on 2017/8/18.
 * TODO：
 */

public class InfoBean {
    private String authorName;          // 作者名称
    private String authorLink;          // 作者链接
    private String time;                // 更新时间
    private String authorImg;           // 作者头像

    private String title;               // 标题
    private String titleImg;
    private String titleLink;           // 标题链接
    private String content;             // 内容
    private String readNum;             // 阅读量
    private String talkNum;             // 评论
    private String bookMarkNum;         // 书签

    public InfoBean(String authorName, String authorLink, String time, String authorImg, String title, String titleImg, String titleLink, String content, String readNum, String talkNum, String bookMarkNum) {
        this.authorName = authorName;
        this.authorLink = authorLink;
        this.time = time;
        this.authorImg = authorImg;
        this.title = title;
        this.titleImg = titleImg;
        this.titleLink = titleLink;
        this.content = content;
        this.readNum = readNum;
        this.talkNum = talkNum;
        this.bookMarkNum = bookMarkNum;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthorImg() {
        return authorImg;
    }

    public void setAuthorImg(String authorImg) {
        this.authorImg = authorImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getTitleLink() {
        return titleLink;
    }

    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getTalkNum() {
        return talkNum;
    }

    public void setTalkNum(String talkNum) {
        this.talkNum = talkNum;
    }

    public String getBookMarkNum() {
        return bookMarkNum;
    }

    public void setBookMarkNum(String bookMarkNum) {
        this.bookMarkNum = bookMarkNum;
    }

    @Override
    public String toString() {
        return "InfoBean{" +
                "authorName='" + authorName + '\'' +
                ", authorLink='" + authorLink + '\'' +
                ", time='" + time + '\'' +
                ", authorImg='" + authorImg + '\'' +
                ", title='" + title + '\'' +
                ", titleImg='" + titleImg + '\'' +
                ", titleLink='" + titleLink + '\'' +
                ", content='" + content + '\'' +
                ", readNum='" + readNum + '\'' +
                ", talkNum='" + talkNum + '\'' +
                ", bookMarkNum='" + bookMarkNum + '\'' +
                '}';
    }
}