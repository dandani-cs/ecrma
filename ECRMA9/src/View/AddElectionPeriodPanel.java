/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import ecrma9.ECRMA9;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author dandani-cs
 */
public class AddElectionPeriodPanel extends JPanel {
    private JLabel lbl_name,
            lbl_start_date,
            lbl_final_date,
            lbl_header,
            lbl_description;
    private JTextField txt_name;
    private JSpinner spinner_start_date, 
            spinner_final_date;
    private JSpinner.DateEditor editor_start_date,
            editor_final_date;
    private JButton btn_save, btn_cancel;
    
    private JPanel panel_header, panel_content, panel_options;
    
    Date today = new Date();
    
    public AddElectionPeriodPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        panel_header = new JPanel(new GridLayout(2, 1, 50, 30));
        panel_header.setOpaque(false);
        panel_header.setBorder(BorderFactory.createEmptyBorder(50, 50, 75, 50));
        
        lbl_header = new JLabel("ADD ELECTION PERIOD");
        lbl_header.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_header.setForeground(StaticResources.getMainColor());
        
        lbl_description = new JLabel("Set the name, starting date, and final date of the election period.");
        lbl_description.setForeground(StaticResources.getMainColor());
        
        panel_header.add(lbl_header);
        panel_header.add(lbl_description);
        
        panel_content = new JPanel(new GridLayout(3, 2));
        panel_content.setOpaque(false);
        panel_content.setBorder(BorderFactory.createEmptyBorder(20, 50, 690, 50));
        
        lbl_name = new JLabel("Election Period Name: ");
        txt_name = new JTextField(20);
        
        lbl_start_date = new JLabel("Start date: ");
        spinner_start_date = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        editor_start_date = new JSpinner.DateEditor(spinner_start_date, "MM/dd/yy");
        spinner_start_date.setEditor(editor_start_date);
        
        lbl_final_date = new JLabel("Final date: ");
        spinner_final_date = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        editor_final_date = new JSpinner.DateEditor(spinner_final_date, "MM/dd/yy");
        spinner_final_date.setEditor(editor_final_date);
        
        panel_content.add(lbl_name);
        panel_content.add(txt_name);
        panel_content.add(lbl_start_date);
        panel_content.add(spinner_start_date);
        panel_content.add(lbl_final_date);
        panel_content.add(spinner_final_date);
        
        panel_options = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        panel_options.setOpaque(false);
        
        btn_save = new JButton("SAVE");
        btn_cancel = new JButton("CANCEL");
        
        panel_options.add(btn_cancel);
        panel_options.add(btn_save);
        
        add(panel_header, BorderLayout.NORTH);
        add(panel_content, BorderLayout.CENTER);
        add(panel_options, BorderLayout.SOUTH);
    }
}
