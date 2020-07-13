package com.kjs.unsplash.controller.photoBoard;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.controller.Controller;

public class DownloadProcController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        String fileName = request.getParameter("fileName");
        String root = request.getServletContext().getRealPath("/");
        String savePath=root+"upload/";

        String fName = URLEncoder.encode(fileName, "UTF-8");
        System.out.println("fName : " + fName);
        String realPath=savePath+fName;
        System.out.println("realPath : "+realPath);
        response.setHeader("Content-Disposition", "attachment;filename=" + fName+";");
        FileInputStream fis = new FileInputStream(realPath);
        ServletOutputStream sos = response.getOutputStream();
        
        byte[] buf = new byte[1024];
        int readData = 0;
        while ((readData = fis.read(buf)) != -1) {
            sos.write(buf);
        }
        fis.close();
        sos.close();
        
        
        return "void:";
        
    }

}
