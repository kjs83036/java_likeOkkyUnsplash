package com.kjs.unsplash.controller.photoBoard;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.DAO.PhotoBoardDAO;
import com.kjs.unsplash.controller.Controller;
import com.kjs.unsplash.jdbc.JdbcUtil;
import com.kjs.unsplash.vo.PhotoBoard;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WrittingProcController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        
        String root = request.getSession().getServletContext().getRealPath("/");
        
        String savePath=root+"upload";
        
        MultipartRequest mpr = new MultipartRequest(request, savePath,1024*1024*100,"UTF-8",new DefaultFileRenamePolicy());
        
        
        
        String writer=(String)request.getSession().getAttribute("nickName");
        String tag=mpr.getParameter("tag");
        String fileSrc=mpr.getFilesystemName("file");
        
        PhotoBoard photoBoard = new PhotoBoard(null, "\\unsplash\\upload\\"+fileSrc, tag, writer, 0, 0, 0);
        
        JdbcUtil ju = new JdbcUtil();
        Connection con = ju.getConnection();
        
        PhotoBoardDAO photoBoardDao = new PhotoBoardDAO(ju, con);
        int insertCount=photoBoardDao.insertPhotoBoard(photoBoard);
        if(fileSrc==null){
            insertCount=0;
        }
        if(insertCount>0){
            ju.commit(con);
            System.out.println("글쓰기성공");
        }else{
            ju.rollback(con);
            System.out.println("글쓰기실패");
        }
        ju.close(con);
        
        
        return "redirect:photoBoard.action";
    }

}
