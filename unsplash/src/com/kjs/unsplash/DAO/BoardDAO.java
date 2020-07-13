package com.kjs.unsplash.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kjs.unsplash.jdbc.JdbcUtil;
import com.kjs.unsplash.vo.Board;

public class BoardDAO {
    
    JdbcUtil ju;
    Connection con;
    public BoardDAO(JdbcUtil ju, Connection con) {
        super();
        this.ju = ju;
        this.con = con;
    }
    
    public int insertBoard(Board board){
        
        PreparedStatement ps= null;
        int insertCount=0;
        String sql="INSERT INTO BOARDS (SEQ,TAG,WRITER,TITLE,CONTENT) VALUES ((SELECT MAX(TO_NUMBER(SEQ)) FROM BOARDS)+1,?,?,?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, board.getTag());
            ps.setString(2, board.getWriter());
            ps.setString(3, board.getTitle());
            ps.setString(4, board.getContent());
            insertCount=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            ju.close(ps);
        }
        
        return insertCount;
        
    }
    
    public List<Board> listBoard(String sort,String search){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Board>boardList = new ArrayList<Board>();
        String sql=null;
        if(sort==null){
            if(search!=null){
                sql="SELECT * FROM BOARDS WHERE TITLE LIKE '%"+search+"%' OR CONTENT LIKE '%"+search+"%' ORDER BY TO_NUMBER(SEQ) DESC";
            }else{
                sql="SELECT * FROM BOARDS ORDER BY TO_NUMBER(SEQ) DESC";
            }
        }else if(sort.equals("read")){
            if(search!=null){
                sql="SELECT * FROM BOARDS WHERE TITLE LIKE '%"+search+"%' OR CONTENT LIKE '%"+search+"%' ORDER BY READ DESC";
            }else{
            sql="SELECT * FROM BOARDS ORDER BY READ DESC";
            }
        }else if(sort.equals("comment")){
            if(search!=null){
                sql="SELECT B.SEQ,B.TAG,B.WRITER,B.TITLE,B.CONTENT,B.READ,B.REGDATE FROM BOARDS B INNER JOIN (SELECT B.SEQ, COUNT(C.FSEQ) AS COUNT FROM BOARDS B FULL OUTER JOIN COMMENTS C ON B.SEQ=C.FSEQ GROUP BY B.SEQ ORDER BY TO_NUMBER(SEQ)) S ON B.SEQ=S.SEQ WHERE TITLE LIKE '%"+search+"%' OR CONTENT LIKE '%"+search+"%' ORDER BY COUNT DESC";
            }else{
            sql="SELECT B.SEQ,B.TAG,B.WRITER,B.TITLE,B.CONTENT,B.READ,B.REGDATE FROM BOARDS B INNER JOIN (SELECT B.SEQ, COUNT(C.FSEQ) AS COUNT FROM BOARDS B FULL OUTER JOIN COMMENTS C ON B.SEQ=C.FSEQ GROUP BY B.SEQ ORDER BY TO_NUMBER(SEQ)) S ON B.SEQ=S.SEQ ORDER BY COUNT DESC";
            }
        }else{
            if(search!=null){
                sql="SELECT * FROM BOARDS WHERE TITLE LIKE '%"+search+"%' OR CONTENT LIKE '%"+search+"%' ORDER BY TO_NUMBER(SEQ) DESC";
            }else{
            sql="SELECT * FROM BOARDS ORDER BY TO_NUMBER(SEQ) DESC";
            }
        }
        
        try {
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                boardList.add(new Board(rs.getString("SEQ"),rs.getString("TAG"),rs.getString("WRITER"),rs.getString("TITLE"),rs.getString("CONTENT"),rs.getInt("READ"),rs.getString("REGDATE")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            ju.close(rs);
            ju.close(ps);
        }
        
        return boardList;
    }
    
    public Board getBoard(String seq){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Board board= null;
        String sql="SELECT * FROM BOARDS WHERE SEQ="+seq;
        try {
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                board= new Board(rs.getString("SEQ"), rs.getString("TAG"), rs.getString("WRITER"), rs.getString("TITLE"), rs.getString("CONTENT"), rs.getInt("READ"),rs.getString("REGDATE"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ju.close(rs);
            ju.close(ps);
        }
        
        
        return board;
    }
    
    public int updateRead(String seq){
        
        PreparedStatement ps = null;
        int updateCount=0;
        String sql="UPDATE BOARDS SET READ =READ+1 WHERE SEQ="+seq;
        
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
    
    public int updateBoard(Board board){
        
        PreparedStatement ps = null;
        String sql="UPDATE BOARDS SET TAG=?, TITLE=?, CONTENT=? WHERE SEQ=?";
        int updateCount=0;
        
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, board.getTag());
            ps.setString(2, board.getTitle());
            ps.setString(3, board.getContent());
            ps.setString(4, board.getSeq());
            updateCount=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            ju.close(ps);
        }
        
        
        return updateCount;
    }

    public int deleteBoard(String seq) {
        // TODO Auto-generated method stub
        PreparedStatement ps = null;
        String sql="DELETE FROM BOARDS WHERE SEQ="+seq;
        int updateCount=0;
        
        try {
            ps=con.prepareStatement(sql);
            updateCount=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            ju.close(ps);
        }
        
        
        return updateCount;
    }
    

}
