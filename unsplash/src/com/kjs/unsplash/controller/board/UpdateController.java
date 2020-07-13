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

public class UpdateController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        String sort=request.getParameter("sort");
        String search=request.getParameter("search");
        String seq=request.getParameter("seq");
        
        JdbcUtil ju = new JdbcUtil();
        Connection con = ju.getConnection();
        
        BoardDAO boardDao = new BoardDAO(ju, con);
        Board board=boardDao.getBoard(seq);
        
        ju.close(con);
        
        request.setAttribute("sort", sort);
        request.setAttribute("search", search);
        request.setAttribute("board", board);
        
        return "board1_update.jsp";
    }

}
