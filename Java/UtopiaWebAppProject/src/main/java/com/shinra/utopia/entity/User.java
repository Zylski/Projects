package com.shinra.utopia.entity;

/*
 * Programmer: Damian Zylski
 * Project:    UtopiaProject
 * Date:       03/20/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    The User class for the utopia project. corresponds to user in the database
 */
public class User
{
    //attributes
    private int userID;
    private int role;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    
    //constructors
    public User()
    {
        this.userID = 0;
        this.role = 0;
        this.userName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phone = "";
    }
    public User(int userID, int role, String userName, String password, String firstName, String lastName, String email, String phone)
    {
        this.userID = userID;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
    
    
    //getters
    public int getUserID()
    {
        return userID;
    }

    public int getRole()
    {
        return role;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhone()
    {
        return phone;
    }
    //setters
    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public void setRole(int role)
    {
        this.role = role;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    //hash
    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 97 * hash + this.userID;
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
        final User other = (User) obj;
        if (this.userID != other.userID)
        {
            return false;
        }
        return true;
    }
    //compareTo
    public int compareTo(User u)
    {
        if(this.userID > u.userID)
        {
            return 1;
        }
        else if(this.userID < u.userID)
        {
            return -1;
        }
        else if(this.userID == u.userID)
        {
            return 0;
        }
        else
        {
            return 0;
        }   
    } 
    //tostring
    @Override
    public String toString()
    {
        return "First Name: " + firstName + "\nLast Name: " + lastName + "\nUserID: " + userID + "\nRole: " + role + "\nUserName: " + userName +  "\nEmail: " + email + "\nPhone: " + phone;
    }

}
