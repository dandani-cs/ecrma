/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Candidate;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dandani-cs
 */
public class AdminViewCandidatesTableModel extends AbstractTableModel {
    private ArrayList<Candidate> candidate_list;
    private String [] column_names = {"Image", "Last Name", "First Name", "Edit", "Delete", "Candidate ID"};
    
    @Override
    public String getColumnName(int column) {
        return column_names[column]; 
    }
    
    public void setData(ArrayList<Candidate> new_candidate_list){
        this.candidate_list = new_candidate_list;
    }
    
    @Override
    public int getRowCount() {
        return 5;
    }
    
    @Override
    public int getColumnCount() {
        return column_names.length;
    }
    
    @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 3 || columnIndex == 4)
                return JButton.class;
            return Object.class;
        }

    @Override
    public Object getValueAt(int row, int col) {
        if (candidate_list.size() > row) {
            Candidate current_candidate = candidate_list.get(row);
            switch (col) {
                case 1: return current_candidate.get_image_path();
                case 2: return current_candidate.get_last_name();
                case 3: return current_candidate.get_first_name();
                case 4: return new JButton("Edit");
                case 5: return new JButton("Delete");
                case 6: return current_candidate.get_candidate_id();
            }
        }
        return null;
    }
    
    public int getCandidateID(int row) {
        if (candidate_list.size() > row) {
            return candidate_list.get(row).get_candidate_id();
        }
        return 0;
    }
    
}
