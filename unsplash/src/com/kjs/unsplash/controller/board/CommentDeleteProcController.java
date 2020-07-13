package com.kjs.unsplash.controller.board;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.DAO.CommentDAO;
import com.kjs.unsplash.controller.Controller;
import com.kjs.unsplash.jdbc.JdbcUtil;

public class CommentDeleteProcController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        String seq = request.getParameter("seq");
        String fSeq = request.getParameter("fSeq");
        String sort=request.getParameter("sort");
        String search=request.getParameter("search");
        
        JdbcUtil ju = new JdbcUtil();
        Connection con = ju.getConnection();
        
        CommentDAO commentDao = new CommentDAO(ju, con);
        int deleteCount=commentDao.deleteComment(seq);
        
        if(deleteCount>0){
            ju.commit(con);
            System.out.println("댓글삭제성공");
        }else{
            ju.rollback(con);
            System.out.println("댓글삭제실패");
        }
        
        ju.close(con);
        
        
        return "redirect:board1_detail.action?sort="+sort+"&search="+search+"&seq="+fSeq;
    }

}
