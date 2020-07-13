package com.kjs.unsplash.controller.board;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.DAO.BoardDAO;
import com.kjs.unsplash.DAO.CommentDAO;
import com.kjs.unsplash.controller.Controller;
import com.kjs.unsplash.jdbc.JdbcUtil;
import com.kjs.unsplash.vo.Board;
import com.kjs.unsplash.vo.Comment;

public class CommentUpdateController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        String seq = request.getParameter("seq");
        String fSeq = request.getParameter("fSeq");
        String sort=request.getParameter("sort");
        String search=request.getParameter("search");
        
        JdbcUtil ju = new JdbcUtil();
        Connection con = ju.getConnection();
        BoardDAO boardDao = new BoardDAO(ju, con);
        CommentDAO commentDao = new CommentDAO(ju, con);
        List<Comment> commentList = commentDao.ListComment(fSeq);
        Board board=boardDao.getBoard(fSeq);

        request.setAttribute("sort", sort);
        request.setAttribute("search", search);
        request.setAttribute("seq", seq);
        request.setAttribute("fSeq", fSeq);
        request.setAttribute("board", board);
        request.setAttribute("commentList", commentList);
        
        ju.close(con);
        
        return "board1_comment_detail.jsp";
    }

}
