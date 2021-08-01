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
                case 0: return current_candidate.get_image_path();
                case 1: return current_candidate.get_last_name();
                case 2: return current_candidate.get_first_name();
                case 3: return new JButton("Edit");
                case 4: return new JButton("Delete");
                case 5: return current_candidate.get_candidate_id();
            }
        }
        return null;
    }
    
}
