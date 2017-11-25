package com.tgrape.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgrape.db.StatesReader;
import com.tgrape.json.JsonUtil;

public class StatesVerify extends HttpServlet{

	private static final long serialVersionUID = 1L;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //response.setContentType("text/html");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        int verifyDays = Integer.parseInt( request.getParameter("days") );
        PrintWriter out = response.getWriter();
        String st = JsonUtil.object2Json(StatesReader.getVerify(verifyDays));
        out.println(st);
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
