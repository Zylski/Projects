package com.shinra.utopia.service;

import com.shinra.utopia.dao.UserDAO;
import com.shinra.utopia.entity.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Programmer: Damian Zylski
 * Project:    UtopiaProject
 * Date:       03/20/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The LoginService handles all requests related to the utopia login page
 */
public class LoginService
{
    //new util, and also connection
    Utility util = new Utility();
    
    //add user
    public List<User> getsUsers() throws SQLException
    {
        //the connection
        Connection conn = null;
        
        try
        {
            //get connection
            conn = util.getConnection();
            
            //create user object
            UserDAO udao = new UserDAO(conn);
            
            //PK
            Integer pk = null;
            
            //Create new user list
            List <User> userList = new ArrayList<User>();
            
            //get list from db
            userList = udao.readData();
            
            //commit changes if no exceptions
            conn.commit();
            //Return success message
            
            //return list
            return userList;
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
        return null;
    }
    
    //add user
    public boolean checkLogin(Integer id, String password) throws SQLException
    {
        //the connection
        Connection conn = null;
        
        try
        {
            //get connection
            conn = util.getConnection();
            
            //create user object
            UserDAO udao = new UserDAO(conn);
            
            return udao.checkLogin(id, password);
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
        return false;
    }
    
    public User getUser(Integer id) throws SQLException
    {
        //the connection
        Connection conn = null;
        User user = null;
        
        try
        {
            //get connection
            conn = util.getConnection();
            
            //create user object
            UserDAO udao = new UserDAO(conn);
            
            user = udao.getUser(id);
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
        return user;
    }
}
