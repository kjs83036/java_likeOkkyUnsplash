package com.kjs.unsplash.controller.member;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.DAO.MemberDAO;
import com.kjs.unsplash.controller.Controller;
import com.kjs.unsplash.jdbc.JdbcUtil;
import com.kjs.unsplash.vo.Member;

public class LoginProcController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String uri=null;
        JdbcUtil ju = new JdbcUtil();
        Connection con = ju.getConnection();
        MemberDAO memberDAO = new MemberDAO(ju, con);
        Member member=memberDAO.getMember(email);
        if(member!=null){
            if(member.getPassword().equals(password)){
                System.out.println("로그인 성공");
                request.getSession().setAttribute("nickName", member.getNickName());
                uri="redirect:home.jsp";
            }else{
                System.out.println("로그인 실패");
                uri="redirect:login.jsp?welcome=login fail";
            }
        }else{
            System.out.println("아이디가없습니다");
            uri="redirect:login.jsp?welcome=login fail";
        }
        return uri;
    }

}
