package com.ub.customizability.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author mohitakhakharia
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      String nextJSP = "/jsp/LoginPage.jsp";
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
      try {
		dispatcher.forward(req,resp);
	} catch (ServletException e) {
		e.printStackTrace();
	}
    
  }
  
  }