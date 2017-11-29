package com.tgrape.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgrape.db.StatesReader;

public class Hit extends HttpServlet{

	private static final long serialVersionUID = 1L;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //response.setContentType("text/html");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String stkcode = request.getParameter("stkcode");
		StatesReader.setHit(stkcode , true);
        out.println("ok");
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
