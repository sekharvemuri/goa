package com.guru.order.app.application;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("LoadServlet.init()");
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LoadServlet.execute()");
		ResourceBundle rBundle = ResourceBundle.getBundle("metadata");
		System.out.println("rBundle : " + rBundle);
		String groupsStr = rBundle.getString("groups");
		System.out.println(groupsStr);
	}

	public void destroy() {
	}

}
