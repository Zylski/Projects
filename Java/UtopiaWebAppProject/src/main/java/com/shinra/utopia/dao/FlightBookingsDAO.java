package com.shinra.utopia.dao;

import com.shinra.utopia.entity.FlightBooking;
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
 * Purpose:    The Flight Bookings DAO for the utopia project, corresponds to flight_bookings in the database
 *The add, update, and delete operations are not finished at the moment
 */
public class FlightBookingsDAO extends BaseDAO
{
    //connection constructor
    public FlightBookingsDAO(Connection conn)
    {
        super(conn);
    }
    
    //Add operation
    public Integer addFlightBooking(FlightBooking fb) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        return crudReturnPK("INSERT INTO flight_bookings(flight_id, booking_id) VALUES(?,?);", 
                new Object[] {fb.getFlightID(), fb.getBookingID()});
    }
    //update operation
    public Integer updateFlightBooking(FlightBooking fb, Integer oldID) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        return crudReturnPK("UPDATE flight_bookings SET flight_id=?,booking_id=? WHERE flight_id=?;", 
                new Object[] {fb.getFlightID(), fb.getBookingID(), oldID});
    }
    //delete operation based on flight_id, booking_id would make more sense though.
    public Integer deleteFlightBooking(FlightBooking fb) throws ClassNotFoundException, SQLException, ServletException
    {
        return crudReturnPK("DELETE FROM flight_bookings WHERE flight_id = ?;", new Object[] {fb.getFlightID()});
    }

    //select all operation
    @Override
    public List<String> readData() throws ClassNotFoundException, SQLException
    {
        //Create new list
        List<String> fbList = new ArrayList <String>();
        
        //Variables
        Integer flightID = 0;
        Integer bookingID = 0;
        Object fb = null;
        
        //Create statement for handling queries
        PreparedStatement stmt = RouteDAO.conn.prepareStatement("SELECT * FROM flight_bookings");
        //execute query
        ResultSet rs = stmt.executeQuery();
        //get database members
        while(rs.next())
        {
            //add fb to list
            fbList.add(rs.getString("flight_id") + " " + rs.getString("booking_id")); 
            
        }
        return fbList;
    }
    

}

