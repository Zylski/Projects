package com.shinra.utopia.dao;

import com.shinra.utopia.entity.Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;

/*
 * Programmer: Damian Zylski
 * Project:    UtopiaProject
 * Date:       04/12/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The Flight DAO for the utopia project, corresponds to flights in the database
 */
public class FlightDAO extends BaseDAO
{
    //connection constructor
    public FlightDAO(Connection conn)
    {
        super(conn);
    }
    
    //Add operation
    public Integer addFlight(Flight flight) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        return crudReturnPK("INSERT INTO flight(id, route_id, airplane_id, departure_time, seat_price, reserved_first_seats, reserved_bus_seats, reserved_eco_seats) VALUES(?,?,?,?,?,?,?,?);", 
                new Object[] {flight.getFlightID(), 
            flight.getRouteID(), flight.getPlaneID(), flight.getDepDate(), flight.getSeatPrice(), flight.getResFirstSeats(), flight.getResBusSeats(), flight.getResEcoSeats()});
    }
    //update operation
    public Integer updateFlight(Flight flight, Integer oldID) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        return crudReturnPK("UPDATE flight SET id=?,route_id=?,airplane_id=?,departure_time=?,seat_price=?,reserved_first_seats=?,reserved_bus_seats=?,reserved_eco_seats=? WHERE id=?;", 
                new Object[] {flight.getFlightID(), 
            flight.getRouteID(), flight.getPlaneID(), flight.getDepDate(), flight.getSeatPrice(), flight.getResFirstSeats(), flight.getResBusSeats(), flight.getResEcoSeats(), oldID});
    }
    //delete operation
    public Integer deleteFlight(Flight flight) throws ClassNotFoundException, SQLException, ServletException
    {
        return crudReturnPK("DELETE FROM flight WHERE id = ?;", new Object[] {flight.getFlightID()});
    }
    
    //select all for certain id operation
    public Flight getFlight(Integer ID) throws ClassNotFoundException, SQLException
    {
        //Create user
        Flight flight = null;
        //Create statement for handling queries
        PreparedStatement stmt = FlightDAO.conn.prepareStatement("SELECT * FROM flight WHERE id = ?");
        //set values
        stmt.setInt(1,ID);
        //execute query
        ResultSet rs = stmt.executeQuery();
        //Return true if result set exists
        if(rs.next())
        {
            flight = new Flight(rs.getInt("id"),rs.getInt("route_id"),rs.getInt("airplane_id"), rs.getString("departure_time"), rs.getDouble("seat_price"),
            rs.getInt("reserved_first_seats"), rs.getInt("reserved_bus_seats"), rs.getInt("reserved_eco_seats"));
        }
        return flight;
    }
    
    
    //select all operation
    @Override
    public List<Flight> readData() throws ClassNotFoundException, SQLException
    {
        //Create new list
        List<Flight> flightList = new ArrayList <Flight>();
        
        //Variables
        Integer id = 0;
        Integer routeID = 0;
        Integer planeID = 0;
        String depTime = null;
        Double price = 0.0;
        Integer reservedFirst = 0;
        Integer reservedBus = 0;
        Integer reservedEco = 0;
        Flight flight = null;
        
        //Create statement for handling queries
        PreparedStatement stmt = FlightDAO.conn.prepareStatement("SELECT * FROM flight");
        //execute query
        ResultSet rs = stmt.executeQuery();
        //get database members
        while(rs.next())
        {
            //add flight to list
            flightList.add(new Flight(rs.getInt("id"),rs.getInt("route_id"),rs.getInt("airplane_id"), rs.getString("departure_time"), rs.getDouble("seat_price"),
            rs.getInt("reserved_first_seats"), rs.getInt("reserved_bus_seats"), rs.getInt("reserved_eco_seats")));
            
        }
        return flightList;
    }
}
