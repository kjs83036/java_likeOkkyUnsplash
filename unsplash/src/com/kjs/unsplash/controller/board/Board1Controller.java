package com.kjs.unsplash.controller.board;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.DAO.BoardDAO;
import com.kjs.unsplash.DAO.CommentDAO;
import com.kjs.unsplash.controller.Controller;
import com.kjs.unsplash.jdbc.JdbcUtil;
import com.kjs.unsplash.vo.Board;

public class Board1Controller implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        String sort=request.getParameter("sort");
        String search=request.getParameter("search");
        
        JdbcUtil ju = new JdbcUtil();
        Connection con = ju.getConnection();
        BoardDAO boardDao = new BoardDAO(ju, con);
        CommentDAO commentDao = new CommentDAO(ju, con);
        List<Board> boardList = boardDao.listBoard(sort,search);
        Map<String,String>countMap=commentDao.countComment();
        
        request.setAttribute("boardList", boardList);
        request.setAttribute("sort", sort);
        request.setAttribute("search", search);
        request.setAttribute("countMap", countMap);
        System.out.println(countMap);
        String cM=countMap.toString();
        String cM2=cM.replaceAll("=", ":");
        System.out.println(cM2);
        request.setAttribute("cM2", cM2);
        
        ju.close(con);
        return "board1.jsp";
    }

}
