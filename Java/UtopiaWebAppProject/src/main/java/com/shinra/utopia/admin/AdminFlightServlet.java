package com.shinra.utopia.admin;

import com.shinra.utopia.entity.Flight;
import com.shinra.utopia.service.AdminFlightService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * Programmer: Damian Zylski
 * Project:    AdminFlightServlet
 * Date:       04/16/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To handle login requests for administrator functions. This will handle requests
 * for the admin flights options
*/
@WebServlet("/adminflightservlet")
public class AdminFlightServlet extends HttpServlet
{
    private AdminFlightService admin;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {    
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            //Create new admin service and needed variables
            admin = new AdminFlightService();
            List <Flight> flightList = null;
            Map  <Integer, String[]> routeMap = null;
            Flight flight = null;
            Integer choice = 0;
            boolean numeric = false;
            Integer numFlights = null;
            String target = null;
            String flightID = null;
            String routeID = null;
            String planeID = null;
            String firstClass = null;
            String businessClass = null;
            String ecoClass = null;
            String price = null;
            String departureTime = null;
            
            //Determine type of operation
            String action = request.getParameter("action");                   
            
            /* Output Servlet info*/
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>UTOPIA ADMIN FLIGHTS</title>");            
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"stylesheet\" href=\"utopia.css\" >");
            out.println("<meta name=\"description\" content=\"A flight servlet web page\">");
            out.println("<meta name=\"author\" content=\"Damian Zylski\">");
            out.println("<meta name = \"revised\" content = \"Shinra, 4/01/2021\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=wrapper >");
            out.println("<header>");
            out.println("<h1>Administrator Control Panel</h1>");
            out.println("</header>");
            out.println("<nav>");
            out.println("<a href=\"index.html\" ><button>Home</button></a>");
            out.println("</nav>");
            out.println("<main>");
            out.println("<section>");
            out.println("<br>");
            out.println("<br>");
            
