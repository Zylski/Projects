package com.shinra.utopia.entity;

/*
 * Programmer: Damian Zylski
 * Project:    UtopiaProject
 * Date:       04/20/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The Flight_booking class for the utopia project. corresponds to flight_booking in the database
 */
public class FlightBooking
{
    //attributes
    private Integer flightID;
    private Integer bookingID;
    
    //Constructors
    public FlightBooking()
    {
        this.flightID = 0;
        this.bookingID = 0;
    }

    public FlightBooking(Integer flightID, Integer bookingID)
    {
        this.flightID = flightID;
        this.bookingID = bookingID;
    }
    
    //getters
     public Integer getFlightID()
    {
        return flightID;
    }

    public Integer getBookingID()
    {
        return bookingID;
    }

    //setters
    public void setFlightID(Integer flightID)
    {
        this.flightID = flightID;
    }

    public void setBookingID(Integer bookingID)
    {
        this.bookingID = bookingID;
    }

    //Equal
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final FlightBooking other = (FlightBooking) obj;
        if (!this.bookingID.equals(other.bookingID))
        {
            return false;
        }
        return true;
    }

    
    //Hash code
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 69 * hash + this.bookingID;
        return hash;
    }
    //compareTo
    public int compareTo(FlightBooking f)
    {
        if(this.bookingID > f.bookingID)
        {
            return 1;
        }
        else if(this.bookingID < f.bookingID)
        {
            return -1;
        }
        else if(this.flightID == f.bookingID)
        {
            return 0;
        }
        else
        {
            return 0;
        }   
    } 
    //ToString
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Flight{flightID=").append(flightID);
        sb.append(", routeID=").append(bookingID);
        sb.append('}');
        return sb.toString();
    }
    

    

    

   
    
    
    
}