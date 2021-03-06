package com.cruds.srs.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruds.srs.db.RouteDAO;
import com.cruds.srs.entity.Route;


	@Service
	public class Routeservice {
	

	@Autowired
	RouteDAO routeDao;


	public String addRoute(Route Routebean)
	{
	return routeDao.addRoute(Routebean);
	}

	public ArrayList<Route> viewByAllRoute()
	{
	return routeDao.viewByAllRoute();
	}

	public int removeRoute(String routeId)
	{
	return routeDao.removeRoute(routeId);
	}

	public boolean modifyRoute(Route routebean)
	{
	return routeDao.modifyRoute(routebean);
	}

}
