package com.shinra.utopia.entity;



/*
 * Programmer: Damian Zylski
 * Project:    UtopiaProject
 * Date:       03/20/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The Flight class for the utopia project. corresponds to flight in the database
 */
public class Flight
{
    //attributes
    private Integer flightID;
    private Integer routeID;
    private Integer planeID;
    private String depDate;
    private Double seatPrice;
    private Integer resFirstSeats;
    private Integer resBusSeats;
    private Integer resEcoSeats;
    
    //Constructors
    public Flight()
    {
        this.flightID = 0;
        this.routeID = 0;
        this.planeID = 0;
        this.depDate = "0-0-0000";
        this.seatPrice = 0.0;
        this.resFirstSeats = 0;
        this.resBusSeats = 0;
        this.resEcoSeats = 0;
    }

    public Flight(Integer flightID, Integer routeID, Integer planeID, String depDate, Double seatPrice, Integer resFirstSeats, Integer resBusSeats, Integer resEcoSeats)
    {
        this.flightID = flightID;
        this.routeID = routeID;
        this.planeID = planeID;
        this.depDate = depDate;
        this.seatPrice = seatPrice;
        this.resFirstSeats = resFirstSeats;
        this.resBusSeats = resBusSeats;
        this.resEcoSeats = resEcoSeats;
    }
    
    //getters
     public Integer getFlightID()
    {
        return flightID;
    }

    public Integer getRouteID()
    {
        return routeID;
    }

    public Integer getPlaneID()
    {
        return planeID;
    }

    public String getDepDate()
    {
        return depDate;
    }

    public Double getSeatPrice()
    {
        return seatPrice;
    }

    public Integer getResFirstSeats()
    {
        return resFirstSeats;
    }

    public Integer getResBusSeats()
    {
        return resBusSeats;
    }

    public Integer getResEcoSeats()
    {
        return resEcoSeats;
    }
    
    //setters
    public void setFlightID(Integer flightID)
    {
        this.flightID = flightID;
    }

    public void setRouteID(Integer routeID)
    {
        this.routeID = routeID;
    }

    public void setPlaneID(Integer planeID)
    {
        this.planeID = planeID;
    }

    public void setDepDate(String depDate)
    {
        this.depDate = depDate;
    }

    public void setSeatPrice(Double seatPrice)
    {
        this.seatPrice = seatPrice;
    }

    public void setResFirstSeats(Integer resFirstSeats)
    {
        this.resFirstSeats = resFirstSeats;
    }

    public void setResBusSeats(Integer resBusSeats)
    {
        this.resBusSeats = resBusSeats;
    }

    public void setResEcoSeats(Integer resEcoSeats)
    {
        this.resEcoSeats = resEcoSeats;
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
        final Flight other = (Flight) obj;
        if (!this.flightID.equals(other.flightID))
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
        hash = 31 * hash + this.flightID;
        return hash;
    }
    //compareTo
    public int compareTo(Flight f)
    {
        if(this.flightID > f.flightID)
        {
            return 1;
        }
        else if(this.flightID < f.flightID)
        {
            return -1;
        }
        else if(this.flightID == f.flightID)
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
        sb.append(", routeID=").append(routeID);
        sb.append(", planeID=").append(planeID);
        sb.append(", depDate=").append(depDate);
        sb.append(", seatPrice=").append(seatPrice);
        sb.append(", resFirstSeats=").append(resFirstSeats);
        sb.append(", resBusSeats=").append(resBusSeats);
        sb.append(", resEcoSeats=").append(resEcoSeats);
        sb.append('}');
        return sb.toString();
    }
    

    

    

   
    
    
    
}