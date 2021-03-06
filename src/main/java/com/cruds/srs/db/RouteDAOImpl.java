package com.cruds.srs.db;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.cruds.srs.entity.Route;

import com.cruds.srs.exception.SRSException;

//@Repository
public class RouteDAOImpl implements RouteDAO {
	
@Autowired
SessionFactory sessionfactory;


public void setSessionfactory(SessionFactory sessionfactory) {
	this.sessionfactory = sessionfactory;
}




//add route
	public String addRoute(Route Routebean) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		
		
		try
		{


			session.save(Routebean);
			session.getTransaction().commit();
			session.close();
			return "success";

		}
		
		
		
		catch (SRSException e) 
		{
		
		e.printStackTrace();
		if(e.getMessage().contains("Same Entry"))
		{
			throw new SRSException(Routebean.getRouteid() +" This RouteID already exists!");
		}
		else
		{   
			throw new SRSException(e.getMessage() +"contact the Ship Administration Authorities!\n Have a Good Day!!");
		}
		}
		
		catch (org.hibernate.exception.ConstraintViolationException e) 
		{
			// TODO: handle exception
			System.out.println("Same Entry is not possible");

		}
		
		return null;
	}

	//route list
	
	public ArrayList<Route> viewByAllRoute()
	{
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		
		ArrayList<Route> routelist= (ArrayList<Route>) session.createQuery("from Route").list();
		session.getTransaction().commit();
		session.close();
		return routelist;
	}

	
	// delete route
	public int removeRoute(String routeId) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();

		try
		{
			Route r= (Route) session.load(Route.class, routeId);			
			session.delete(r);
			session.flush();
			session.getTransaction().commit();
			session.close();

			return 1;
		}
		
		catch ( java.lang.IllegalArgumentException e) {

			System.out.println(" Exception"+e.getMessage());
			
			return 0;
		}
		
		catch (org.hibernate.exception.ConstraintViolationException e) 
		{
			
			return 0;
		}

	}
	
	
//modify route
	public boolean modifyRoute(Route routebean) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		try
		{
			session.update(routebean);
			session.getTransaction().commit();
			session.close();
			return true ;
		}
		catch ( java.lang.NumberFormatException e) {

			System.out.println("Exception");
			return false;
		}
		catch (org.hibernate.TransientObjectException e) {
			
			return false;
		}
	}

}
