/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Candidate;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dandani-cs
 */
public class AdminViewCandidatesTableModel extends AbstractTableModel {
    private ArrayList<Candidate> candidate_list;
    private String [] column_names = {"Image", "Last Name", "First Name", "Edit", "Delete"};
    
    @Override
    public String getColumnName(int column) {
        return column_names[column]; 
    }
    
    public void setData(ArrayList<Candidate> new_candidate_list){
        this.candidate_list = new_candidate_list;
    }
    
    @Override
    public int getRowCount() {
        return candidate_list.size();
    }
    

    @Override
    public int getColumnCount() {
        return column_names.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Candidate candidate = candidate_list.get(row);
        
        switch(col){
            case 0: return candidate.get_candidate_id();
            case 1: return candidate.get_last_name();
            case 2: return candidate.get_first_name();
        }
        return null;
    }
    
}
