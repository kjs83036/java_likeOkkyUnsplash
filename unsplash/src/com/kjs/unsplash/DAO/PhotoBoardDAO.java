package com.kjs.unsplash.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kjs.unsplash.jdbc.JdbcUtil;
import com.kjs.unsplash.vo.PhotoBoard;

public class PhotoBoardDAO {
    
    JdbcUtil ju;
    Connection con;
    public PhotoBoardDAO(JdbcUtil ju, Connection con) {
        super();
        this.ju = ju;
        this.con = con;
    }
    
    public int insertPhotoBoard(PhotoBoard photoBoard){
        
        PreparedStatement ps= null;
        int insertCount=0;
        String sql="INSERT INTO PHOTOBOARDS (SEQ,TAG,WRITER,IMAGEURI) VALUES ((SELECT MAX(TO_NUMBER(SEQ)) FROM PHOTOBOARDS)+1,?,?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, photoBoard.getTag());
            ps.setString(2, photoBoard.getWriter());
            ps.setString(3, photoBoard.getImageURI());
            insertCount=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            ju.close(ps);
        }
        
        return insertCount;
        
    }

    public List<PhotoBoard> listPhotoBoard() {
    // TODO Auto-generated method stub
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<PhotoBoard>photoBoardList = new ArrayList<PhotoBoard>();
    String sql="SELECT * FROM PHOTOBOARDS ORDER BY TO_NUMBER(SEQ) DESC ";
    
    try {
        ps=con.prepareStatement(sql);
        rs=ps.executeQuery();
        while(rs.next()){
            photoBoardList.add(new PhotoBoard(rs.getString("SEQ"),rs.getString("IMAGEURI"),rs.getString("TAG"),rs.getString("WRITER"),rs.getInt("LIKES"),rs.getInt("VIEWS"),rs.getInt("DOWNLOADS"),rs.getString("REGDATE")));
        }
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }finally{
        ju.close(rs);
        ju.close(ps);
    }
    
    return photoBoardList;
    
}

    public int updateLike(String seq) {
    // TODO Auto-generated method stub
        PreparedStatement ps = null;
        int updateCount=0;
        String sql="UPDATE PHOTOBOARDS SET LIKES =LIKES+1 WHERE SEQ="+seq;
        
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

    public int updateDownLoad(String seq) {
        // TODO Auto-generated method stub
        
        PreparedStatement ps = null;
        int updateCount=0;
        String sql="UPDATE PHOTOBOARDS SET DOWNLOADS =DOWNLOADS+1 WHERE SEQ="+seq;
        
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

    public int getLike(String seq) {
        // TODO Auto-generated method stub
        PreparedStatement ps = null;
        ResultSet rs = null;
        int like=0;
        String sql="SELECT LIKES FROM PHOTOBOARDS WHERE SEQ="+seq;
        try {
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                like=rs.getInt("LIKES");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ju.close(rs);
            ju.close(ps);
        }
        
        
        return like;
    }
    
    public int updateView(String seq) {
        // TODO Auto-generated method stub
        
        PreparedStatement ps = null;
        int updateCount=0;
        String sql="UPDATE PHOTOBOARDS SET VIEWS =VIEWS+1 WHERE SEQ="+seq;
        
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

    public int getView(String seq) {
        // TODO Auto-generated method stub
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        int view=0;
        String sql="SELECT VIEWS FROM PHOTOBOARDS WHERE SEQ="+seq;
        try {
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                view=rs.getInt("VIEWS");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ju.close(rs);
            ju.close(ps);
        }
        
        
        return view;
        
    }

    public int getDownLoad(String seq) {
        // TODO Auto-generated method stub
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        int download=0;
        String sql="SELECT DOWNLOADS FROM PHOTOBOARDS WHERE SEQ="+seq;
        try {
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                download=rs.getInt("DOWNLOADS");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ju.close(rs);
            ju.close(ps);
        }
        
        
        return download;
        
    }

}
