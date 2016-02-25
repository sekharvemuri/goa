/**
 * 
 */
package com.guru.order.app.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.guru.order.app.resource.OrderResourceImpl;
import com.guru.order.app.resource.WorkOrderResourceImpl;

/**
 * @author RPILLARISETT
 * 
 */
public class GuruOrderApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public GuruOrderApplication() {
		singletons.add(new OrderResourceImpl());
		singletons.add(new WorkOrderResourceImpl());
		//singletons.add(new GroupResourceImpl());
	}

	public Set<Object> getSingletons() {
		return singletons;
	}
}
