package com.shinra.utopia.login;

import com.shinra.utopia.entity.User;
import com.shinra.utopia.service.AdminService;
import com.shinra.utopia.service.LoginService;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Programmer: Damian Zylski
 * Project:    LoginServlet
 * Date:       03/20/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To handle login requests for the utopia server. 
 */

@WebServlet("/utopialogin")
public class UtopiaLogin extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //hard coded private login and password info, I'm sure there's a way to pull this from
    //the server but don't know how yet
    private List <User> userList;
    private LoginService login;
    
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
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //Variables 
        boolean correct = false;
        String adminPage = "<a style=\"color:#FFFFFF;text-decoration: none;\n" +
"        font-size: 150%;\n" +
"        padding: 1%;border:5px inset #CCCCCC;margin: 1%\" href=\"adminlanding.html\" > Proceed to Admin Page!</a>";
               
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            //login servlet needs to get list of users and their info
            login = new LoginService();
            userList = login.getsUsers();
            User user = null;
            Integer role = 2;
            
            
            //handle input and check if login success
            String inputID = request.getParameter("Name");
            String inputPassword = request.getParameter("Password");
            
            //Check if input id is numeric
            boolean isNumber = isNumeric(inputID);            
            
            //Check if password matches what is in the system
            //Make sure input is numeric
            if(isNumber)
            {                             
                Integer id = Integer.parseInt(inputID);
                if(login.checkLogin(id, inputPassword))
                {
                    //Password is correct
                    correct = true;
                    //Create user
                    user = login.getUser(id);  
                }
            }
            else
            {
                //password is wrong
                correct = false;
            }
            
            /* Output Servlet info*/
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>UTOPIA LOGIN</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"stylesheet\" href=\"utopia.css\" >");
            out.println("<meta name=\"description\" content=\"A login servlet web page\">");
            out.println("<meta name=\"author\" content=\"Damian Zylski\">");
            out.println("<meta name = \"revised\" content = \"Shinra, 4/01/2021\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=wrapper >");
            out.println("<header>");
            out.println("<h1>Utopia Login Page</h1>");
            out.println("</header>");
            out.println("<nav>");
            out.println("<a href=\"index.html\" ><button>Home</button></a>");
            out.println("</nav>");
            out.println("<main>");
            out.println("<section>");
            out.println("<br>");
            //output based on whether password is correct or not
            //Right now we are only handling input for admin users!
            if(correct && user.getRole() == 1) //correct
            {
                //welcome user and log them in
                out.println("<h3 style=\"color:#FFFFFF;text-decoration: none;\n" +
"        font-size: 200%;\n" +
"        padding: 1%;margin: 1%\" > Welcome " + user.getFirstName() + "! You are now logged in. </h3>");
                out.println("<br>");
                //Redirect users based on their role id
                //For admin users
               if(user.getRole() == 1)
               {
                    out.println("<br>");
                    out.println(adminPage);
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<br>");
               }
                
            }
            else //wrong
            {
              //give login error response
              out.println("<h3>Username or password is incorrect!</h3>");
              out.println("<br>");
              //Link back to login page
              out.println("<a href=index.html >Return to login screen</a>"); //This is weird because it doesn't like quotes
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
        catch(IOException | InputMismatchException | IllegalArgumentException | SQLException | IllegalStateException e)
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
        //set content type
        response.setContentType("text/html");        
        //process request
        RequestDispatcher rd = request.getRequestDispatcher("/path");
        //rd.forward(request, response);
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Login Status:";
    }// </editor-fold>

}
