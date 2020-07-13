package com.kjs.unsplash.vo;

public class Comment {

    private String seq;
    private String fSeq;
    private String writer;
    private String text;
    private int likes;
    private String regDate;

    public Comment(String seq, String fSeq, String writer, String text, int likes, String regDate) {
        super();
        this.seq = seq;
        this.fSeq = fSeq;
        this.writer = writer;
        this.text = text;
        this.likes = likes;
        this.regDate = regDate;
    }

    public final String getSeq() {
        return seq;
    }

    public final void setSeq(String seq) {
        this.seq = seq;
    }

    public final String getfSeq() {
        return fSeq;
    }

    public final void setfSeq(String fSeq) {
        this.fSeq = fSeq;
    }

    public final String getWriter() {
        return writer;
    }

    public final void setWriter(String writer) {
        this.writer = writer;
    }

    public final String getText() {
        return text;
    }

    public final void setText(String text) {
        this.text = text;
    }

    public final int getLikes() {
        return likes;
    }

    public final void setLikes(int likes) {
        this.likes = likes;
    }

    public final String getRegDate() {
        return regDate;
    }

    public final void setRegDate(String regDate) {
        this.regDate = regDate;
    }

}
