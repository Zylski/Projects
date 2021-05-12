package com.shinra.utopia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;

/*
 * Programmer: Damian Zylski
 * Project:    UtopiaProject
 * Date:       03/20/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The base DAO for the utopia project, is the base for all other daos.
 */
public abstract class BaseDAO
{    
    //Database connection properties
    protected static Connection conn = null;
    
    //Constructor for connection
    public BaseDAO(Connection conn)
    {
        this.conn = conn;
    }
    
    //Automatic statement for performing crud operations to database
    public void crud(String sql, Object [] values) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        //Prepared statement with query sent in
        PreparedStatement stmt = conn.prepareStatement(sql);
        //counter
        int count = 1;
        //read all values from received object
        for(Object o : values)
        {
            stmt.setObject(count,o);
            count++;
        }
        //execute query
        stmt.executeUpdate();
    }
    
    //thread safe method for crud and returning last used primary key
    public Integer crudReturnPK(String sql, Object [] values) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException, ServletException
    {
        //Prepared statement with query sent in
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //counter
        int count = 1;
        //read all values from received object
        for(Object o : values)
        {
            stmt.setObject(count,o);
            count++;
        }
        //execute query
        stmt.executeUpdate();
        
        //Get result set with key
        ResultSet rs = stmt.getGeneratedKeys();
        //ResultSet rs = stmt.executeQuery(); //Or try this?
        while(rs.next())
        {
            return rs.getInt(1);
        }
        return null;
    }
    
    //readData
    public abstract List readData() throws ClassNotFoundException, SQLException;
    
    
}
