//TABLE SCHEMA

//    CREATE TABLE ELECPER(
//        ELECPERID INT NOT NULL AUTO_INCREMENT,
//        NAME VARCHAR(30),
//        SDATE DATE,
//        FDATE DATE,
//        ARCHIVED BOOLEAN,
//        PRIMARY KEY (ELECPERID)
//    );



package Model;

import static Model.CampaignsSQL.getConnection;
import static Model.CampaignsSQL.myConn;
import static Model.CampaignsSQL.myStmt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class ElecPerSQL {
    static Connection myConn = null;
    static Statement myStmt = null;
    
    static String user = "root";
    static String pass = "admin";
    static String db_name  = "ecrma";

    public static void getConnection()
    {
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", user, pass);
        }catch(SQLException se)	{System.out.println(se.getMessage());}  
    }
    
    public static void createTable() {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="CREATE TABLE ELECPER(ELECPER INT NOT NULL AUTO_INCREMENT, "
                    + "NAME VARCHAR(30), "
                    + "SDATE DATE, "
                    + "FDATE DATE, "
                    + "ARCHIVED BOOLEAN, "
                    + "PRIMARY KEY (ELECPERID));";

            myStmt.executeUpdate(qry);
                        
            myStmt.close();
            
            System.out.println("ELECPER SQL: CREATE TABLE SUCCESSFUL");
	} catch(SQLException se){
            System.out.println("ELECPER SQL: CREATE TABLE FAIL\n" + se.getMessage() + "\n");
        }
    }
    
    public static void addRow(ElecPer elecPer) {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
                        
            int archived;
            if(elecPer.isArchived() == true) {
                archived = 1;
            } else {
                archived = 0;
            }
            
            String qry="INSERT INTO ELECPER (NAME, SDATE, FDATE, ARCHIVED) VALUES ('"
                    + elecPer.getName() + "','"
                    + elecPer.getSdate() + "','"
                    + elecPer.getFdate() + "',"
                    + archived + ");";
            myStmt.executeUpdate(qry);                    
            myStmt.close();
            
            System.out.println("ELECPER SQL: ADD ROW SUCCESSFUL");
	} catch(SQLException se){
            System.out.println("ELECPER SQL: ADD ROW FAIL\n" + se.getMessage() + "\n");
        }
    }
    
    public static void deleteRow(int elecPerId) {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="DELETE FROM ELECPER WHERE ELECPERID = " + elecPerId + ";"; 
            myStmt.executeUpdate(qry);

            myStmt.close();
            
            System.out.println("ELECPER SQL: DELETE ROW SUCCESSFUL");
	} catch(SQLException se){
            System.out.println("ELECPER SQL: DELETE ROW FAIL\n" + se.getMessage() + "\n");
        }
    }
    
    public static void editRow(ElecPer elecPer) {
        getConnection();
        try{            

            int archived;
            if(elecPer.isArchived() == true) {
                archived = 1;
            } else {
                archived = 0;
            }
            
            myStmt=myConn.createStatement();	
            
            String qry="UPDATE ELECPER SET NAME = '" + elecPer.getName() + "', "
                    + "SDATE = '" + elecPer.getSdate() + "', " 
                    + "FDATE = '" + elecPer.getFdate() + "', " 
                    + "ARCHIVED = " + archived 
                    + " WHERE ELECPERID = " + elecPer.getElecPerId() + ";";
            myStmt.executeUpdate(qry);                        
            
            myStmt.close();
            
            System.out.println("ELECPER SQL: EDIT ROW SUCCESSFUL");
	} catch(SQLException se){
            System.out.println("ELECPER SQL: EDIT ROW FAIL\n" + se.getMessage() + "\n");
        }
    }
    
    public static void editArchived(int elecPerId,boolean archived) {
        getConnection();
        
        int intArchived = 0;
        if(archived == true) {
            intArchived = 1;
        }
        
        try{
            myStmt=myConn.createStatement();	
            
            String qry="UPDATE ELECPER SET ARCHIVED = " + intArchived + " WHERE ELECPERID = " + elecPerId + ";";
            myStmt.executeUpdate(qry);             
            
            myStmt.close();
            
            System.out.println("ELECPER SQL: EDIT ARCHIVED SUCCESSFUL");
            
	} catch(SQLException se){
            System.out.println("ELECPER SQL: EDIT ARCHIVED FAIL\n" + se.getMessage() + "\n");
        }
    }
    
    public static Object[][] getTable() {
                getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="SELECT * FROM ELECPER;";
            ResultSet rs = myStmt.executeQuery(qry); 
                        
            ArrayList<Object[]> al = new ArrayList<>();
            while(rs.next()) {
                
                al.add(new Object[] {
                rs.getInt("ELECPERID"),
                rs.getString("NAME"), 
                rs.getTimestamp("SDATE").toLocalDateTime().format(DateTimeFormatter.ofPattern("LLLL/dd/yyyy")),                               
                rs.getTimestamp("FDATE").toLocalDateTime().format(DateTimeFormatter.ofPattern("LLLL/dd/yyyy")),
                rs.getBoolean("ARCHIVED")});
            }
            
            rs.close();
            myStmt.close();
            
            Object[][] elecPer = new Object[al.size()][3];
            for(int i = 0; i < al.size(); i++) {
                elecPer[i] = al.get(i);
            }
            
            System.out.println("ELECPER SQL: GET TABLE SUCCESSFUL");
            
            return elecPer;
	} catch(SQLException se){
            System.out.println("ELECPER SQL: GET TABLE FAIL\n" + se.getMessage() + "\n");
        }
        return null;
    }
    
    public static String getElecPerName(int elecPerId){
      getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="SELECT * FROM ELECPER WHERE ELECPERID = " + elecPerId + ";";
            ResultSet rs = myStmt.executeQuery(qry);                        
            
            rs.next();
            String name = rs.getString("NAME");
            
            rs.close();
            myStmt.close();
            
            System.out.println("CAMPAIGN SQL: GET NAME SUCCESSFUL");
            
            return name;
	} catch(SQLException se){
            System.out.println("CAMPAIGN SQL: GET NAME FAIL\n" + se.getMessage() + "\n");
        }
        return null;
    };
}
