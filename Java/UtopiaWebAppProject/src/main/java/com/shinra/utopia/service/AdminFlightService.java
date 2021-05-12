package com.shinra.utopia.service;
import com.shinra.utopia.dao.FlightBookingsDAO;
import com.shinra.utopia.dao.FlightDAO;
import com.shinra.utopia.dao.RouteDAO;
import com.shinra.utopia.entity.Flight;
import com.shinra.utopia.entity.FlightBooking;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;

/*
 * Programmer: Damian Zylski
 * Project:    UtopiaProject
 * Date:       04/12/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The AdminFlightService class handles all actions and logic associated with admin users for flight records, like adding
 * or deleting records. This one will primarily service flight records
 */
public class AdminFlightService
{
    //new util, and also connection
    Utility util = new Utility();
    
    //add flight
    public void addFlight(Flight flight) throws SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        //the connection
        Connection conn = null;
        
        try
        {
            //get connection
            conn = util.getConnection();
            
            //create flight object
            FlightDAO fdao = new FlightDAO(conn);
            
            //PK
            Integer pk = null;
            
            //Add new flight
            pk = fdao.addFlight(flight);
            
            System.out.println("PK: " + pk);
            
            //commit changes if no exceptions
            conn.commit();
            //Return success message
        }
        catch(SQLException | ClassNotFoundException | NullPointerException e)
        {   
            e.printStackTrace();            
            //rollback changes
            conn.rollback();
            //return error message
        }
        finally
        {
            if(conn != null)
            {
                //close connection
                conn.close();
            }
        }
    }
    //update flight
    public void updateFlight(Flight flight, Integer flightNum) throws SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        //the connection
        Connection conn = null;
        
        try
        {
            //get connection
            conn = util.getConnection();
            
            //create flight DAO object
            FlightDAO fdao = new FlightDAO(conn);
            
            //PK
            Integer pk = null;
            
            //Update flight
            pk = fdao.updateFlight(flight, flightNum);
            
            System.out.println("PK: " + pk);
            
            //commit changes if no exceptions
            conn.commit();
        }
        catch(SQLException | ClassNotFoundException | NullPointerException e)
        {   
            e.printStackTrace();            
            //rollback changes
            conn.rollback();
            //return error message
        }
        finally
        {
            if(conn != null)
            {
                //close connection
                conn.close();
            }
        }
    }
    
    //delete flight
    public void deleteFlight(Flight flight) throws SQLException, ServletException
    {
        //the connection
        Connection conn = null;
        
        try
        {
            //get connection
            conn = util.getConnection();
            
            //create flight DAO objects
            FlightDAO fdao = new FlightDAO(conn);
            FlightBookingsDAO fbdao = new FlightBookingsDAO(conn);
            
            //PK
            Integer pk = null;
            
            //delete flight bookings
            FlightBooking fb = new FlightBooking(flight.getFlightID(),0);
            pk = fbdao.deleteFlightBooking(fb);
            //delete flight
            pk = fdao.deleteFlight(flight);
            
            System.out.println("PK: " + pk);
            
            //commit changes if no exceptions
            conn.commit();
        }
        catch(SQLException | ClassNotFoundException | NullPointerException e)
        {   
            e.printStackTrace();            
            //rollback changes
            conn.rollback();
            //return error message
        }
        finally
        {
            if(conn != null)
            {
                //close connection
                conn.close();
            }
        }
    }
    
    //read flights
    public List <Flight> readFlights() throws SQLException
    {
        //the connection
        Connection conn = null;
        //Flight list
        List <Flight> flightList = null;
        
        try
        {
            //get connection
            conn = util.getConnection();
            
            //create flight object
            FlightDAO fdao = new FlightDAO(conn);
            //get list of flights
            flightList = fdao.readData();
        }
        catch(SQLException | ClassNotFoundException | NullPointerException e)
        {   
            e.printStackTrace();            
            //rollback changes
        }
        finally
        {
            if(conn != null)
            {
                //close connection
                conn.close();
            }
        }
        return flightList;
    }
    
    //read routes
    public Map getRoutesMap() throws SQLException
    {
        //the connection
        Connection conn = null;
        
        //Create new map
        Map <Integer, String[]> routeMap = null;
        
        try
        {
            //get connection
            conn = util.getConnection();
            
            //create route object
            RouteDAO rdao = new RouteDAO(conn);
            //get map of routes
            routeMap = rdao.getMap();
        }
        catch(SQLException | ClassNotFoundException | NullPointerException e)
        {   
            e.printStackTrace();            
            //rollback changes
        }
        finally
        {
            if(conn != null)
            {
                //close connection
                conn.close();
            }
        }
        return routeMap;
    }

}
