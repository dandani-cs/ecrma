/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Candidate;
import Model.CandidateDatabase;
import java.util.ArrayList;

/**
 *
 * @author Uriel Federez
 */
public class CandidateController {
    CandidateDatabase candidate_db;
    
    public CandidateController()
    {
        candidate_db = new CandidateDatabase();
    }
    
    public void add_candidate(CandidateAddFormEvent evt)
    {
        candidate_db.add_candidate(evt.get_candidate());
    }
    
    public void delete_candidate(Candidate to_delete)
    {
        candidate_db.delete_candidate(to_delete.get_candidate_id());
    }
    
    public void update_candidate(int candidate_id, Candidate new_information)
    {
        candidate_db.update_candidate(candidate_id, new_information);
    }
    
    public ArrayList<Candidate> query_all_candidates()
    {
        return candidate_db.query_all_candidates();
    }
    
    public ArrayList<Candidate> query_candidate_by_name(String pattern)
    {
        return candidate_db.query_candidates_by_name(pattern);
    }
    
    public Candidate query_candidate_by_id(int candidate_id)
    {
        return candidate_db.query_candidate_by_id(candidate_id);
    }
}
