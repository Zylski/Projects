/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shinra.utopia.admin;

import com.shinra.utopia.entity.User;
import com.shinra.utopia.service.AdminService;
import com.shinra.utopia.service.LoginService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * Programmer: Damian Zylski
 * Project:    LoginServlet
 * Date:       03/22/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To handle login requests for administrator functions. I was originally going 
 * to use this to handle all database elements but I realize I need to split this up into smaller
 * pieces. So there is a lot of repeat code in the cases that could have just been written once.
 * I think it would make more sense to create a servlet for each database element. This Servlet will
 * handle Users only. 
 */
@WebServlet("/adminservlet")
public class AdminServlet extends HttpServlet
{
    private AdminService admin;

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
            //Create new admin service
            admin = new AdminService();
            List <User> userList = null;
            User user = null;
            Integer choice = 0;
            boolean numeric = false;
            Integer numUsers = null;
            String target = null;
            
            //Determine type of operation
            String action = request.getParameter("action");                   
            
            /* Output Servlet info*/
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>UTOPIA ADMIN USERS</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"stylesheet\" href=\"utopia.css\" >");
            out.println("<meta name=\"description\" content=\"A user servlet web page\">");
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
                //ADD USERS*****************************************************
                case "addUsers":
                    //get int parameters
                    String userID = request.getParameter("ID");
                    String userRoleId = request.getParameter("Role");
                    //check if id and role are numeric
                    if(isNumeric(userID) && isNumeric(userRoleId))
                    {
                        //set isnumeric and grab other vars
                        numeric = true;
                        String fName = request.getParameter("First Name");
                        String lName = request.getParameter("Last Name");
                        String userName = request.getParameter("Username");
                        String userEmail = request.getParameter("Email");
                        String password = request.getParameter("Password");
                        String userPhone = request.getParameter("Phone");
                        //create new user
                        user = new User(Integer.parseInt(userID), Integer.parseInt(userRoleId), userName, password, fName, lName, userEmail, userPhone);
                        //proceed with adding user
                        admin.addUser(user);
                        //Display success message
                        out.println("<h3>User added successfully.</h3>");
                        out.println("<br>");
                        //Link back to login page
                        out.println("<a href=\"adminusers.html\" ><button tabindex=\"10\" >Return to previous screen</button></a>"); 
                        out.println("<br>");
                    }
                    break;
                    //UPDATE USERS*****************************************************
                    case "updateUsers":
                        //get list of Users
                        userList = admin.readUsers();
                        numeric = true;
                        
                        //Display Users
                        out.println("<h3>Users in System:</h3><br><br>");
                        for(int i = 0; i < userList.size(); i++)
                        {
                            out.println("<p>" + (i+1) + ".) " + userList.get(i).getFirstName() + " " 
                                    + userList.get(i).getLastName() + "</p><br>");
                        }
                        //Ask for which user to update
                        out.println("<h3>Please choose a user number to update:</h3>");
                        out.println("<form action=\"AdminServlet\" method=\"post\" >");
                        out.println("<input type=\"hidden\" id=\"custId\" name=\"action\" value=\"userUpdate\">");
                        out.println("<input type=\"text\" id=\"userNum\" required=\"required\" name=\"UserNum\">");                       
                        out.println("<br>");
                        out.println("<h3>Enter the details for the user to update:</h3>");
                        out.println("<label for=\"Name\" >ID: </label><br>");
                        out.println("<input type=\"text\" name=\"ID\" size=\"25\" required=\"required\" tabindex=\"1\"><br>");
                        out.println("<label for=\"Name\" >Role ID: </label><br>");
                        out.println("<input type=\"text\" name=\"Role\" size=\"25\" required=\"required\" tabindex=\"2\"><br>");
                        out.println("<label for=\"Name\" >First Name: </label><br>");
                        out.println("<input type=\"text\" name=\"First Name\" size=\"25\" required=\"required\" tabindex=\"3\"><br>");
                        out.println("<label for=\"Name\" >Last Name: </label><br>");
                        out.println("<input type=\"text\" name=\"Last Name\" size=\"25\" required=\"required\" tabindex=\"4\"><br>");
                        out.println("<label for=\"Name\" >Username: </label><br>");
                        out.println("<input type=\"text\" name=\"Username\" size=\"25\" required=\"required\" tabindex=\"5\"><br>");
                        out.println("<label for=\"Name\" >Email: </label><br>");
                        out.println("<input type=\"text\" name=\"Email\" size=\"25\" required=\"required\" tabindex=\"6\"><br>");
                        out.println("<label for=\"Name\" >Password: </label><br>");
                        out.println("<input type=\"text\" name=\"Password\" size=\"25\" required=\"required\" tabindex=\"7\"><br>");
                        out.println("<label for=\"Name\" >Phone: </label><br>");
                        out.println("<input type=\"text\" name=\"Phone\" size=\"25\" required=\"required\" tabindex=\"8\"><br>");
                        out.println("<br>");
                        out.println("<input type=\"submit\" value=\"Submit\" name=\"chooseUser\" tabindex=\"2\" ><br>");
                        out.println("</form>");
                        //Link back to login page
                        out.println("<br><a href=\"adminusers.html\" ><button tabindex=\"10\" >Return to previous screen</button></a><br>"); 
                        break;
                        //User delete
                    case "userUpdate":
                        //get list of Users
                        userList = admin.readUsers();
                        numUsers = userList.size();
                        target = request.getParameter("UserNum");
                        numeric = isNumeric(target);
                        Integer oldID = 0;

                        if(numeric)
                        {
                            //parse into int
                            int userNum = Integer.parseInt(target)-1;
                            //Check if input in bounds
                            if(userNum < 0 || userNum >= numUsers)
                            {
                                numeric = false;
                                break;
                            }
                            //set old user ID
                            oldID = userList.get(userNum).getUserID();
                            System.out.println("Servlet Old ID: " + userNum);                            
                            //get int parameters
                            userID = request.getParameter("ID");
                            userRoleId = request.getParameter("Role");
                            //check if id and role are numeric
                            if(isNumeric(userID) && isNumeric(userRoleId))
                            {
                                try
                                {
                                //set isnumeric and grab other vars
                                numeric = true;
                                String fName = request.getParameter("First Name");
                                String lName = request.getParameter("Last Name");
                                String userName = request.getParameter("Username");
                                String userEmail = request.getParameter("Email");
                                String password = request.getParameter("Password");
                                String userPhone = request.getParameter("Phone");
                                //create new user
                                user = new User(Integer.parseInt(userID), Integer.parseInt(userRoleId), userName, password, fName, lName, userEmail, userPhone);
                                //proceed with adding user
                                admin.updateUser(user, oldID);
                                //Display success message
                                out.println("<h3>User updated successfully.</h3>");
                                out.println("<br>");
                                }
                                catch(SQLIntegrityConstraintViolationException e)
                                {
                                    e.printStackTrace();
                                    System.out.println("Error! Primary or unique value already exists!.");
                                }
                                //Link back to login page
                                out.println("<a href=\"adminusers.html\" ><button tabindex=\"10\" >Return to previous screen</button></a>"); 
                                out.println("<br>");
                            }
                        }
                        break;
                    //DELETE USERS*****************************************************
                    case "deleteUsers":
                        //get list of Users
                        userList = admin.readUsers();
                        numeric = true;
                        
                        //Display Users
                        out.println("<h3>Users in System:</h3><br><br>");
                        for(int i = 0; i < userList.size(); i++)
                        {
                            out.println("<p>" + (i+1) + ".) " + userList.get(i).getFirstName() + " " 
                                    + userList.get(i).getLastName() + "</p><br>");
                        }
                        //Ask for which user to delete
                        out.println("<h3>Please choose a user number to delete:</h3>");
                        out.println("<form action=\"AdminServlet\" method=\"post\" >");
                        out.println("<input type=\"hidden\" id=\"custId\" name=\"action\" value=\"userDelete\">");
                        out.println("<input type=\"text\" id=\"userNum\" name=\"UserNum\">");
                        out.println("<input type=\"submit\" value=\"Submit\" name=\"chooseUser\" tabindex=\"2\" >");
                        out.println("<br>");
                        out.println("</form>");
                        //Link back to login page
                        out.println("<br><a href=\"adminusers.html\" ><button tabindex=\"10\" >Return to previous screen</button></a><br>"); 
                        break;
                    //User delete
                    case "userDelete":
                        //get list of Users
                        userList = admin.readUsers();
                        numUsers = userList.size();
                        target = request.getParameter("UserNum");
                        numeric = isNumeric(target);

                        if(numeric)
                        {
                            //parse into int
                            int userNum = Integer.parseInt(target)-1;
                            //Check if input in bounds
                            if(userNum < 0 || userNum >= numUsers)
                            {
                                numeric = false;
                                break;
                            }

                            //Delete user
                            user = userList.get(userNum);
                            admin.deleteUser(user);
                            out.println("<br>");
                            out.println("<h2>USER DELETED:");
                            out.println("<br>");
                            //Link back to login page
                            out.println("<br><br><a href=\"adminusers.html\" ><button tabindex=\"10\" >Return to previous screen</button></a><br>");
                        }
                        break;
                    //READ USERS*****************************************************
                    case "readUsers":
                        //get list of Users
                        userList = admin.readUsers();
                        numeric = true;
                        
                        //Display Users
                        out.println("<h3>Users in System:</h3><br><br>");
                        for(int i = 0; i < userList.size(); i++)
                        {
                            out.println("<p>" + (i+1) + ".) " + userList.get(i).getFirstName() + " " 
                                    + userList.get(i).getLastName() + "</p><br>");
                        }
                        
                        //Ask to see more details
                        out.println("<h3>Please choose a user number to see more details for user:</h3>");
                        out.println("<form action=\"AdminServlet\" method=\"post\" >");
                        out.println("<input type=\"hidden\" id=\"custId\" name=\"action\" value=\"userDetail\">");
                        out.println("<input type=\"text\" id=\"userNum\" name=\"UserNum\">");
                        out.println("<input type=\"submit\" value=\"Submit\" name=\"chooseUser\" tabindex=\"2\" >");
                        out.println("<br>");
                        out.println("</form>");
                        //Link back to login page
                        out.println("<br><br><a href=\"adminusers.html\" ><button tabindex=\"10\" >Return to previous screen</button></a><br>"); 
                        break;
                        //Get user detail*****************************************************
                    case "userDetail":
                        //get list of Users
                        userList = admin.readUsers();
                        numUsers = userList.size();
                        target = request.getParameter("UserNum");
                        numeric = isNumeric(target);

                        if(numeric)
                        {
                            //parse into int
                            int userNum = Integer.parseInt(target)-1;
                            //Check if input in bounds
                            if(userNum < 0 || userNum >= numUsers)
                            {
                                numeric = false;
                                break;
                            }

                            //Display details
                            out.println("<h2>User Details:");
                            out.println("<br>");
                            out.println("<h3>Name: " + userList.get(userNum).getFirstName() + " " + userList.get(userNum).getLastName() + "</h3>");
                            out.println("<h3>ID Number: " + userList.get(userNum).getUserID() + "</h3>");
                            out.println("<h3>Role: " + userList.get(userNum).getRole() + "</h3>");
                            out.println("<h3>Username: " + userList.get(userNum).getUserName() + "</h3>");
                            out.println("<h3>Email: " + userList.get(userNum).getEmail() + "</h3>");
                            out.println("<h3>Phone: " + userList.get(userNum).getPhone() + "</h3>");
                            out.println("<br>");
                            //Link back to login page
                            out.println("<br><br><a href=\"adminusers.html\" ><button tabindex=\"10\" >Return to previous screen</button></a><br>");
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
        catch(IOException | InputMismatchException | IllegalArgumentException | IllegalStateException | SQLIntegrityConstraintViolationException e)
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
