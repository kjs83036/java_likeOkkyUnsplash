package com.kjs.unsplash.controller;

public class test {
    
    public static void main(String[] args) {
        String s="댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력댓글입력";
        for(int i =199;i>180;i--){
            for(int j=1;j<6;j++){
                System.out.println("INSERT INTO COMMENTS (SEQ,FSEQ,WRITER,TEXT) VALUES((SELECT MAX(TO_NUMBER(SEQ)) FROM COMMENTS)+1,"+i+",'nickname"+j+"','"+s+"ninkname"+j+"');");
            }
        }
    }

}
