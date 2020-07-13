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

public class WrittingProcController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        String writer=(String)request.getSession().getAttribute("nickName");
        String title=request.getParameter("title");
        String tag=request.getParameter("tag");
        String content=request.getParameter("content");
        Board board = new Board(null, tag, writer, title, content, 0, null);
        
        JdbcUtil ju = new JdbcUtil();
        Connection con = ju.getConnection();
        
        BoardDAO boardDao = new BoardDAO(ju, con);
        int insertCount=boardDao.insertBoard(board);
        if(insertCount>0){
            ju.commit(con);
            System.out.println("글쓰기성공");
        }else{
            ju.rollback(con);
            System.out.println("글쓰기실패");
        }
        ju.close(con);
        
        
        return "redirect:board1.action";
    }

}
