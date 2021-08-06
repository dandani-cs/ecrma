/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Candidate;
import java.awt.Component;
import java.awt.Image;
import static java.awt.Image.SCALE_SMOOTH;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

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
                case 0: 
//                  ImageIcon img = new ImageIcon(current_candidate.get_image_path());
                    try {
                        ImageIcon img = new ImageIcon(getClass().getResource(current_candidate.get_image_path()));
                        Image imgtmp = img.getImage();
                        imgtmp = imgtmp.getScaledInstance(120, 120, SCALE_SMOOTH);
                        img = new ImageIcon(imgtmp);
                        return img;
                        
                    } catch (Exception e) {
                        ImageIcon img = new ImageIcon("img/candidate2_128px.png");
                        Image imgtmp = img.getImage();
                        imgtmp = imgtmp.getScaledInstance(120, 120, SCALE_SMOOTH);
                        img = new ImageIcon(imgtmp);
                        return img;
                    }
                    
                case 1: return current_candidate.get_last_name();
                case 2: return current_candidate.get_first_name();
                case 3: return new JButton("Edit");
                case 4: return new JButton("Delete");
                case 5: return current_candidate.get_candidate_id();
            }
        }
        return null;
    }
    
    DefaultTableCellRenderer cellpad = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
                ob, boolean b1, boolean b2, int row, int column) {
                super.getTableCellRendererComponent(
                    table, ob, b1, b2, row, column);
                setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
                return this;
            }
        };
    
    public int getCandidateID(int row) {
        if (candidate_list.size() > row) {
            return candidate_list.get(row).get_candidate_id();
        }
        return 0;
    }
    
}
