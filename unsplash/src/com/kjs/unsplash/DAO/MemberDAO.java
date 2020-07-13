package com.kjs.unsplash.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kjs.unsplash.jdbc.JdbcUtil;
import com.kjs.unsplash.vo.Member;

public class MemberDAO {

    JdbcUtil ju;
    Connection con;

    public MemberDAO(JdbcUtil ju, Connection con) {
        super();
        this.ju = ju;
        this.con = con;
    }

    public int joinMember(String firstName, String lastName, String nickName, String email, String password) {

        PreparedStatement ps = null;
        int insertCount = 0;
        String sql = "INSERT INTO MEMBERS (firstName,lastName,nickName,email,password) VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, nickName);
            ps.setString(4, email);
            ps.setString(5, password);
            insertCount = ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            ju.close(ps);
        }
        
        return insertCount;

    }
    
    public Member getMember(String email){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member=null;
        String sql="SELECT * FROM MEMBERS WHERE email=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, email);
            rs=ps.executeQuery();
            while(rs.next()){
                member= new Member(rs.getString("firstName"), rs.getString("lastName"), rs.getString("nickName"), rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return member;
        
    }

}
