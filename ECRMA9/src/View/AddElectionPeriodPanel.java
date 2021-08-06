/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ElecPerController;
import Controller.FormEvent;
import Controller.FormListener;
import Controller.MainController;
import Model.ElecPer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;

/**
 *
 * @author dandani-cs
 */
public class AddElectionPeriodPanel extends JPanel {
    private JLabel lbl_name,
            lbl_start_date,
            lbl_final_date,
            lbl_header,
            lbl_description,
            lbl_validation;
    private JTextField txt_name;
    private JSpinner spinner_start_date, 
            spinner_final_date;
    private JSpinner.DateEditor editor_start_date,
            editor_final_date;
    private JButton btn_save, btn_cancel;
    
    private JPanel panel_content;
    
    private HeaderPanel panel_header;
    
    private static final Color primary_blue = new Color(33, 97, 140);
    private static final Color primary_bg   = Color.white;
    
    Date today = new Date();
    Date start_date, final_date;
    
    private FormListener formListener;
    
    public AddElectionPeriodPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // create a line border with the specified color and width
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        
        panel_header = new HeaderPanel("ADD ELECTION PERIOD", "Specify the name, starting date, and final date of the new election period. ");
        
        panel_content = new JPanel(new GridLayout(6, 2));
        panel_content.setOpaque(false);
        panel_content.setBorder(BorderFactory.createEmptyBorder(0, 50, 890, 50));
        
        
        //for validation
        start_date = new Date();
        final_date = new Date();
        
        lbl_validation = new JLabel("");
        lbl_validation.setForeground(Color.red);
        
        
        lbl_name = new JLabel("Election Period Name: ");
        lbl_name.setBorder(border);
        txt_name = new JTextField(20);
        txt_name.setBorder(border);
        
        lbl_start_date = new JLabel("Start date: ");
        lbl_start_date.setBorder(border);
        spinner_start_date = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        editor_start_date = new JSpinner.DateEditor(spinner_start_date, "MM/dd/yy");
        spinner_start_date.setEditor(editor_start_date);
        spinner_start_date.setBorder(border);
        
        lbl_final_date = new JLabel("Final date: ");
        lbl_final_date.setBorder(border);
        spinner_final_date = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        editor_final_date = new JSpinner.DateEditor(spinner_final_date, "MM/dd/yy");
        spinner_final_date.setEditor(editor_final_date);
        spinner_final_date.setBorder(border);
        
        btn_save = new JButton("SAVE");
        btn_cancel = new JButton("CANCEL");
        
//        btn_save.setBackground(primary_blue);
//        btn_save.setForeground(primary_bg);
//        
//        btn_cancel.setBackground(primary_blue);
//        btn_cancel.setForeground(primary_bg);
        
        panel_content.add(lbl_validation);
        panel_content.add(new Component() {});
        panel_content.add(lbl_name);
        panel_content.add(txt_name);
        panel_content.add(lbl_start_date);
        panel_content.add(spinner_start_date);
        panel_content.add(lbl_final_date);
        panel_content.add(spinner_final_date);
        panel_content.add(btn_cancel);
        panel_content.add(btn_save);
        
//        panel_options = new JPanel(new FlowLayout(FlowLayout.TRAILING));
//        panel_options.setOpaque(false);
        
//        btn_save = new JButton("SAVE");
//        btn_cancel = new JButton("CANCEL");
        
//        panel_options.add(btn_cancel);
//        panel_options.add(btn_save);
        
        add(panel_header, BorderLayout.NORTH);
        add(panel_content, BorderLayout.CENTER);
//        add(panel_options, BorderLayout.SOUTH);
        
        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start_date = (Date) spinner_start_date.getValue();
                final_date = (Date) spinner_final_date.getValue();
                
                ElecPer ep = new ElecPer(
                        txt_name.getText(), 
                        start_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
                        final_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        false
                );
                
                FormEvent fe = new FormEvent(e,ep);
                formListener.formEventOccurred(fe);
            }
        });
    }

    
    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }
    
    public boolean validate_input() {
        Boolean valid = true;
        String error_str = "";
        
        if(txt_name.getText().trim().isEmpty() == true) {
            error_str += "Name cannot be empty! ";
            valid = false;
        }
        if(start_date.compareTo(final_date) >= 1) {
            error_str += "Start date cannot be later than Final date!";
            valid = false;
        }
        lbl_validation.setText(error_str);
        return valid;
    }

}
