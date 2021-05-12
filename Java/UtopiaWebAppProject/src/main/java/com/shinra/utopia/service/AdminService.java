package com.shinra.utopia.service;
import com.shinra.utopia.dao.UserDAO;
import com.shinra.utopia.entity.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/*
 * Programmer: Damian Zylski
 * Project:    UtopiaProject
 * Date:       03/20/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The AdminService class handles all actions and logic associated with admin users, like adding
 * or deleting records. This one will primarily service User records
 */
public class AdminService
{
    //new util, and also connection
    Utility util = new Utility();
    
    //add user
    public void addUser(User user) throws SQLException, SQLIntegrityConstraintViolationException
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
            
            //Add new user
            pk = udao.addUser(user);
            
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
    //update user
    public void updateUser(User user, Integer userNum) throws SQLException, SQLIntegrityConstraintViolationException
    {
        //the connection
        Connection conn = null;
        
        try
        {
            //get connection
            conn = util.getConnection();
            
            //create user DAO object
            UserDAO udao = new UserDAO(conn);
            
            //PK
            Integer pk = null;
            
            //Update user
            pk = udao.updateUser(user, userNum);
            
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
    
    //delete user
    public void deleteUser(User user) throws SQLException
    {
        //the connection
        Connection conn = null;
        
        try
        {
            //get connection
            conn = util.getConnection();
            
            //create user DAO object
            UserDAO udao = new UserDAO(conn);
            
            //PK
            Integer pk = null;
            
            //delete user
            pk = udao.deleteUser(user);
            
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
    
    //read users
    public List <User> readUsers() throws SQLException
    {
        //the connection
        Connection conn = null;
        //User list
        List <User> userList = null;
        
        try
        {
            //get connection
            conn = util.getConnection();
            
            //create user object
            UserDAO udao = new UserDAO(conn);
            
            //get list of users
            userList = udao.readData();
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
        return userList;
    }

}
