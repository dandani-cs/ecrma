/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Candidate;
import Model.CandidateDatabase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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
    
    public Boolean add_candidate(FormEvent evt)
    {
        return candidate_db.add_candidate(evt.getCandidate());
    }
    
    public void delete_candidate(Candidate to_delete)
    {
        candidate_db.delete_candidate(to_delete.get_candidate_id());
    }
    
    public Boolean update_candidate(int candidate_id, Candidate new_information)
    {
        return candidate_db.update_candidate(candidate_id, new_information);
    }
    
    public ArrayList<Candidate> query_all_candidates()
    {
        return candidate_db.query_all_candidates();
    }
    
    public Object[][] query_all_candidates_for_admin_view() {
        ArrayList<Candidate> candidate_list = candidate_db.query_all_candidates();
        Object[][] format = new Object[candidate_list.size()][5];
        JButton btn_edit, btn_delete;
        Border padding = new EmptyBorder(20, 20, 20, 20);
        
        for (int i = 0; i < candidate_list.size(); i++) {
            Candidate candidate = candidate_list.get(i);
            
            btn_edit = new JButton("Edit");
            btn_delete = new JButton("Delete");
            
            format[i] = new Object[] {
                candidate.get_image_path(),
                candidate.get_last_name(),
                candidate.get_first_name(),
                btn_edit,
                btn_delete,
                candidate.get_candidate_id()
            };
        }
        
        return format;
    }
    
    public ArrayList<Candidate> query_candidate_by_name(String pattern)
    {
        return candidate_db.query_candidates_by_name(pattern);
    }
    
    public Candidate query_candidate_by_id(int candidate_id)
    {
        return candidate_db.query_candidate_by_id(candidate_id);
    }
    
    public Object[][] query_candidates_by_party_elecper(String party, int elecper)
    {
        return candidate_db.query_candidates_by_party_elecper(party, elecper);
    }
    
    public Object[][] query_candidates_by_position_elecper(String position, int elecper)
    {
        return candidate_db.query_candidates_by_position_elecper(position, elecper);
    }
    
    public ArrayList<Object[]> query_candidates_by_name_elecper(String pattern, int elecper_id)
    {
        return candidate_db.query_candidates_by_name_elecper(pattern, elecper_id);
    }
}
