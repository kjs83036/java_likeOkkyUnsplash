package com.kjs.unsplash.controller.photoBoard;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.DAO.PhotoBoardDAO;
import com.kjs.unsplash.controller.Controller;
import com.kjs.unsplash.jdbc.JdbcUtil;

public class LikeProcController  implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        String seq = request.getParameter("seq");
        
        JdbcUtil ju = new JdbcUtil();
        Connection con = ju.getConnection();
        
        PhotoBoardDAO pbDao = new PhotoBoardDAO(ju, con);
        int updateCount=pbDao.updateLike(seq);
        int like=pbDao.getLike(seq);
        
        if(updateCount>0&&like>0){
            ju.commit(con);
            System.out.println("추천성공");
        }else{
            ju.rollback(con);
            System.out.println("추천실패");
        }
        
        ju.close(con);
        
        request.setAttribute("like", like);
        System.err.println("like : "+like);
        
        
        return "like.jsp";
    }

}
