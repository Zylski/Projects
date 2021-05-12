package com.shinra.utopia.dao;

import com.shinra.utopia.entity.User;
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
 * Date:       03/20/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The User DAO for the utopia project, corresponds to user in the database
 */
public class UserDAO extends BaseDAO
{
    //connection constructor
    public UserDAO(Connection conn)
    {
        super(conn);
    }
    
    //Add operation
    public Integer addUser(User user) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        return crudReturnPK("INSERT INTO user(id, role_id, given_name, family_name, username, email, password, phone) VALUES(?,?,?,?,?,?,?,?);", new Object[] {user.getUserID(), 
            user.getRole(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail(), user.getPassword(), user.getPhone()});
    }
    //update operation
    public Integer updateUser(User user, Integer oldID) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        return crudReturnPK("UPDATE user SET id=?,role_id=?,given_name=?,family_name=?,username=?,email=?,password=?,phone=? WHERE id=?;", new Object[] {user.getUserID(), 
            user.getRole(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail(), user.getPassword(), user.getPhone(), oldID});
    }
    //delete operation
    public Integer deleteUser(User user) throws ClassNotFoundException, SQLException, ServletException
    {
        return crudReturnPK("DELETE FROM user WHERE id = ?;", new Object[] {user.getUserID()});
    }
    
    //getIndex operation
    public User getUser(Integer ID) throws ClassNotFoundException, SQLException
    {
        //Create user
        User user = null;
        //Create statement for handling queries
        PreparedStatement stmt = UserDAO.conn.prepareStatement("SELECT * FROM user WHERE id = ?");
        //set values
        stmt.setInt(1,ID);
        //execute query
        ResultSet rs = stmt.executeQuery();
        //Return true if result set exists
        if(rs.next())
        {
            user = new User(rs.getInt("id"),rs.getInt("role_id"),rs.getString("username"), rs.getString("password"), rs.getString("given_name"),
            rs.getString("family_name"), rs.getString("email"), rs.getString("phone"));
        }
        return user;
    }
    
    //check for valid login operation
    public boolean checkLogin(Integer id, String password) throws ClassNotFoundException, SQLException
    {
        //Create statement for handling queries
        PreparedStatement stmt = UserDAO.conn.prepareStatement("SELECT * FROM user WHERE id = ? AND password = ?");
        //set values
        stmt.setInt(1,id);
        stmt.setString(2,password);
        //execute query
        ResultSet rs = stmt.executeQuery();
        //Return true if result set exists
        if(rs.next())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Read all operation
    @Override
    public List<User> readData() throws ClassNotFoundException, SQLException
    {
        //Create new list
        List<User> userList = new ArrayList <User>();
        
        //Create statement for handling queries
        PreparedStatement stmt = UserDAO.conn.prepareStatement("SELECT * FROM user");
        //execute query
        ResultSet rs = stmt.executeQuery();

        //get database members
        while(rs.next())
        {
            //add user to list
            userList.add(new User(rs.getInt("id"), rs.getInt("role_id"), rs.getString("username"), rs.getString("password"), rs.getString("given_name"), rs.getString("family_name"),
            rs.getString("email"), rs.getString("phone")));
        }
        return userList;
    }
}
