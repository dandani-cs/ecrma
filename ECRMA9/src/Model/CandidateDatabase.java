/*
 *  
    CREATE TABLE candidates(
        candidate_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
        last_name   VARCHAR(32),
        first_name  VARCHAR(32),
        mid_initial CHAR(1),
        birth_date  DATE,
        religion    VARCHAR(32),
        degree      VARCHAR(32),
        university  VARCHAR(32),
        grad_date   DATE,
        sex         VARCHAR(8),
        img_path    VARCHAR(32), 
        UNIQUE (last_name, first_name, mid_initial, birth_date, sex));
 */
package Model;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uriel Federez
 */
public class CandidateDatabase {
    private final DateFormat date_fmt = new SimpleDateFormat("yyyy-MM-dd");
    public  static final int ALL_CANDIDATES   = -1;
    private static final String ERROR_MSG_FMT = "[Candidate SQL (%s)] %s\n";
    
    private Connection db_connection = null;
    
    // TODO: standardize on these
    private String username = "root";
    private String password = "admin";
    private String db_name  = "ecrma";
    
    public void CandidateDatabase() {
        get_connection();
    }
    
    public void get_connection()
    {
        try 
        {
            db_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                                                        username, password);
        }catch(SQLException se) 
        {
            System.err.println(se.getMessage());
        }
    }
    
    public void create_table()
    {
        get_connection();
        try
        {
            Statement statement = db_connection.createStatement();
            String query = "CREATE TABLE candidates( "  +
                            "candidate_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                            "last_name   VARCHAR(32), " +
                            "first_name  VARCHAR(32), " +
                            "mid_initial CHAR(1), "     +
                            "birth_date  DATE, "        +
                            "religion    VARCHAR(32), " +
                            "degree      VARCHAR(32), " +
                            "university  VARCHAR(32), " +
                            "grad_date   DATE,"         +
                            "sex         VARCHAR(6),  " +
                            "img_path    VARCHAR(32), " +
                            "UNIQUE (last_name, first_name, mid_initial, birth_date))";
            statement.executeUpdate(query);
        } catch(SQLException se)
        {
            System.err.printf(ERROR_MSG_FMT, "create_table", se.getMessage());
        }
    }
    
    /**
     * Adds Candidate record to the database and updates the ID of
     * the parameter candidate
     * @param candidate
     * 
     * @return true/false based on successful addition
     */
    public Boolean add_candidate(Candidate candidate)
    {
        get_connection();
        try
        {
            Statement statement = db_connection.createStatement();
            String query = "INSERT INTO candidates "
                  + "(last_name, first_name, mid_initial, "
                  + "birth_date, religion, sex, degree, university, grad_date,"
                  + "img_path) "
                  + "VALUES ("
                    + "\"" + candidate.get_last_name()   + "\", "
                    + "\"" + candidate.get_first_name()  + "\", "
                    + "\"" + candidate.get_mid_initial() + "\", "
                    + "\"" + date_fmt.format(candidate.get_birth_date())+ "\", "
                    + "\"" + candidate.get_religion()    + "\", "
                    + "\"" + candidate.get_sex()         + "\", "
                    + "\"" + candidate.get_degree()      + "\", "
                    + "\"" + candidate.get_university()  + "\", "
                    + "\"" + date_fmt.format(candidate.get_grad_date())+ "\", "
                    + "\"" + candidate.get_image_path()  + "\")";

            System.out.println("Query: " + query);
            statement.executeUpdate(query);
            
            // Update the ID of the candidate passed to this function
            query = "SELECT MAX(candidate_id) AS LastID FROM candidates";
            ResultSet last_id = statement.executeQuery(query);
            
            if(last_id.next())
                candidate.set_candidate_id(last_id.getInt("LastID"));
            
            last_id.close();
            statement.close();
            return true;
        } catch(SQLException se)
        {
            System.err.printf(ERROR_MSG_FMT, "add_candidate", se.getMessage());
        }
        return false;
    }

    public Boolean update_candidate(int target_id, Candidate new_information)
    {
        get_connection();
        try
        {
            Statement statement = db_connection.createStatement();
            String query = "UPDATE candidates SET "
                + "last_name   = \"" + new_information.get_last_name()  + "\", "
                + "first_name  = \"" + new_information.get_first_name()+ "\", "
                + "mid_initial = \'" + new_information.get_mid_initial()+ "\', "
                + "birth_date  = \"" + date_fmt.format(new_information.get_birth_date()) + "\", "
                + "religion    = \"" + new_information.get_religion()   + "\", "
                + "degree      = \"" + new_information.get_degree()     + "\", "
                + "sex         = \"" + new_information.get_sex()        + "\", "
                + "university  = \"" + new_information.get_university() + "\", "
                + "grad_date   = \"" + date_fmt.format(new_information.get_grad_date())+ "\", "
                + "img_path    = \"" + new_information.get_image_path() + "\" "
                + "WHERE candidate_id = " + target_id;

            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch(SQLException se)
        {
            System.err.printf(ERROR_MSG_FMT, "update_candidate", se.getMessage());
        }
        return false;
    }
    
    public ArrayList<Candidate> query_candidates_by_name(String pattern)
    {
        ArrayList<Candidate> query_set = new ArrayList<>();
        
        get_connection();
        try 
        {
            Statement statement = db_connection.createStatement();
            String query = "SELECT * FROM candidates WHERE "
                            + "CONCAT(first_name, last_name, mid_initial) "
                            + "LIKE \"%" + pattern + "%\"";
            System.out.println("Query: " + query);
            
            ResultSet results = statement.executeQuery(query);
            while(results.next())
                query_set.add(construct_from_result(results));
            
            statement.close();
        } catch(SQLException se)
        {
            System.err.printf(ERROR_MSG_FMT, "query_candidate_by_name", se.getMessage());
        }
        
        return query_set;
    }
    
    public ArrayList<Candidate> query_all_candidates()
    {
        ArrayList<Candidate> query_set = new ArrayList<>();
        
        get_connection();
        try 
        {
            Statement statement = db_connection.createStatement();
            String query = "SELECT * FROM candidates";
            System.out.println("Query: " + query);
            
            ResultSet results = statement.executeQuery(query);
            while(results.next())
                query_set.add(construct_from_result(results));
            
            statement.close();
        } catch(SQLException se)
        {
            System.err.printf(ERROR_MSG_FMT, "query_all_candidates", se.getMessage());
        }
        
        return query_set;
    }
    
    public Candidate query_candidate_by_id(int candidate_id)
    {
        Candidate result = null;
        get_connection();
        try
        {
            Statement statement = db_connection.createStatement();
            String query = "SELECT * FROM candidates "
                         + "WHERE candidate_id = " + candidate_id;
            
            ResultSet results = statement.executeQuery(query);
            if(results.next())
                result = construct_from_result(results);
            
            statement.close();
        } catch(SQLException se)
        {
            System.err.printf(ERROR_MSG_FMT, "query_candidate_by_id", se.getMessage());
        }
        return result;
    }
    
    
    public Object[][] query_candidates_by_party_elecper(String party, int elecper) {
        get_connection();
        try{
            Statement statement = db_connection.createStatement();	
            //select * from candidates inner join campaigns on candidates.candidate_id = campaigns.candidateid where campaigns.elecperid = 10 and party = "PARTY10";
            String qry="SELECT * FROM CANDIDATES INNER JOIN CAMPAIGNS ON "
                    + "CANDIDATES.CANDIDATE_ID = CAMPAIGNS.CANDIDATEID WHERE "
                    + "CAMPAIGNS.ELECPERID = " + elecper + " and "
                    + "party = '" + party + "';";
            ResultSet rs = statement.executeQuery(qry);                        
            
            
            ArrayList<Object[]> al = new ArrayList<>();
            while(rs.next()) {
                String name = rs.getString("first_name");
                name += " " + rs.getString("mid_initial");
                name += " " + rs.getString("last_name");
                
                al.add(new String[] {rs.getString("img_path"), 
                name, 
                rs.getString("Party"),
                rs.getString("Position")});
            }
            
            Object[][] query = new Object[al.size()][];
            
            for(int i = 0; i < al.size(); i++) {
                query[i] = al.get(i);
            }
            rs.close();
            statement.close();
            
            return query;
	} catch(SQLException se)
        {
            System.err.printf(ERROR_MSG_FMT, "query_candidates_by_party_elecper", se.getMessage());
        }
        
        return null;
    }
    
    public Object[][] query_candidates_by_position_elecper(String position, int elecper) {
        get_connection();
        try{
            Statement statement = db_connection.createStatement();	
            String qry="SELECT * FROM CANDIDATES INNER JOIN CAMPAIGNS ON "
                    + "CANDIDATES.CANDIDATE_ID = CAMPAIGNS.CANDIDATEID WHERE "
                    + "CAMPAIGNS.ELECPERID = " + elecper + " and "
                    + "POSITION = '" + position + "';";
            ResultSet rs = statement.executeQuery(qry);                        
            
            
            ArrayList<Object[]> al = new ArrayList<>();
            while(rs.next()) {
                String name = rs.getString("first_name");
                name += " " + rs.getString("mid_initial");
                name += " " + rs.getString("last_name");
                
                al.add(new String[] {rs.getString("img_path"), 
                name, 
                rs.getString("Party"),
                rs.getString("Position")});
            }
            
            Object[][] query = new Object[al.size()][];
            
            for(int i = 0; i < al.size(); i++) {
                query[i] = al.get(i);
            }
            rs.close();
            statement.close();
            
            return query;
	} catch(SQLException se)
        {
            System.err.printf(ERROR_MSG_FMT, "query_candidates_by_position_elecper", se.getMessage());
        }
        
        return null;
    }
    
    public void delete_candidate(int candidate_id)
    {
        get_connection();
        try
        {
            Statement statement = db_connection.createStatement();
            String query = String.format(
                    "DELETE FROM candidates WHERE candidate_id = %d",
                    candidate_id
            );
            System.out.println("Query: " + query);
            statement.executeUpdate(query);
            statement.close();
        } catch(SQLException se)
        {
            System.err.printf(ERROR_MSG_FMT, "delete_candidate", se.getMessage());
        }
    }
    
    private Candidate construct_from_result(ResultSet results) throws SQLException
    {
        // TODO: some weird exception w/ MONTH even though date
        // format is correct in SQL database, doing this for now
        String bday_str = results.getString("birth_date");
        String grad_str = results.getString("grad_date");
        Date birth_date = null;
        Date grad_date  = null;
        try {
            birth_date = date_fmt.parse(bday_str);
            grad_date  = date_fmt.parse(grad_str);
        } catch (ParseException ex) {
            Logger.getLogger(CandidateDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        Candidate new_candidate = new Candidate(
                results.getString("last_name"),
                results.getString("first_name"),
                (char)results.getByte("mid_initial"),
                birth_date,
                results.getString("religion"),
                results.getString("sex"),
                results.getString("degree"),
                results.getString("university"),
                grad_date,
                results.getString("img_path"));
        new_candidate.set_candidate_id(results.getInt("candidate_id"));
        
        return new_candidate;
    }
}
