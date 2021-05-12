package com.shinra.utopia.entity;

import java.util.List;

/*
 * Programmer: Damian Zylski
 * Project:    UtopiaProject
 * Date:       03/20/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The UserRole class for the utopia project, corresponds to user_role in the database
 */
public class UserRole
{
    //attributes 
    private int roleID;
    private String roleName;
    private List <User> userList;
    
    //getters
    public int getRoleID()
    {
        return roleID;
    }

    public String getRoleName()
    {
        return roleName;
    }
    
    public List getUserList()
    {
        return userList;
    }
    //setters
    public void setRoleID(int roleID)
    {
        this.roleID = roleID;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }
    
    //hash
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + this.roleID;
        return hash;
    }
    //equals
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
        final UserRole other = (UserRole) obj;
        if (this.roleID != other.roleID)
        {
            return false;
        }
        return true;
    }
    //compareTo
    public int compareTo(UserRole u)
    {
        if(this.roleID > u.roleID)
        {
            return 1;
        }
        else if(this.roleID < u.roleID)
        {
            return -1;
        }
        else if(this.roleID == u.roleID)
        {
            return 0;
        }
        else
        {
            return 0;
        }
    } 
    
    //toString
    @Override
    public String toString()
    {
        return "UserRole{" + "roleID=" + roleID + ", roleName=" + roleName + ", userList=" + userList + '}';
    }
}
