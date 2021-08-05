//TABLE SCHEMA

//    CREATE TABLE CAMPAIGNS(
//        CAMPAIGNID INT NOT NULL AUTO_INCREMENT,
//        CANDIDATEID INT,
//        ELECPERID INT,
//        PARTY VARCHAR(40),
//        POSITION VARCHAR(40),
//        PLATFORM TEXT,
//        PRIMARY KEY (CAMPAIGNID),
//        FOREIGN KEY (CANDIDATEID) REFERENCES CANDIDATES(CANDIDATE_ID),
//        FOREIGN KEY (ELECPERID) REFERENCES ELECPER(ELECPERID)
//    );


package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CampaignsSQL {
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
            
            String qry="CREATE TABLE CAMPAIGNS(CAMPAIGNID INT NOT NULL AUTO_INCREMENT, "
                    + "CANDIDATEID INT, "
                    + "ELECPERID INT, "
                    + "PARTY VARCHAR(40), "
                    + "POSITION VARCHAR(40), "
                    + "PLATFORM TEXT, "
                    + "PRIMARY KEY (CAMPAIGNID), "
                    + "FOREIGN KEY (CANDIDATEID) REFERENCES CANDIDATES (CANDIDATEID), "
                    + "FOREIGN KEY (ELECPERID) REFERENCES ELECPER(ELECPERID));";
            myStmt.executeUpdate(qry);
                        
            myStmt.close();
            
            System.out.println("CAMPAIGN SQL: CREATE TABLE SUCCESSFUL");
	} catch(SQLException se){
            System.out.println("CAMPAIGN SQL: CREATE TABLE FAIL\n" + se.getMessage() + "\n");
        }
    }

    public static void addRow(Campaigns campaign) {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="INSERT INTO CAMPAIGNS(CANDIDATEID, ELECPERID, PARTY, POSITION, PLATFORM) VALUES ("
                    + campaign.getCandidateId() + ","
                    + campaign.getElecPerId()+ ",'"
                    + campaign.getParty() + "','"
                    + campaign.getPosition() + "','"
                    + campaign.getPlatform()+ "');";
            myStmt.executeUpdate(qry);
                        
            //IN CASE MAGKAROON NG CANDIDATE-CAMPAIGN DIRECTORY
            /*
            qry = "SELECT CAMPAIGNID FROM CAMPAIGNS WHERE CANDID =" + campaign.getCandidateId()
                    + " AND ELECID = " + campaign.getElecPerId();
            ResultSet rs = myStmt.executeQuery(qry);
            rs.next();
            campaign.setCampaignId(rs.getInt("CAMPAIGNID"));
            rs.close();
            
            camdir_engine.addRow(campaigns.candId, campaigns.camId);
            */
            
            myStmt.close();
            
            System.out.println("CAMPAIGN SQL: ADD ROW SUCCESSFUL");
	} catch(SQLException se){
            System.out.println("CAMPAIGN SQL: ADD ROW FAIL\n" + se.getMessage() + "\n");
        }
    }
    
    public static void deleteRow(int candidateId, int elecPerId) { //CHANGE PARATMETER TO CAMPAIGNID IF USING CANDIDATE-CAMPAIGN DIRECTORY
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="DELETE FROM CAMPAIGNS WHERE CANDIDATEID = " + candidateId 
                    + " AND ELECPERID = " + elecPerId + ";";
            myStmt.executeUpdate(qry);
                        
            //IN CASE MAGKAROON NG CANDIDATE-CAMPAIGN DIRECTORY
            /*
                MAKE SURE TO DELETE RECORD FROM CANDIDATE-CAMPAIGN DIRECTORY
            */
            
            myStmt.close();
            
            System.out.println("CAMPAIGN SQL: DELETE ROW SUCCESSFUL");
	} catch(SQLException se){
            System.out.println("CAMPAIGN SQL: DELETE ROW FAIL\n" + se.getMessage() + "\n");
        }
    }
    
    public static void editRow(Campaigns campaign) {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="UPDATE CAMPAIGNS SET PARTY = '" + campaign.getParty() + "', "
                    + "POSITION = '" + campaign.getPosition() + "', " 
                    + "PLATFORM = '" + campaign.getPlatform() + "' "
                    + "WHERE CANDIDATEID = " + campaign.getCandidateId()
                    + " AND ELECPERID = " + campaign.getElecPerId() + ";";
            myStmt.executeUpdate(qry);                        
            
            myStmt.close();
            
            System.out.println("CAMPAIGN SQL: EDIT ROW SUCCESSFUL");
	} catch(SQLException se){
            System.out.println("CAMPAIGN SQL: EDIT ROW FAIL\n" + se.getMessage() + "\n");
        }
    }
        
    public static Object[] getRow(int candidateId, int elecPerId) {        
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="SELECT * FROM CAMPAIGNS WHERE CANDIDATEID = " + candidateId 
                    + " AND ELECPERID = " + elecPerId + ";";
            ResultSet rs = myStmt.executeQuery(qry);                        
            
            rs.next();
            Object[] candidate = new Object[] {
                elecPerId,
                rs.getString("PARTY"),
                rs.getString("POSITION"),
                rs.getString("PLATFORM")
            };
            
            rs.close();
            myStmt.close();
            
            candidate[0] = ElecPerSQL.getElecPerName(elecPerId);
            
            System.out.println("CAMPAIGN SQL: GET ROW (2 PARAM) SUCCESSFUL");
            
            return candidate;
	} catch(SQLException se){
            System.out.println("CAMPAIGN SQL: GET ROW (2 PARAM) FAIL\n" + se.getMessage() + "\n");
        }
        return null;
    }
    
    public static Object[][] getRows(int candidateId) {        
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            
            String qry="SELECT * FROM CAMPAIGNS WHERE CANDIDATEID = " + candidateId + ";";
            ResultSet rs = myStmt.executeQuery(qry);                        
            
            ArrayList<Object[]> al = new ArrayList<>();
            while(rs.next()) {
                al.add(new Object[] {
                ElecPerSQL.getElecPerName(rs.getInt("ELECPERID")),
                rs.getString("PARTY"),
                rs.getString("POSITION"),
                rs.getString("PLATFORM")});
            }
            Object[][] campaigns = new Object[al.size()][4];
            
            for(int i = 0; i < al.size(); i++) {
                campaigns[i] = al.get(i);
            }
            
            rs.close();
            myStmt.close();
            System.out.println("CAMPAIGN SQL: GET ROW (1 PARAM) SUCCESSFUL");
            
            return campaigns;
	} catch(SQLException se){
            System.out.println("CAMPAIGN SQL: GET ROW (1 PARAM) FAIL\n" + se.getMessage() + "\n");
        }
        return null;
    }
    
    public static String[] getPartiesOfElecper(int elecperid) {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            //select * from campaigns where elecperid = 1;
            String qry="SELECT DISTINCT PARTY FROM CAMPAIGNS WHERE ELECPERID = " + elecperid + ";";
            ResultSet rs = myStmt.executeQuery(qry);                        
            
            ArrayList<String> al = new ArrayList<>();
            while(rs.next()) {
                al.add(rs.getString("PARTY"));
            }
            
            String[] parties = new String[al.size() + 1];
            
            for(int i = 0; i < al.size(); i++) {
                parties[i + 1] = al.get(i);
            }
            
            
            rs.close();
            myStmt.close();
            System.out.println("CAMPAIGN SQL: GET PARTIES OF ELECPER SUCCESSFUL");
            
            return parties;
	} catch(SQLException se){
            System.out.println("CAMPAIGN SQL: GET PARTIES OF ELECPER FAIL\n" + se.getMessage() + "\n");
        }
        return null;
    }
    
    public static String[] getPositionsOfElecper(int elecperid) {
        getConnection();
        try{
            myStmt=myConn.createStatement();	
            //select * from campaigns where elecperid = 1;
            String qry="SELECT DISTINCT POSITION FROM CAMPAIGNS WHERE ELECPERID = " + elecperid + ";";
            ResultSet rs = myStmt.executeQuery(qry);                        
            
            ArrayList<String> al = new ArrayList<>();
            while(rs.next()) {
                al.add(rs.getString("POSITION"));
            }
            
            String[] parties = new String[al.size() + 1];
            
            for(int i = 0; i < al.size(); i++) {
                parties[i + 1] = al.get(i);
            }
            
            
            rs.close();
            myStmt.close();
            System.out.println("CAMPAIGN SQL: GET POSITIOn OF ELECPER SUCCESSFUL");
            
            return parties;
	} catch(SQLException se){
            System.out.println("CAMPAIGN SQL: GET POSITION OF ELECPER FAIL\n" + se.getMessage() + "\n");
        }
        return null;
    }
}
