package com.kjs.unsplash.controller.board;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.DAO.BoardDAO;
import com.kjs.unsplash.controller.Controller;
import com.kjs.unsplash.jdbc.JdbcUtil;
import com.kjs.unsplash.vo.Board;

public class UpdateProcController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        request.setCharacterEncoding("UTF-8");
        String sort=request.getParameter("sort");
        String search=request.getParameter("search");
        String seq=request.getParameter("seq");
        String title=request.getParameter("title");
        String tag=request.getParameter("tag");
        String content=request.getParameter("content");
        
        JdbcUtil ju = new JdbcUtil();
        Connection con = ju.getConnection();
        
        BoardDAO boardDao = new BoardDAO(ju, con);
        Board board=boardDao.getBoard(seq);
        Board newBoard = new Board(board.getSeq(), tag, board.getWriter(), title, content, board.getRead());
        int updateCount=boardDao.updateBoard(newBoard);
        if(updateCount>0){
            ju.commit(con);
            System.out.println("수정성공");
        }else{
            ju.rollback(con);
            System.out.println("수정실패");
        }
        ju.close(con);
        
        request.setAttribute("sort", sort);
        request.setAttribute("search", search);
        request.setAttribute("seq", newBoard.getSeq());
        
        return "board1_detail.action";
    }

}
