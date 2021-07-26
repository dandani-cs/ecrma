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
import java.util.ArrayList;
import java.util.Date;


public class ElecPerSQL {
    static Connection myConn = null;
    static Statement myStmt = null;
    
    static String user = "root";
    static String pass = "admin";

    public static void getConnection()
    {
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/intest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", user, pass);
        }catch(SQLException se)	{System.out.println(se.getMessage());}  
    }
    
    public static void addRow(ElecPer elecPer) {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String sDate = elecPer.getSdate().getYear() + "-"
                    + elecPer.getSdate().getMonth() + "-"
                    + elecPer.getSdate().getDate();
            
            String fDate = elecPer.getFdate().getYear() + "-"
                    + elecPer.getFdate().getMonth() + "-"
                    + elecPer.getFdate().getDate();
            
            int archived;
            if(elecPer.isArchived() == true) {
                archived = 1;
            } else {
                archived = 0;
            }
            
            String qry="INSERT INTO ELECPER (NAME, SDATE, FDATE, ARCHIVED) VALUES ('"
                    + elecPer.getName() + "','"
                    + sDate + "','"
                    + fDate + "',"
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
            String sDate = elecPer.getSdate().getYear() + "-"
                    + elecPer.getSdate().getMonth() + "-"
                    + elecPer.getSdate().getDate();
            
            String fDate = elecPer.getFdate().getYear() + "-"
                    + elecPer.getFdate().getMonth() + "-"
                    + elecPer.getFdate().getDate();
            
            int archived;
            if(elecPer.isArchived() == true) {
                archived = 1;
            } else {
                archived = 0;
            }
            
            myStmt=myConn.createStatement();	
            
            String qry="UPDATE ELECPER SET NAME = '" + elecPer.getName() + "', "
                    + "SDATE = '" + sDate + "', " 
                    + "FDATE = '" + fDate + "', " 
                    + "ARCHIVED = " + archived 
                    + " WHERE ELECPERID = " + elecPer.getElecPerId() + ";";
            myStmt.executeUpdate(qry);                        
            
            myStmt.close();
            
            System.out.println("ELECPER SQL: EDIT ROW SUCCESSFUL");
	} catch(SQLException se){
            System.out.println("ELECPER SQL: EDIT ROW FAIL\n" + se.getMessage() + "\n");
        }
    }
    
    public static Object[] getRow(int elecPerId) {
                getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="SELECT * FROM ELECPER WHERE ELECPERID = " + elecPerId + ";";
            ResultSet rs = myStmt.executeQuery(qry);             
            rs.next();
            
            String sDateStr = rs.getString("SDATE");
            int sDateYear = Integer.valueOf(sDateStr.substring(0, 4));
            int sDateMonth = Integer.valueOf(sDateStr.substring(5, 7));
            int sDateDate = Integer.valueOf(sDateStr.substring(8, 10));
            Date sDate = new Date(sDateYear, sDateMonth, sDateDate);
            
            String fDateStr = rs.getString("fDATE");
            int fDateYear = Integer.valueOf(fDateStr.substring(0, 4));
            int fDateMonth = Integer.valueOf(fDateStr.substring(5, 7));
            int fDateDate = Integer.valueOf(fDateStr.substring(8, 10));
            Date fDate = new Date(fDateYear, fDateMonth, fDateDate);
            
            //Date sDate = new Date();
            Object[] elecPer = new Object[] {
                    rs.getString("NAME"),
                    sDate,
                    fDate,
                    rs.getBoolean("ARCHIVED")};

            rs.close();
            myStmt.close();
            
            System.out.println("ELECPER SQL: GET ROW SUCCESSFUL");
            
            return elecPer;
	} catch(SQLException se){
            System.out.println("ELECPER SQL: GET ROW FAIL\n" + se.getMessage() + "\n");
        }
        return null;
    }
    
    public static Object[][] getTable() {
                getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="SELECT * FROM ELECPER WHERE ARCHIVED = 0;";
            ResultSet rs = myStmt.executeQuery(qry);                        
            
            
            String sDateStr, fDateStr;
            int sDateYear, sDateMonth, sDateDate;
            int fDateYear, fDateMonth, fDateDate;
            Date sDate, fDate;
            ArrayList<Object[]> al = new ArrayList<>();
            while(rs.next()) {
                
            sDateStr = rs.getString("SDATE");
            sDateYear = Integer.valueOf(sDateStr.substring(0, 4));
            sDateMonth = Integer.valueOf(sDateStr.substring(5, 7));
            sDateDate = Integer.valueOf(sDateStr.substring(8, 10));
            sDate = new Date(sDateYear, sDateMonth, sDateDate);
            
            fDateStr = rs.getString("fDATE");
            fDateYear = Integer.valueOf(fDateStr.substring(0, 4));
            fDateMonth = Integer.valueOf(fDateStr.substring(5, 7));
            fDateDate = Integer.valueOf(fDateStr.substring(8, 10));
            fDate = new Date(fDateYear, fDateMonth, fDateDate);
            
                al.add(new Object[] {
                rs.getString("NAME"),
                sDate,
                fDate,
                rs.getBoolean("ARCHIVED")});
            }
            
            rs.close();
            myStmt.close();
            
            Object[][] elecPer = new Object[al.size()][4];
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
