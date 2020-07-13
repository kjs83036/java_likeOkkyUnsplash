package com.kjs.unsplash.vo;

public class Board {

    private String seq;
    private String tag;
    private String writer;
    private String title;
    private String content;
    private int read;
    private String regDate;

    public Board(String seq, String tag, String writer, String title, String content, int read, String regDate) {
        //super();
        this.seq = seq;
        this.tag = tag;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.read = read;
        this.regDate = regDate;
    }

    public Board(String seq, String tag, String writer, String title, String content, int read) {
        //super();
        this.seq = seq;
        this.tag = tag;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.read = read;
    }

    public final String getSeq() {
        return seq;
    }

    public final void setSeq(String seq) {
        this.seq = seq;
    }

    public final String getTag() {
        return tag;
    }

    public final void setTag(String tag) {
        this.tag = tag;
    }

    public final String getWriter() {
        return writer;
    }

    public final void setWriter(String writer) {
        this.writer = writer;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final String getContent() {
        return content;
    }

    public final void setContent(String content) {
        this.content = content;
    }

    public final int getRead() {
        return read;
    }

    public final void setRead(int read) {
        this.read = read;
    }

    public final String getRegDate() {
        return regDate;
    }

    public final void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Board [seq=" + seq + ", tag=" + tag + ", writer=" + writer + ", title=" + title + ", content=" + content
                + ", read=" + read + ", regDate=" + regDate + "]";
    }

}
