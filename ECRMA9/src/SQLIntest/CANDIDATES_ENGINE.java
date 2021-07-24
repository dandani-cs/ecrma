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
import javax.swing.JOptionPane;

/**
 *
 * @author apmbonifacio
 */
public class CANDIDATES_ENGINE {
     
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
    
    public void addRow(Candidates candidates)
    {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="INSERT INTO `CANDIDATES`(`CANDID`,`LNAME`,`FNAME`,`MINIT`,`BDAY`,`DEGREE`,`UNI`,`GRADDATE`,`PICNAME`) VALUES ("
                    + "NULL,'"
                    + candidates.lName + "','"
                    + candidates.fName + "','"
                    + candidates.mInit + "','"
                    + candidates.bDay.getYear() + "-" + candidates.bDay.getMonth() + "-" + candidates.bDay.getDate() + "','"
                    + candidates.degree + "','"
                    + candidates.uni + "','"
                    + candidates.gradDate.getYear() + "-" + candidates.gradDate.getMonth() + "-" + candidates.gradDate.getDate() + "','"
                    + candidates.picName + "');";
            myStmt.executeUpdate(qry);
            myStmt.close();
            
            System.out.println("CANDIDATES_ENGINE: ADDROW SUCCESS (" +  candidates.fName + " " + candidates.lName + ")");
	} catch(SQLException se){
            //System.out.println("CANDIDATES_ENGINE: ADDROW FAIL (" +  candidate.fName + " " + candidate.lName + ")");
            System.out.println(se.getMessage());}
    }
    
    public void editRow(Candidates candidates)
    {
        getConnection();
        try{            
            myStmt=myConn.createStatement();	
            String qry="DELETE FROM CANDIDATES WHERE CANDID = " + candidates.candId;
            myStmt.executeUpdate(qry);
            
            qry="INSERT INTO `CANDIDATES`(`CANDID`,`LNAME`,`FNAME`,`MINIT`,`BDAY`,`DEGREE`,`UNI`,`GRADDATE`,`PICNAME`) VALUES ("
                    + candidates.candId + ",'"
                    + candidates.lName + "','"
                    + candidates.fName + "','"
                    + candidates.mInit + "','"
                    + candidates.bDay.getYear() + "-" + candidates.bDay.getMonth() + "-" + candidates.bDay.getDate() + "','"
                    + candidates.degree + "','"
                    + candidates.uni + "','"
                    + candidates.gradDate.getYear() + "-" + candidates.gradDate.getMonth() + "-" + candidates.gradDate.getDate() + "','"
                    + candidates.picName + "');";
            myStmt.executeUpdate(qry);
            myStmt.close();
            System.out.println("CANDIDATES_ENGINE: EDITROW SUCCESS (" +  candidates.lName + " " + candidates.fName + ")");
	}
	catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}
    }
    
    public void deleteRow(int candId)
    {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String qry="DELETE FROM CANDIDATES WHERE CANDID = " + candId;
            myStmt.executeUpdate(qry);
            myStmt.close();
            System.out.println("CANDIDATES_ENGINE: DELETEROW SUCCESS (" +  candId + ")");
	}
	catch(SQLException se){System.out.println(se.getMessage());}
    }
    
    public Candidates getRow(int candId) {
        Candidates candidates = new Candidates();
        
        getConnection();
        try{
            myStmt=myConn.createStatement();	

            String qry = "SELECT * FROM CANDIDATES WHERE CANDID = '" + candId + "';";
            ResultSet rs = myStmt.executeQuery(qry);
            rs.next();
            candidates = new Candidates(rs.getInt("CANDID"),
                    rs.getString("LNAME"),
                    rs.getString("FNAME"),
                    rs.getString("MINIT"),
                    rs.getDate("bDay"),
                    rs.getString("DEGREE"),
                    rs.getString("uni"),
                    rs.getDate("gradDate"),
                    rs.getString("picName"));
            rs.close();
            myStmt.close();
            System.out.println("RS:" + candidates.candId + " " + candidates.lName +" " + candidates.fName);
	}
	catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}
        

        return candidates;
    };
    
    public ArrayList<Candidates> getTable()
    {
        ArrayList<Candidates> candidates=new ArrayList<>();
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String query = "SELECT * FROM CANDIDATES;";
	    ResultSet rs = myStmt.executeQuery(query);

	    while(rs.next())
            {
                candidates.add(
                        new Candidates(rs.getInt("CANDID"),
                        rs.getString("LNAME"),
                        rs.getString("FNAME"),
                        rs.getString("MINIT"),
                        rs.getDate("bDay"),
                        rs.getString("DEGREE"),
                        rs.getString("uni"),
                        rs.getDate("gradDate"),
                        rs.getString("picName")));
            }
            rs.close();
	    myStmt.close();				
        }
	catch(SQLException se){System.out.println(se.getMessage());}
        return candidates;
    }
}
