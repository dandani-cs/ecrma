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
import Model.ElecPerSQL;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Uriel Federez
 */
public class CandidateSearchPanel extends javax.swing.JPanel {

    private FormListener form_listener;
    private String current_filter_card = "name_filter";
    
    UserMenu menu;
    Frame_Login login;
    
    private Color hoverMENU = new Color(33,82,117);
    private Color byeMENU   = new Color(33,97,140);
    
    private JPanel name_filters     = new JPanel();
    private JPanel party_filters    = new JPanel();
    private JPanel position_filters = new JPanel();
    private Object[][] data         = null;
    private Object[][] elec_periods = null;
    private Object[] candidate_ids  = null;
    private Object[] parties        = null;
    private Object[] positions      = null;
    
    private MainController controller = new MainController();
    
    private CardLayout filter_container_cards; 
    private JTable search_results_table;
    // TODO: remove
    private JTable search_by_party_table;
    private JTable search_by_position_table;
    /**
     * Creates new form CandidateSearchPanel
     */
    public CandidateSearchPanel() {
        initComponents();
        
        HeaderPanel header_panel = new HeaderPanel("Search Candidates WTF", "Search by blabla");
        header_container.setLayout(new BorderLayout());
        header_container.add(header_panel, BorderLayout.CENTER);
        //header_container.setBorder(BorderFactory.createLineBorder(Color.black));
        
        // Populate election periods
        elec_periods = ElecPerSQL.getTable();
        eper_combo_box.removeAllItems();
        for(int i = 0; i < elec_periods.length; i++)
            eper_combo_box.addItem((String) elec_periods[i][1]);
       
        update_party_position_from_eper();
        eper_combo_box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update_table_model();
                update_party_position_from_eper();
            }
        });
        
        // Populate initial table
        search_results_table = new JTable() {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        
        update_table_model();

        search_results_table.getColumnModel().getColumn(0).setMaxWidth(120);
        search_results_table.getColumnModel().getColumn(0).setMinWidth(120);

        search_results_table.setRowHeight(120);

        search_results_table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN, 24));
        search_results_table.setFont(new Font("CALIBRI", Font.PLAIN, 18));

        JScrollPane scrollpane = new JScrollPane(search_results_table);

        search_results_table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = search_results_table.rowAtPoint(evt.getPoint());
                    int col = search_results_table.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) 
                    {
                        FormEvent view_details_evt = new FormEvent(this);
                        view_details_evt.setCandidate(controller
                                                        .candidate_controller
                                                        .query_candidate_by_id((int)data[row][4]));
                        
                        if(form_listener != null)
                            form_listener.formEventOccurred(view_details_evt);
                        else
                            System.err.println("[ERROR] Null form listener on CandidateSearchPanel");
                    }
            }
        });
        table_container.add(scrollpane, BorderLayout.CENTER);
        add(header_container, BorderLayout.NORTH);
        
        filter_container_cards = (CardLayout) candidate_filters.getLayout();
        setCard("name_filter");
        set_header("Filter Candidate by Name",
                   "Input the name of the candidate in the text field below along with the corresponding election period");
    }
    
    public void set_form_listener(FormListener fl) 
    {
        this.form_listener = fl;
    }
    
    private void set_header(String title, String subtitle)
    {
        header_container.removeAll();
        header_container.add(new HeaderPanel(title, subtitle), BorderLayout.NORTH);
    }


    private void update_party_position_from_eper()
    {
        int elecper_id  = (int) elec_periods[eper_combo_box.getSelectedIndex()][0];
        parties   = CampaignsSQL.getPartiesOfElecper(elecper_id);
        positions = CampaignsSQL.getPositionsOfElecper(elecper_id);

        position_combo_box.setModel(new DefaultComboBoxModel(positions));
        party_combo_box.setModel(new DefaultComboBoxModel(parties));
    }
    
    public void setCard(String str){
        if(str.equals("all_filter"))
        {
            filter_container_cards.show(candidate_filters, "name_filter");
            name_text_field.setEnabled(false);
        }
        else
        {
            filter_container_cards.show(candidate_filters, str);
            name_text_field.setEnabled(true);
        }
        current_filter_card = str;
        update_table_model();
        
        if(str.equals("party_filter")) 
        {
            set_header("Filter Candidate by Party",
                        "Select the party of the candidate in dropdown below along with the corresponding election period");
            
        } else if(current_filter_card.equals("position_filter"))
        {
            set_header("Filter Candidate by Position",
                        "Select the party of the candidate in dropdown below along with the corresponding election period");

        } else if(str.equals("name_filter"))
        {
            set_header("Filter Candidate by Name",
                       "Input the name of the candidate in the text field below along with the corresponding election period");
        } else
        {
            set_header("View All Candidates",
                       "All of the candidates who participated in the shown election period are listed below");
        }
    }
    
    
    private void update_table_model()
    {
        String[] colNames = {"", "Name", "Party", "Position"};
        int elecper_id    = (int) elec_periods[eper_combo_box.getSelectedIndex()][0];
        
        
        // Determine selected search method
        if(current_filter_card.equals("party_filter")) 
        {
            data = controller.candidate_controller
                        .query_candidates_by_party_elecper(
                                (String) party_combo_box.getSelectedItem(), 
                                elecper_id);
            
        } else if(current_filter_card.equals("position_filter"))
        {
            data = controller.candidate_controller
                        .query_candidates_by_position_elecper(
                                (String) position_combo_box.getSelectedItem(), 
                                elecper_id);
            
            for(int i = 0; i < data[0].length; i++)
                System.out.println(data[0][i]);

        } else
        {
            String query_pattern = name_text_field.getText();
            ArrayList<Object[]> search_results = controller.candidate_controller
                                                        .query_candidates_by_name_elecper(query_pattern, elecper_id);

            data = new Object[search_results.size()][5];

            for(int i = 0; i < search_results.size(); i++)
            {
                Candidate curr_candidate = (Candidate) search_results.get(i)[0];
                Campaigns curr_campaign  = (Campaigns) search_results.get(i)[1];
                Object[]  curr_row       = data[i];

                ImageIcon img_icon = new ImageIcon(curr_candidate.get_image_path());
                Image resized_img  = img_icon.getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
                ImageIcon img_view = new ImageIcon(resized_img);

                curr_row[0] = img_view;
                curr_row[1] = String.format("%s %c %s", 
                                            curr_candidate.get_first_name(),
                                            curr_candidate.get_mid_initial(),
                                            curr_candidate.get_last_name());
                System.out.println(curr_row[1]);
                curr_row[2] = curr_campaign.getParty();
                curr_row[3] = curr_campaign.getPosition();
                curr_row[4] = curr_candidate.get_candidate_id();
            }
            
            System.out.println("filter by name");
        }
        
        
        DefaultTableModel model = new DefaultTableModel(data, colNames);
        
        search_results_table.setModel(model);
        search_results_table.getColumnModel()
                .getColumn(0)
                .setCellRenderer(search_results_table.getDefaultRenderer(ImageIcon.class));
        search_results_table.getColumnModel().getColumn(0).setMaxWidth(120);
        search_results_table.getColumnModel().getColumn(0).setMinWidth(120);
        search_results_table.setRowHeight(120);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        header_container = new javax.swing.JPanel();
        results_container = new javax.swing.JPanel();
        table_container = new javax.swing.JPanel();
        filter_container = new javax.swing.JPanel();
        candidate_filters = new javax.swing.JPanel();
        name_filter = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        name_text_field = new javax.swing.JTextField();
        party_filter = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        party_combo_box = new javax.swing.JComboBox<>();
        position_filter = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        position_combo_box = new javax.swing.JComboBox<>();
        no_filter = new javax.swing.JPanel();
        elecper_filter_container = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        eper_combo_box = new javax.swing.JComboBox<>();

        setLayout(new java.awt.BorderLayout());

        header_container.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout header_containerLayout = new javax.swing.GroupLayout(header_container);
        header_container.setLayout(header_containerLayout);
        header_containerLayout.setHorizontalGroup(
            header_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 777, Short.MAX_VALUE)
        );
        header_containerLayout.setVerticalGroup(
            header_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1139, Short.MAX_VALUE)
        );

        add(header_container, java.awt.BorderLayout.NORTH);

        results_container.setBackground(new java.awt.Color(255, 255, 255));
        results_container.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 50, 25, 50));
        results_container.setLayout(new java.awt.BorderLayout());

        table_container.setBackground(new java.awt.Color(255, 255, 255));
        table_container.setLayout(new java.awt.BorderLayout());
        results_container.add(table_container, java.awt.BorderLayout.CENTER);

        filter_container.setBackground(new java.awt.Color(255, 255, 255));
        filter_container.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 10, 0));
        filter_container.setLayout(new java.awt.GridLayout(1, 2, 15, 15));

        candidate_filters.setBackground(new java.awt.Color(255, 255, 255));
        candidate_filters.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        candidate_filters.setLayout(new java.awt.CardLayout());

        name_filter.setBackground(new java.awt.Color(255, 255, 255));
        name_filter.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Search for a candidate's name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.25;
        name_filter.add(jLabel1, gridBagConstraints);

        name_text_field.setColumns(30);
        name_text_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_text_fieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 0.75;
        name_filter.add(name_text_field, gridBagConstraints);

        candidate_filters.add(name_filter, "name_filter");

        party_filter.setBackground(new java.awt.Color(255, 255, 255));
        party_filter.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Select the party of the candidate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.25;
        party_filter.add(jLabel3, gridBagConstraints);

        party_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        party_combo_box.setPreferredSize(new java.awt.Dimension(158, 20));
        party_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                party_combo_boxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.75;
        party_filter.add(party_combo_box, gridBagConstraints);

        candidate_filters.add(party_filter, "party_filter");

        position_filter.setBackground(new java.awt.Color(255, 255, 255));
        position_filter.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Select the position of the candidate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.25;
        position_filter.add(jLabel4, gridBagConstraints);

        position_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        position_combo_box.setPreferredSize(new java.awt.Dimension(158, 20));
        position_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                position_combo_boxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.75;
        position_filter.add(position_combo_box, gridBagConstraints);

        candidate_filters.add(position_filter, "position_filter");

        no_filter.setBackground(new java.awt.Color(255, 255, 255));
        no_filter.setLayout(new java.awt.GridBagLayout());
        candidate_filters.add(no_filter, "no_filter");

        filter_container.add(candidate_filters);

        elecper_filter_container.setBackground(new java.awt.Color(255, 255, 255));
        elecper_filter_container.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Election Period:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.25;
        elecper_filter_container.add(jLabel5, gridBagConstraints);

        eper_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        eper_combo_box.setMinimumSize(new java.awt.Dimension(256, 20));
        eper_combo_box.setPreferredSize(new java.awt.Dimension(256, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.75;
        elecper_filter_container.add(eper_combo_box, gridBagConstraints);

        filter_container.add(elecper_filter_container);

        results_container.add(filter_container, java.awt.BorderLayout.NORTH);

        add(results_container, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void name_text_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_text_fieldActionPerformed
        // TODO add your handling code here:
        update_table_model();
    }//GEN-LAST:event_name_text_fieldActionPerformed

    private void party_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_party_combo_boxActionPerformed
        // TODO add your handling code here:
        update_table_model();
    }//GEN-LAST:event_party_combo_boxActionPerformed

    private void position_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_position_combo_boxActionPerformed
        // TODO add your handling code here:
        update_table_model();
    }//GEN-LAST:event_position_combo_boxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel candidate_filters;
    private javax.swing.JPanel elecper_filter_container;
    private javax.swing.JComboBox<String> eper_combo_box;
    private javax.swing.JPanel filter_container;
    private javax.swing.JPanel header_container;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel name_filter;
    private javax.swing.JTextField name_text_field;
    private javax.swing.JPanel no_filter;
    private javax.swing.JComboBox<String> party_combo_box;
    private javax.swing.JPanel party_filter;
    private javax.swing.JComboBox<String> position_combo_box;
    private javax.swing.JPanel position_filter;
    private javax.swing.JPanel results_container;
    private javax.swing.JPanel table_container;
    // End of variables declaration//GEN-END:variables
}
