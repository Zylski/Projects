package com.shinra.utopia.service;
/*
 * Programmer: Damian Zylski
 * Project:    UtopiaProject
 * Date:       03/20/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The utility service helps create connection to db
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utility
{
    //Database connection properties
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3305/utopia";
    private static final String username = "zylski"; //Maybe set it up so it asks for this from input
    private static final String password = "maple123";
    
    //SQL Connection
    public Connection getConnection() throws ClassNotFoundException, SQLException
    {
        //Register sql driver
        Class.forName(driver);
        //Create connection
        Connection conn = DriverManager.getConnection(url, username, password);
        //adjust auto commit
        conn.setAutoCommit(Boolean.FALSE);
        return conn;
    }
}
