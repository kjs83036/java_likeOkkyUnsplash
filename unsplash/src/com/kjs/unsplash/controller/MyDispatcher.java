package com.kjs.unsplash.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyDispatcher extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        service(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        service(request, response);
    }

    Map<String, Controller> conMap = new HashMap<String, Controller>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        String path = config.getInitParameter("txtPath");
        String readMap = null;
        String[] readMaps = null;
        String uri = null;
        Controller con = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            while ((readMap = br.readLine()) != null) {
                readMaps = readMap.split(" ");
                uri = readMaps[0];
                con = (Controller) Class.forName(readMaps[1]).newInstance();
                conMap.put(uri, con);
            }
            br.close();
        } catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String requestURI = request.getRequestURI();
        String toMove = null;
        System.out.println("conMap : "+conMap);
        Controller con=conMap.get(requestURI);
        System.out.println("requestURI : "+requestURI);
        System.out.println("con : "+con);
        toMove=con.execute(request, response);
        String[] toMoves=toMove.split(":");
        if(toMoves[0].equals("redirect")){
            response.sendRedirect(toMoves[1]);
            System.out.println("redirect");
        }else if(toMoves[0].equals("void")){
            System.out.println("void");
        }else{
            RequestDispatcher rd = request.getRequestDispatcher(toMove);
            rd.forward(request, response);
            System.out.println("forward");
        }
    }
}
