package com.kjs.unsplash.controller.member;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.DAO.MemberDAO;
import com.kjs.unsplash.controller.Controller;
import com.kjs.unsplash.jdbc.JdbcUtil;

public class JoinProcController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String nickName = request.getParameter("nickName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        JdbcUtil ju = new JdbcUtil();
        Connection con=ju.getConnection();
        MemberDAO memberDAO = new MemberDAO(ju,con);
        String uri=null;
        int insertCount=memberDAO.joinMember(firstName,lastName,nickName,email,password);
        if(insertCount>0){
            System.out.println("join success");
            ju.commit(con);
            uri="redirect:login.jsp?welcome='welcome "+email+"'&email="+email;
        }else{
            System.out.println("join Fail");
            ju.rollback(con);
            uri="redirect:join.jsp?fail='try again'";
        }
        ju.close(con);
        
        return uri;
    }

}
