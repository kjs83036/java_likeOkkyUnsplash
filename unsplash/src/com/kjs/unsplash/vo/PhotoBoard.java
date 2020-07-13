package com.kjs.unsplash.vo;

public class PhotoBoard {
    private String seq;
    private String imageURI;
    private String tag;
    private String writer;
    private int likes;
    private int views;
    private int downloads;
    private String regDate;

    public PhotoBoard(String seq, String imageURI, String tag, String writer, int likes, int views, int downloads) {
        super();
        this.seq=seq;
        this.imageURI = imageURI;
        this.tag = tag;
        this.writer = writer;
        this.likes = likes;
        this.views = views;
        this.downloads = downloads;
    }

    public PhotoBoard(String seq, String imageURI, String tag, String writer, int likes, int views, int downloads, String regDate) {
        super();
        this.seq=seq;
        this.imageURI = imageURI;
        this.tag = tag;
        this.writer = writer;
        this.likes = likes;
        this.views = views;
        this.downloads = downloads;
        this.regDate = regDate;
    }

    
    public final String getSeq() {
        return seq;
    }

    public final void setSeq(String seq) {
        this.seq = seq;
    }

    public final String getImageURI() {
        return imageURI;
    }

    public final void setImageURI(String imageURI) {
        this.imageURI = imageURI;
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

    public final int getLikes() {
        return likes;
    }

    public final void setLikes(int likes) {
        this.likes = likes;
    }

    public final int getViews() {
        return views;
    }

    public final void setViews(int views) {
        this.views = views;
    }

    public final int getDownloads() {
        return downloads;
    }

    public final void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public final String getRegDate() {
        return regDate;
    }

    public final void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "PhotoBoard [imageUrl=" + imageURI + ", tag=" + tag + ", writer=" + writer + ", likes=" + likes + ", views=" + views + ", downloads=" + downloads + ", regDate=" + regDate + "]";
    }

}
