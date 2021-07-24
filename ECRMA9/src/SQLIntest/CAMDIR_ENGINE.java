/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLIntest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author apmbonifacio
 */
public class CAMDIR_ENGINE {
             
    Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRs = null;

    String user = "root";
    String pass = "admin";

    public void getConnection()
    {
        try {
        // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/intest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", user, pass);
        }catch(SQLException se)	{System.out.println(se.getMessage());}  
    }      
    
    public void addRow(int candId, int camId)
    {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="INSERT INTO `CAMDIR`(`CANDID`,`CAMID`) VALUES ('"
                    + candId + "','"
                    + camId + "');";
            myStmt.executeUpdate(qry);
            myStmt.close();
            
	} catch(SQLException se){
            System.out.println("CAMDIR ADDROW ERROR");
            System.out.println(se.getMessage());}
    }
   
    public void deleteRow(int candId, int camId)
    {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String qry="DELETE FROM CAMPAIGNS WHERE CANDID = " + camId 
                    + "AND CAMID = " + camId;
            myStmt.executeUpdate(qry);
            myStmt.close();
            System.out.println("CANDIDATES_ENGINE: DELETEROW SUCCESS");
	}
	catch(SQLException se){
            System.out.println("CAMDIR DELETE ROW ERROR");
            System.out.println(se.getMessage());
        }
    }
}
