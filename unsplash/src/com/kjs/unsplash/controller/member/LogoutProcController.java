package com.kjs.unsplash.controller.member;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.controller.Controller;

public class LogoutProcController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        request.getSession().removeAttribute("nickName");
        return "redirect:home.jsp";
    }

}
