package com.kjs.unsplash.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kjs.unsplash.controller.Controller;

public class LoginController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        String welcome = request.getParameter("welcome");
        if (welcome != null) {
            if (welcome.equals("null")) {
                welcome = null;
            }
        } else {
            welcome = "";
        }
        
        
        return "redirect:login.jsp?welcome="+welcome;
    }
    

}
