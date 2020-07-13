package com.kjs.unsplash.controller.board;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.DAO.CommentDAO;
import com.kjs.unsplash.controller.Controller;
import com.kjs.unsplash.jdbc.JdbcUtil;
import com.kjs.unsplash.vo.Comment;

public class CommentProcController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        request.setCharacterEncoding("UTF-8");
        String writer = (String)request.getSession().getAttribute("nickName");
        String seq = request.getParameter("seq");
        String sort = request.getParameter("sort");
        String search = request.getParameter("search");
        String text = request.getParameter("text");
        
        JdbcUtil ju = new JdbcUtil();
        Connection con=ju.getConnection();
        
        CommentDAO commentDao = new CommentDAO(ju, con);
        Comment comment = new Comment(null, seq, writer, text, 0, null);
        int insertCount=commentDao.insertComment(comment);
        
        if(insertCount>0){
            ju.commit(con);
            System.out.println("댓글성공");
        }else{
            ju.rollback(con);
            System.out.println("댓글실패");
        }
        ju.close(con);
        
        
        return "redirect:board1_detail.action?sort="+sort+"&search="+search+"&seq="+seq;
    }

}
