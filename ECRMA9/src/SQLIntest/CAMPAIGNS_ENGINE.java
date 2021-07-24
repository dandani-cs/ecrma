package SQLIntest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CAMPAIGNS_ENGINE {
         
    //CAMDIR_ENGINE camdir_engine = new CAMDIR_ENGINE();
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
    
    public void addRow(Campaigns campaigns)
    {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="INSERT INTO `Campaigns`(`CANDID`, `ELECID`,`PARTY`,`POSITION`,`PLATFORM`) VALUES ('"
                    + campaigns.candId + "','"
                    + campaigns.elecId + "','"
                    + campaigns.party + "','"
                    + campaigns.position + "','"
                    + campaigns.platform + "');";
            myStmt.executeUpdate(qry);
            
            qry = "SELECT * FROM `CAMPAIGNS` WHERE CANDID =" + campaigns.candId 
                    + " AND ELECID = " + campaigns.elecId;
            ResultSet rs = myStmt.executeQuery(qry);
            rs.next();
            campaigns.camId = rs.getInt("CAMID");
            rs.close();
            
            //camdir_engine.addRow(campaigns.candId, campaigns.camId);
            myStmt.close();
            
            
            System.out.println("CAMPAIGNS_ENGINE: ADDROW SUCCESS");
	} catch(SQLException se){
            System.out.println(se.getMessage());}
    }
    
    public void editRow(Campaigns campaigns)
    {
        getConnection();
        try{            
            myStmt=myConn.createStatement();	
            String qry="DELETE FROM CAMPAIGNS WHERE CAMID = " + campaigns.camId;
            myStmt.executeUpdate(qry);
            
            qry="INSERT INTO `Campaigns`(`ELECID`,`PARTY`,`POSITION`,`PLATFORM`) VALUES ('"
                    + campaigns.elecId + "','"
                    + campaigns.party + "','"
                    + campaigns.position + "','"
                    + campaigns.platform + "');";
            myStmt.executeUpdate(qry);
            myStmt.close();
            System.out.println("CAMPAIGNS_ENGINE: EDITROW SUCCESS");
	}
	catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}
    }
    
    public void deleteRow(int camId)
    {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String qry="DELETE FROM CAMPAIGNS WHERE camId = " + camId;
            myStmt.executeUpdate(qry);
            myStmt.close();
            System.out.println("CANDIDATES_ENGINE: DELETEROW SUCCESS");
	}
	catch(SQLException se){System.out.println(se.getMessage());}
    }
    
    public Campaigns getRow(int camId) {
        Campaigns campaigns = new Campaigns();
        
        getConnection();
        try{
            myStmt=myConn.createStatement();	

            String qry = "SELECT * FROM CAMPAIGNS WHERE CAM = '" + camId + "';";
            ResultSet rs = myStmt.executeQuery(qry);
            rs.next();
            campaigns = new Campaigns(rs.getInt("CAMID"),
                    rs.getInt("CANDID"),
                    rs.getInt("ELECID"),
                    rs.getString("PARTY"),
                    rs.getString("POSITION"),
                    rs.getString("PLATFORM"));
            rs.close();
            myStmt.close();
	}
	catch(SQLException se)
	{
            System.out.println(se.getMessage());
	}
        return campaigns;
    };
    
    public ArrayList<Campaigns> getTable()
    {
        ArrayList<Campaigns> campaigns=new ArrayList<>();
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String query = "SELECT * FROM CANDIDATES;";
	    ResultSet rs = myStmt.executeQuery(query);

	    while(rs.next())
            {
                campaigns.add(
                        new Campaigns(rs.getInt("CAMID"),
                        rs.getInt("CANDID"),
                        rs.getInt("ELECID"),
                        rs.getString("PARTY"),
                        rs.getString("POSITION"),
                        rs.getString("PLATFORM")));
            }
            rs.close();
	    myStmt.close();				
        }
	catch(SQLException se){System.out.println(se.getMessage());}
        return campaigns;
    }
    
    public ArrayList<Campaigns> getTable(int candId)
    {
        ArrayList<Campaigns> campaigns=new ArrayList<>();
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            String query = "SELECT * FROM CAMPAIGNS WHERE CANDID = " + candId + ";";
	    ResultSet rs = myStmt.executeQuery(query);

	    while(rs.next())
            {
                campaigns.add(
                        new Campaigns(rs.getInt("CAMID"),
                        rs.getInt("CANDID"),
                        rs.getInt("ELECID"),
                        rs.getString("PARTY"),
                        rs.getString("POSITION"),
                        rs.getString("PLATFORM")));
            }
            rs.close();
	    myStmt.close();				
        }
	catch(SQLException se){System.out.println(se.getMessage() + "BAKA WALANG CAMPAIGN");}
        return campaigns;
    }
}