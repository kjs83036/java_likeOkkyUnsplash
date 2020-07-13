package com.kjs.unsplash.controller.photoBoard;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.DAO.PhotoBoardDAO;
import com.kjs.unsplash.controller.Controller;
import com.kjs.unsplash.jdbc.JdbcUtil;
import com.kjs.unsplash.vo.PhotoBoard;

public class PhotoBoardController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        
        JdbcUtil ju = new JdbcUtil();
        Connection con = ju.getConnection();
        PhotoBoardDAO pBoardDao = new PhotoBoardDAO(ju, con);
        List<PhotoBoard> photoBoardList = pBoardDao.listPhotoBoard();
        
        String realPath = request.getServletContext().getRealPath("/upload/");
        System.out.println("realPath : " + realPath);

        request.setAttribute("photoBoardList", photoBoardList);
        request.setAttribute("realPath", realPath);
        
        ju.close(con);
        
        return "photoBoard.jsp";
    }

}
