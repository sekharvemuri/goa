package com.guru.order.app.application;

import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class DataLoadListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("in dataloadlistener");
		ServletContext context = sce.getServletContext();
		ResourceBundle rBundle = ResourceBundle.getBundle("metadata");
		System.out.println("rBundle : " + rBundle);
		String groupsStr = rBundle.getString("groups");
		System.out.println(groupsStr);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
