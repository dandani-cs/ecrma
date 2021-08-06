/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.FormEvent;
import Controller.FormListener;
import Controller.MainController;
import Model.Campaigns;
import Model.CampaignsSQL;
import Model.Candidate;
import Model.ElecPer;
import Model.ElecPerSQL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Uriel Federez
 */
public class AddCampaignPanel extends JPanel {
    private static final Color primary_blue = new Color(33, 97, 140);
    private static final Font  font_primary = new Font("Open Sans", Font.BOLD, 14);
    MainController controller;
    
    private JComboBox<Candidate> candidates_combo;
    private JComboBox<ElecPer>   elecper_combo;
    private JComboBox<String>    party_combo;
    
    private JPanel     party_container;
    private JTextField party_text;
    private JTextField position_text;
    private JTextArea  platform_textarea;
    private JButton    save_btn;
    private JButton    cancel_btn;
    
    FormListener form_listener;
    
    
    
    public void set_controller(MainController mc)
    {
        controller = mc;
    }
    
    public AddCampaignPanel()
    {
        controller       = new MainController();
        candidates_combo = new JComboBox<>();
        elecper_combo    = new JComboBox<>();
        party_combo      = new JComboBox<>();
        
        HeaderPanel header = new HeaderPanel("Add Campaign", 
                                             "Specify the candidate and election period for this campaign. "
                                           + "Input the candidate's party, position and platform.");
        
        // Get list to populate candidates combobox
        ArrayList<Candidate> candidates     = controller.candidate_controller.query_all_candidates();
        ArrayList<ElecPer> election_periods = new ArrayList<>();
        ArrayList<Campaigns> campaigns      = new ArrayList<>();
        ArrayList<String> unique_parties    = new ArrayList<>();
        
        Object[][] elecper_objs = ElecPerSQL.getTable();
        for(int i = 0; i < elecper_objs.length; i++)
        {
            // Don't show archived election periods as option
            if(((Boolean) elecper_objs[i][4]) == true)
                continue;
            
            LocalDate start_date = LocalDate.parse((String)elecper_objs[i][2],
                                                    DateTimeFormatter.ofPattern("LLLL/dd/yyyy"));
            LocalDate end_date   = LocalDate.parse((String)elecper_objs[i][3],
                                                    DateTimeFormatter.ofPattern("LLLL/dd/yyyy"));
            
            ElecPer eper = new ElecPer((String)    elecper_objs[i][1],
                                       start_date,
                                       end_date,
                                       (Boolean)   elecper_objs[i][4]);
            eper.setElecPerId((Integer) elecper_objs[i][0]);
            election_periods.add(eper);
        }

        for(Candidate c : candidates)
        {
            candidates_combo.addItem(c);
            Object[][] campaign_objs = CampaignsSQL.getRows(c.get_candidate_id());
            
            if(campaign_objs.length != 0)
            {
                for(int i = 0; i < campaign_objs.length; i++)
                {
                    String party = (String) campaign_objs[i][1];
                    if(!unique_parties.contains(party))
                    {
                        unique_parties.add(party);
                        party_combo.addItem(party);
                    }
                }
            }
        }
        
        
        party_text      = new JTextField();
        party_container = new JPanel();
        party_container.setLayout(new BorderLayout());
        
        party_combo.addItem("Other");
        party_combo.setBackground(Color.white);
        
        party_container.add(party_combo, BorderLayout.CENTER);
        
        if(!unique_parties.isEmpty())
        {
            party_combo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(party_combo.getSelectedItem().equals("Other"))
                    {
                        swap_with_party_text();
                    }
                }
            });
        } else
            swap_with_party_text();
        
        for(ElecPer e : election_periods)
            elecper_combo.addItem(e);
        
        
        cancel_btn = new JButton("Cancel");
        save_btn   = new JButton("Save");
        
        save_btn.setBackground(primary_blue);
        save_btn.setForeground(Color.white);
        
        cancel_btn.setBackground(primary_blue);
        cancel_btn.setForeground(Color.white);
        
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        button_panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        button_panel.add(cancel_btn);
        button_panel.add(save_btn);
        
        position_text = new JTextField();
        platform_textarea = new JTextArea(5, 30);
        
        JPanel fields_container = new JPanel();
        JPanel fields_content   = new JPanel();
        fields_content.setLayout(new GridBagLayout());
        
        GridBagConstraints fields_gbc = new GridBagConstraints();
        fields_gbc.gridheight = 1;
        fields_gbc.gridwidth  = 1;
        fields_gbc.weightx    = 0.5;
        fields_gbc.weighty    = 0.0;
        fields_gbc.insets     = new Insets(5, 5, 5, 5);
        fields_gbc.ipady      = 10;
        fields_gbc.fill       = GridBagConstraints.NONE;
        fields_gbc.anchor     = GridBagConstraints.FIRST_LINE_START;
        
        JLabel label_01 = new JLabel("Candidate: ");
        label_01.setFont(font_primary);
        fields_gbc.weightx = 0.1;
        fields_gbc.gridx   = 0;
        fields_gbc.gridy   = 0;
        fields_content.add(label_01, fields_gbc);
        
        JLabel label_02 = new JLabel("Election Period: ");
        label_02.setFont(font_primary);
        fields_gbc.gridx = 0;
        fields_gbc.gridy = 1;
        fields_content.add(label_02, fields_gbc);
        
        JLabel label_03 = new JLabel("Party: ");
        label_03.setFont(font_primary);
        fields_gbc.gridx = 0;
        fields_gbc.gridy = 2;
        fields_content.add(label_03, fields_gbc);
        
        JLabel label_04 = new JLabel("Position: ");
        label_04.setFont(font_primary);
        fields_gbc.gridx = 0;
        fields_gbc.gridy = 3;
        fields_content.add(label_04, fields_gbc);
        
        JLabel label_05 = new JLabel("Platform: ");
        label_05.setFont(font_primary);
        fields_gbc.gridx = 0;
        fields_gbc.gridy = 4;
        fields_content.add(label_05, fields_gbc);
        
        fields_gbc.weightx    = 0.9;
        fields_gbc.fill       = GridBagConstraints.HORIZONTAL;
        fields_gbc.anchor     = GridBagConstraints.FIRST_LINE_START;
        fields_gbc.gridx = 1;
        fields_gbc.gridy = 0;
        candidates_combo.setBackground(Color.white);
        fields_content.add(candidates_combo, fields_gbc);
        
        fields_gbc.gridx = 1;
        fields_gbc.gridy = 1;
        elecper_combo.setBackground(Color.white);
        fields_content.add(elecper_combo, fields_gbc);
        
        fields_gbc.gridx = 1;
        fields_gbc.gridy = 2;
        fields_content.add(party_container, fields_gbc);
        
        fields_gbc.gridx = 1;
        fields_gbc.gridy = 3;
        fields_content.add(position_text, fields_gbc);
        
        fields_gbc.gridx = 1;
        fields_gbc.gridy = 4;
        platform_textarea.setBorder(BorderFactory.createLineBorder(Color.gray));
        fields_content.add(platform_textarea, fields_gbc);
        
        fields_gbc.gridx     = 0;
        fields_gbc.gridy     = 5;
        fields_gbc.insets    = new Insets(0, 0, 0, 0);
        fields_gbc.gridwidth = 2;
        fields_content.add(button_panel, fields_gbc);
        
        float side_margin = 0.3f;
        
        fields_container.setLayout(new GridBagLayout());
        GridBagConstraints fields_main_gbc = new GridBagConstraints();
        fields_main_gbc.weightx = side_margin / 2.0;
        fields_main_gbc.weighty = 1.0;
        fields_main_gbc.gridwidth = 1;
        fields_main_gbc.gridheight = 2;
        fields_main_gbc.gridx = 0;
        fields_main_gbc.gridy = 0;
        fields_main_gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        fields_main_gbc.fill  = GridBagConstraints.HORIZONTAL;
        
        JPanel bp1 = new JPanel();
        bp1.setBackground(Color.white);
        fields_container.add(bp1, fields_main_gbc);
        
        fields_main_gbc.weightx = 1 - 2*(side_margin);
        fields_main_gbc.gridx = 1;
        fields_main_gbc.gridy = 0;
        fields_main_gbc.gridwidth = 1;
        fields_main_gbc.gridheight = 1;
        fields_container.add(fields_content, fields_main_gbc);
        
        JPanel bp2 = new JPanel();
        bp2.setBackground(Color.white);
        fields_container.add(bp2, fields_main_gbc);
        fields_main_gbc.weightx = side_margin / 2.0;
        fields_main_gbc.gridx = 2;
        fields_main_gbc.gridy = 0;
        fields_container.add(bp2, fields_main_gbc);
        
        button_panel.setBackground(Color.white);
        fields_content.setBackground(Color.white);
        header.setBackground(Color.white);
        fields_container.setBackground(Color.white);
        setBackground(Color.white);
        
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(fields_container, BorderLayout.CENTER);
        
        save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String error = validate_input();
                System.out.println("Error: " + error);
                System.out.println("Error size: " + error.length());
                if(!error.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, error, 
                                                  "Add Campaign Failed!", 
                                                  JOptionPane.ERROR_MESSAGE); 
                }
                else
                {
                    System.out.println("NO ERROR");
                    Candidate candidate = (Candidate)candidates_combo.getSelectedItem(); 
                    ElecPer   elecper   = (ElecPer)  elecper_combo.getSelectedItem();

                    int candidate_id = candidate.get_candidate_id();
                    int elecper_id   = elecper.getElecPerId();

                    String party     = party_combo.isVisible() ? 
                                            (String) party_combo.getSelectedItem() : 
                                            party_text.getText();

                    String position  = position_text.getText();
                    String platform  = platform_textarea.getText();

                    Campaigns campaign = new Campaigns(candidate_id, elecper_id, party,
                                                       position, platform);

                    FormEvent fe = new FormEvent(this);
                    fe.setCampaign(campaign);
                    fe.setCandidate((Candidate)candidates_combo.getSelectedItem());
                    fe.setElection_period(elecper);
                    
                    controller.campaign_controller.addCampaign(fe);

                    if(form_listener != null)
                        form_listener.formEventOccurred(fe);
                }
            }
        });
        
    }
    
    private String validate_input()
    {
        String error_message = "";
        
        boolean is_valid_party = party_combo.isVisible() ||
                                 (!party_combo.isVisible() && !party_text.getText().isBlank());
        boolean is_valid_position = !position_text.getText().isBlank();
        boolean is_valid_platform = !platform_textarea.getText().isEmpty();
     
        if(!is_valid_party)
            error_message += "Invalid input for party!\n";
        if(!is_valid_position)
            error_message += "Invalid position entered!\n";
        if(!is_valid_platform)
            error_message += "Invalid platform entered!\n";
        
        return error_message;
    }
    
    private void swap_with_party_text()
    {
        party_combo.setVisible(false);
        party_container.add(party_text);
    }

    public void setFormListener(FormListener formListener) {
        this.form_listener = form_listener;
    }
}