            //perform operation based on action type
            switch(action)
            {
                //ADD FLIGHTS*****************************************************
                case "addFlights":
                    //get int parameters
                    flightID = request.getParameter("ID");
                    routeID = request.getParameter("Route");
                    planeID = request.getParameter("Airplane");
                    firstClass = request.getParameter("First");
                    businessClass = request.getParameter("Business");
                    ecoClass = request.getParameter("Economy");
                    price = request.getParameter("Price");
                    //check if numeric vars are numeric
                    if(isNumeric(flightID) && isNumeric(routeID) && isNumeric(planeID) && isNumeric(firstClass)
                            && isNumeric(businessClass) && isNumeric(ecoClass) && isFloat(price))
                    {
                        //set isnumeric and grab other vars
                        numeric = true;
                        departureTime = request.getParameter("Departure");

                        //create new Flight
                        flight = new Flight(Integer.parseInt(flightID), Integer.parseInt(routeID), Integer.parseInt(planeID), departureTime, Double.parseDouble(price), 
                                Integer.parseInt(firstClass), Integer.parseInt(businessClass), Integer.parseInt(ecoClass));
                        //proceed with adding Flight
                        admin.addFlight(flight);
                        //Display success message
                        out.println("<h3>Flight added successfully.</h3>");
                        out.println("<br>");
                        //Link back to login page
                        out.println("<a href=\"adminflights.html\" ><button tabindex=\"10\" >Return to admin flight screen</button></a>"); 
                        out.println("<br>");
                    }
                    break;
                    //UPDATE FLIGHTS*****************************************************
                    case "updateFlights":
                        //get list of Flights
                        flightList = admin.readFlights();
                        //get routes
                        routeMap = admin.getRoutesMap();
                        numeric = true;
                        //Display Flights
                        out.println("<h3>Flights in System:</h3><br><br>");
                        for(int i = 0; i < flightList.size(); i++)
                        {
                            out.println("<p>" + (i+1) + ".) Flight ID: " + flightList.get(i).getFlightID() + " Departure Date: " 
                                    + flightList.get(i).getDepDate() + "<br>Route: " + 
                                    routeMap.get(flightList.get(i).getRouteID())[1] + " to " + routeMap.get(flightList.get(i).getRouteID())[2] + "</p><br>");
                        }
                        //Ask for which flight to update
                        out.println("<h3>Please choose a flight number to update:</h3>");
                        out.println("<form action=\"AdminFlightServlet\" method=\"post\" >");
                        out.println("<input type=\"hidden\" id=\"flighttId\" name=\"action\" value=\"flightUpdate\">");
                        out.println("<input type=\"text\" id=\"flightNum\" required=\"required\" name=\"FlightNum\">");                       
                        out.println("<br>");
                        out.println("<h3>Enter the details for the flight to update:</h3>");
                        out.println("<label for=\"Name\" >Flight ID: </label><br>");
                        out.println("<input type=\"text\" name=\"ID\" size=\"25\" required=\"required\" tabindex=\"1\"><br>");
                        out.println("<label for=\"Name\" >Route ID: </label><br>");
                        out.println("<input type=\"text\" name=\"Route\" size=\"25\" required=\"required\" tabindex=\"2\"><br>");
                        out.println("<label for=\"Name\" >Airplane ID: </label><br>");
                        out.println("<input type=\"text\" name=\"Airplane\" size=\"25\" required=\"required\" tabindex=\"3\"><br>");
                        out.println("<label for=\"Name\" >Departure Time: </label><br>");
                        out.println("<input type=\"text\" name=\"Departure\" size=\"25\" required=\"required\" tabindex=\"4\"><br>");
                        out.println("<label for=\"Name\" >Seat Price: </label><br>");
                        out.println("<input type=\"text\" name=\"Price\" size=\"25\" required=\"required\" tabindex=\"5\"><br>");
                        out.println("<label for=\"Name\" >Reserved First Class Seats: </label><br>");
                        out.println("<input type=\"text\" name=\"First\" size=\"25\" required=\"required\" tabindex=\"6\"><br>");
                        out.println("<label for=\"Name\" >Reserved Business Class Seats: </label><br>");
                        out.println("<input type=\"text\" name=\"Business\" size=\"25\" required=\"required\" tabindex=\"7\"><br>");
                        out.println("<label for=\"Name\" >Reserved Economy Class Seats: </label><br>");
                        out.println("<input type=\"text\" name=\"Economy\" size=\"25\" required=\"required\" tabindex=\"8\"><br>");
                        out.println("<br>");
                        out.println("<input type=\"submit\" value=\"Submit\" name=\"chooseFlight\" tabindex=\"2\" ><br>");
                        out.println("</form>");
                        //Link back to login page
                        out.println("<br><a href=\"adminflights.html\" ><button tabindex=\"10\" >Return to admin flight screen</button></a><br>"); 
                        break;
                        //Flight update
                    case "flightUpdate":
                        //get list of Flights
                        flightList = admin.readFlights();
                        numFlights = flightList.size();
                        target = request.getParameter("FlightNum");
                        numeric = isNumeric(target);
                        Integer oldID = 0;

                        if(numeric)
                        {
                            //parse into int
                            int flightNum = Integer.parseInt(target)-1;
                            //Check if input in bounds
                            if((flightNum < 0 || flightNum >= numFlights))
                            {
                                numeric = false;
                                break;
                            }
                            //set old flight ID
                            oldID = flightList.get(flightNum).getFlightID();
                            System.out.println("Servlet Old ID: " + flightNum);                            
                            //get int parameters
                            //get int parameters
                            flightID = request.getParameter("ID");
                            routeID = request.getParameter("Route");
                            planeID = request.getParameter("Plane");
                            firstClass = request.getParameter("First");
                            businessClass = request.getParameter("Business");
                            ecoClass = request.getParameter("Economy");
                            price = request.getParameter("Price");
                            //check if numeric vars are numeric
                            if(isNumeric(flightID) && isNumeric(routeID) && isNumeric(planeID) && isNumeric(firstClass)
                                    && isNumeric(businessClass) && isNumeric(ecoClass) && isFloat(price))
                            {
                                try
                                {
                                //set isnumeric and grab other vars
                                numeric = true;
                                departureTime = request.getParameter("Departure");
                                
                                //Create new flight object
                                flight = new Flight(Integer.parseInt(flightID), Integer.parseInt(routeID), Integer.parseInt(planeID), departureTime, Double.parseDouble(price), 
                                Integer.parseInt(firstClass), Integer.parseInt(businessClass), Integer.parseInt(ecoClass));
                                //proceed with updating flight
                                admin.updateFlight(flight, oldID);
                                //Display success message
                                out.println("<h3>Flight updated successfully.</h3>");
                                out.println("<br>");
                                }
                                catch(SQLIntegrityConstraintViolationException e)
                                {
                                    e.printStackTrace();
                                    System.out.println("Error! Primary or unique value already exists!.");
                                }
                                //Link back to login page
                                out.println("<a href=\"adminflights.html\" ><button tabindex=\"10\" >Return to admin flight screen</button></a>"); 
                                out.println("<br>");
                            }
                        }
                        break;
                    //DELETE FLIGHTS*****************************************************
                    case "deleteFlights":
                        //get list of Flights
                        //get list of Flights
                        flightList = admin.readFlights();
                        //get routes
                        routeMap = admin.getRoutesMap();
                        numeric = true;
                        //Display Flights
                        out.println("<h3>Flights in System:</h3><br><br>");
                        for(int i = 0; i < flightList.size(); i++)
                        {
                            out.println("<p>" + (i+1) + ".) Flight ID: " + flightList.get(i).getFlightID() + " Departure Date: " 
                                    + flightList.get(i).getDepDate() + "<br>Route: " + 
                                    routeMap.get(flightList.get(i).getRouteID())[1] + " to " + routeMap.get(flightList.get(i).getRouteID())[2] + "</p><br>");
                        }
                        //Ask for which flight to delete
                        out.println("<h3>Please choose a flight number to delete:</h3>");
                        out.println("<form action=\"AdminFlightServlet\" method=\"post\" >");
                        out.println("<input type=\"hidden\" id=\"flighttId\" name=\"action\" value=\"flightDelete\">");
                        out.println("<input type=\"text\" id=\"flightNum\" name=\"FlightNum\">");
                        out.println("<input type=\"submit\" value=\"Submit\" name=\"chooseFlight\" tabindex=\"2\" >");
                        out.println("<br>");
                        out.println("</form>");
                        //Link back to login page
                        out.println("<br><a href=\"adminflights.html\" ><button tabindex=\"10\" >Return to admin flight screen</button></a><br>"); 
                        break;
                    //Flight delete
                    case "flightDelete":
                        //get list of Flights
                        flightList = admin.readFlights();
                        numFlights = flightList.size();
                        target = request.getParameter("FlightNum");
                        numeric = isNumeric(target);

                        if(numeric)
                        {
                            //parse into int
                            int flightNum = Integer.parseInt(target)-1;
                            //Check if input in bounds
                            if(flightNum < 0 || flightNum >= numFlights)
                            {
                                numeric = false;
                                break;
                            }

                            //Delete Flight
                            flight = flightList.get(flightNum);
                            admin.deleteFlight(flight);
                            out.println("<br>");
                            out.println("<h2>FLIGHT DELETED:");
                            out.println("<br>");
                            //Link back to login page
                            out.println("<br><br><a href=\"adminflights.html\" ><button tabindex=\"10\" >Return to admin flight screen</button></a><br>");
                        }
                        break;
                    //READ FLIGHTS*****************************************************
                    case "readFlights":
                        //get list of Flights
                        flightList = admin.readFlights();
                        //get routes
                        routeMap = admin.getRoutesMap();
                        numeric = true;
                        //Display Flights
                        out.println("<h3>Flights in System:</h3><br><br>");
                        for(int i = 0; i < flightList.size(); i++)
                        {
                            out.println("<p>" + (i+1) + ".) Flight ID: " + flightList.get(i).getFlightID() + " Departure Date: " 
                                    + flightList.get(i).getDepDate() + "<br>Route: " + 
                                    routeMap.get(flightList.get(i).getRouteID())[1] + " to " + routeMap.get(flightList.get(i).getRouteID())[2] + "</p><br>");
                        }
                        
                        //Ask to see more details
                        out.println("<h3>Please choose a flight number to see more details for a flight:</h3>");
                        out.println("<form action=\"AdminFlightServlet\" method=\"post\" >");
                        out.println("<input type=\"hidden\" id=\"flightId\" name=\"action\" value=\"flightDetail\">");
                        out.println("<input type=\"text\" id=\"flightNum\" name=\"FlightNum\">");
                        out.println("<input type=\"submit\" value=\"Submit\" name=\"chooseFlight\" tabindex=\"2\" >");
                        out.println("<br>");
                        out.println("</form>");
                        //Link back to login page
                        out.println("<br><br><a href=\"adminflights.html\" ><button tabindex=\"10\" >Return to admin flight screen</button></a><br>"); 
                        break;
                        //Get Flight detail*****************************************************
                    case "flightDetail":
                        //get list of Flights
                        flightList = admin.readFlights();
                        numFlights = flightList.size();
                        target = request.getParameter("FlightNum");
                        //get routes
                        routeMap = admin.getRoutesMap();
                        numeric = isNumeric(target);

                        if(numeric)
                        {
                            //parse into int
                            int flightNum = Integer.parseInt(target)-1;
                            //Check if input in bounds
                            if(flightNum < 0 || flightNum >= numFlights)
                            {
                                numeric = false;
                                break;
                            }

                            //Display details
                            out.println("<h2>Flight Details:");
                            out.println("<br>");
                            out.println("<h3>Flight ID: " + flightList.get(flightNum).getFlightID() + "</h3>");
                            out.println("<h3>Route ID: " + flightList.get(flightNum).getRouteID() + " " 
                                    + routeMap.get(flightList.get(flightNum).getRouteID())[1] + " to " + routeMap.get(flightList.get(flightNum).getRouteID())[2] + "</h3>");
                            out.println("<h3>Plane ID: " + flightList.get(flightNum).getPlaneID() + "</h3>");
                            out.println("<h3>Departure Time: " + flightList.get(flightNum).getDepDate() + "</h3>");
                            out.println("<h3>Seat Price: $" + flightList.get(flightNum).getSeatPrice() + "</h3>");
                            out.println("<h3>Reserved First Class Seats: " + flightList.get(flightNum).getResFirstSeats() + "</h3>");
                            out.println("<h3>Reserved Business Class Seats: " + flightList.get(flightNum).getResBusSeats() + "</h3>");
                            out.println("<h3>Reserved Economy Class Seats: " + flightList.get(flightNum).getResEcoSeats() + "</h3>");
                            out.println("<br>");
                            //Link back to login page
                            out.println("<br><br><a href=\"adminflights.html\" ><button tabindex=\"10\" >Return to admin flight screen</button></a><br>");
                        }
                        break;
                    //DEFAULT*****************************************************
                default:
                     //give input error message
                    out.println("<h3>Something went wrong!</h3>");
                    out.println("<br>");
                    //Link back to login page
                    out.println("<a href=\"adminlanding.html\" ><button tabindex=\"10\" >Return to previous screen</button></a>"); //This is weird because it doesn't like quotes
                    out.println("<br>");
                    break;
            }
            if(!numeric)
            {
               //give input error message
              out.println("<h3>Input is incorrect!</h3>");
              out.println("<br>");
              //Link back to login page
              out.println("<a href=\"adminlanding.html\" ><button tabindex=\"10\" >Return to previous screen</button></a>"); //This is weird because it doesn't like quotes
              out.println("<br>"); 
            }
            out.println("</section>");
            out.println("<main>");
            out.println("<footer>\n" +
"                Copyright &copy Utopia Airlines 2021<br>\n" +
"                <script>\n" +
"                    document.write(\"Last modified on \" + document.lastModified);\n" +
"                </script><br>\n" +
"                <a href=\"mailto:mail@utopia.com\" >mail@utopia.com</a><br>\n" +
"            </footer> ");
            //close the html tag
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
            this.getServletInfo();
            
            out.close();
        }
        //Both cathes should open an error page. 
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("Input is invalid!");
            java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://localhost:8080/UtopiaProject/loginerror.html"));
            
        }
        catch(IOException | InputMismatchException | IllegalArgumentException | IllegalStateException | SQLIntegrityConstraintViolationException | ServletException e)
        {
            e.printStackTrace();
            System.out.println("Error! Something went wrong.");
            java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://localhost:8080/UtopiaProject/loginerror.html"));
        }
        finally
        {
            //If it throws, we need to throw  
            throw new ServletException();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }
    
    //*isNumeric****************************************************************
    //checks if value is numeric
    public boolean isNumeric(String str) 
    { 
        try 
        {  
            Integer.parseInt(str);  
            return true;
        } 
        catch(NumberFormatException e)
        {  
            return false;  
        }  
    }
    
    //*isFloat****************************************************************
    //checks if value is a float
    public boolean isFloat(String str) 
    { 
        try 
        {  
            Double.parseDouble(str);  
            return true;
        } 
        catch(NumberFormatException e)
        {  
            return false;  
        }  
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
