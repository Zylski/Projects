package com.shinra.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;

/*
 * Programmer: Damian Zylski
 * Project:    UtopiaProject
 * Date:       04/20/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The Route DAO for the utopia project, corresponds to route in the database
 *The add, update, and delete operations are not finished at the moment
 */
public class RouteDAO extends BaseDAO
{
    //connection constructor
    public RouteDAO(Connection conn)
    {
        super(conn);
    }
    
    //Add operation
    public Integer addRoute(Object route) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        return crudReturnPK("INSERT INTO flight(id, origin_id, destination_id, route_hours) VALUES(?,?,?,?);", 
                new Object[] {route.toString()});
    }
    //update operation
    public Integer updateRoute(Object route, Integer oldID) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        return crudReturnPK("UPDATE route SET id=?,origin_id=?,destination_id=?,route_hours=? WHERE id=?;", 
                new Object[] {route.toString(), oldID});
    }
    //delete operation
    public Integer deleteRoute(Object route) throws ClassNotFoundException, SQLException, ServletException
    {
        return crudReturnPK("DELETE FROM route WHERE id = ?;", new Object[] {route.toString()});
    }
    
    //select all for certain id operation
    public Object getFlight(Integer ID) throws ClassNotFoundException, SQLException
    {
        //Create user
        Object route = null;
        //Create statement for handling queries
        PreparedStatement stmt = RouteDAO.conn.prepareStatement("SELECT * FROM route WHERE id = ?");
        //set values
        stmt.setInt(1,ID);
        //execute query
        ResultSet rs = stmt.executeQuery();
        //Return true if result set exists
        if(rs.next())
        {
            route = new Object(); //Need to create route class to finish this
        }
        return route;
    }
    
    
    //select all operation
    @Override
    public List<Object> readData() throws ClassNotFoundException, SQLException
    {
        //Create new list
        List<Object> routeList = new ArrayList <Object>();
        
        //Variables
        Integer id = 0;
        Integer originID = 0;
        Integer destinationID = 0;
        Integer routeHours = 0;
        Object route = null;
        
        //Create statement for handling queries
        PreparedStatement stmt = RouteDAO.conn.prepareStatement("SELECT * FROM route");
        //execute query
        ResultSet rs = stmt.executeQuery();
        //get database members
        while(rs.next())
        {
            //add route to list
            routeList.add(rs.getInt("id")); //need to create route class to add route objects
            
        }
        return routeList;
    }
    
    //return map operation
    public Map<Integer, String[]> getMap() throws ClassNotFoundException, SQLException
    {
        //Create new map
        Map <Integer, String[]> routeMap = new <Integer, String[]> HashMap();
        
        //Variables
        String id = null;
        String originID = null;
        String destinationID = null;
        String routeHours = null;
        Object route = null;
        
        //Create statement for handling queries
        PreparedStatement stmt = RouteDAO.conn.prepareStatement("SELECT * FROM route");
        //execute query
        ResultSet rs = stmt.executeQuery();
        //get database members
        while(rs.next())
        {
            //add route to map
            id = rs.getString("id");
            originID = rs.getString("origin_id");
            destinationID = rs.getString("destination_id");
            routeHours = rs.getString("route_hours");
            routeMap.put(Integer.parseInt(id), new String[] {id, originID, destinationID, routeHours});
            
        }
        return routeMap;
    }
}

