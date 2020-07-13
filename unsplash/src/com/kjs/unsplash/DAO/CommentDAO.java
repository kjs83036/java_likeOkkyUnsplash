package com.kjs.unsplash.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kjs.unsplash.jdbc.JdbcUtil;
import com.kjs.unsplash.vo.Comment;

public class CommentDAO {

    JdbcUtil ju;
    Connection con;

    public CommentDAO(JdbcUtil ju, Connection con) {
        super();
        this.ju = ju;
        this.con = con;
    }

    public Comment getComment(String seq) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Comment comment = null;
        String sql = "SELECT * FROM COMMENTS WHERE SEQ=" + seq;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                comment = new Comment(rs.getString("SEQ"), rs.getString("FSEQ"), rs.getString("WRITER"), rs.getString("TEXT"), rs.getInt("LIKES"), rs.getString("REGDATE"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            ju.close(rs);
            ju.close(ps);
        }

        return comment;
    }

    public List<Comment> ListComment(String fSeq) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Comment> commentList = new ArrayList<Comment>();
        String sql = "SELECT * FROM COMMENTS WHERE FSEQ=" + fSeq;

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                commentList.add(new Comment(rs.getString("SEQ"), rs.getString("FSEQ"), rs.getString("WRITER"), rs.getString("TEXT"), rs.getInt("LIKES"), rs.getString("REGDATE")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            ju.close(rs);
            ju.close(ps);
        }

        return commentList;

    }

    public Map<String, String> countComment() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, String> countMap = new HashMap<String, String>();
        String sql = "SELECT FSEQ,COUNT(FSEQ) FROM COMMENTS GROUP BY FSEQ";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                countMap.put(rs.getString("FSEQ"), Integer.toString(rs.getInt("COUNT(FSEQ)")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            ju.close(rs);
            ju.close(ps);
        }

        return countMap;
    }
    
    public int insertComment(Comment comment){
        
        PreparedStatement ps= null;
        int insertCount=0;
        String sql="INSERT INTO COMMENTS (SEQ,FSEQ,WRITER,TEXT) VALUES ((SELECT MAX(TO_NUMBER(SEQ)) FROM COMMENTS)+1,?,?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, comment.getfSeq());
            ps.setString(2, comment.getWriter());
            ps.setString(3, comment.getText());
            insertCount=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            ju.close(ps);
        }
        
        return insertCount;
        
    }

    public int deleteComment(String seq) {
        // TODO Auto-generated method stub
        PreparedStatement ps = null;
        String sql="DELETE FROM COMMENTS WHERE SEQ="+seq;
        int deleteCount=0;
        
        try {
            ps=con.prepareStatement(sql);
            deleteCount=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            ju.close(ps);
        }
        
        
        return deleteCount;
        
    }

    public int updateComment(Comment comment) {
        // TODO Auto-generated method stub
        
        PreparedStatement ps = null;
        int updateCount=0;
        String sql="UPDATE COMMENTS SET TEXT =? WHERE SEQ="+comment.getSeq();
        
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, comment.getText());
            updateCount=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            ju.close(ps);
        }
        
        return updateCount;
        
    }

    public int updateLike(String seq) {
        // TODO Auto-generated method stub
        PreparedStatement ps = null;
        int updateCount=0;
        String sql="UPDATE COMMENTS SET LIKES =LIKES+1 WHERE SEQ="+seq;
        
        try {
            ps=con.prepareStatement(sql);
            updateCount=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            ju.close(ps);
        }
        
        return updateCount;
    }

}
