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

public class CommentUpdateProcController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        String seq = request.getParameter("seq");
        String fSeq = request.getParameter("fSeq");
        String sort = request.getParameter("sort");
        String search = request.getParameter("search");
        String text = request.getParameter("text");

        JdbcUtil ju = new JdbcUtil();
        Connection con = ju.getConnection();

        CommentDAO commentDao = new CommentDAO(ju, con);
        Comment comment = new Comment(seq, fSeq, null, text, 0, null);
        int updateCount = commentDao.updateComment(comment);

        if (updateCount > 0) {
            System.out.println("댓글 수정 성공");
            ju.commit(con);
        } else {
            System.out.println("댓글 수정 실패");
            ju.rollback(con);
        }

        ju.close(con);

        return "redirect:board1_detail.action?sort=" + sort + "&search=" + search + "&seq=" + fSeq;
    }

}
