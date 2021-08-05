/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CampaignController;
import Controller.MainController;
import Model.Campaigns;
import Model.CampaignsSQL;
import Model.Candidate;
import Model.ElecPer;
import Model.ElecPerSQL;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rheeeiiii
 */
public class UserMainContentArea extends javax.swing.JFrame {
    
    public static void main(String[] args)
    {
        new UserMainContentArea().setVisible(true);
    }
    
    private String current_filter_card = "name_filter";
    
    UserMenu menu;
    Frame_Login login;
    
    UserCard myPanel = new UserCard();

    private Color hoverMENU = new Color(33,82,117);
    private Color byeMENU   = new Color(33,97,140);
    
    private JPanel name_filters     = new JPanel();
    private JPanel party_filters    = new JPanel();
    private JPanel position_filters = new JPanel();
    private Object[][] elec_periods = null;
    private Object[] parties        = null;
    private Object[] positions      = null;
    
    private MainController controller = new MainController();
    
    private CardLayout filter_container_cards; 
    private JTable search_results_table;
    // TODO: remove
    private JTable search_by_party_table;
    private JTable search_by_position_table;
    
    
    /**
     * Creates new form UserCard2
     */
    public UserMainContentArea() {
        initComponents(); 
        this.setSize(new Dimension(1920,1080));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Election Candidates Record Management");
        
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
        
        search_results_table.getColumnModel().getColumn(0).setCellRenderer(search_results_table.getDefaultRenderer(ImageIcon.class));

        search_results_table.getColumnModel().getColumn(0).setMaxWidth(120);
        search_results_table.getColumnModel().getColumn(0).setMinWidth(120);

        search_results_table.setRowHeight(120);

        search_results_table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN, 24));
        search_results_table.setFont(new Font("CALIBRI", Font.PLAIN, 18));

        JScrollPane scrollpane = new JScrollPane(search_results_table);

        search_results_table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                    int row = search_results_table.rowAtPoint(evt.getPoint());
//                    int col = search_results_table.columnAtPoint(evt.getPoint());
//                    if (row >= 0 && col >= 0) {
//                         cardViewAll.setVisible(false);
//                         remove(cardViewAll);
//
//                        cardDetails.setVisible(true);
//                        cardDetails.repaint();
//                        cardDetails.revalidate();
//                        setSize(new Dimension(1720,1080));
            }
        });
        table_container.add(scrollpane, BorderLayout.CENTER);
        MainPanel.add(header_container, BorderLayout.NORTH);
        
        filter_container_cards = (CardLayout) candidate_filters.getLayout();
        setCard("name_filter");
        set_header("Filter Candidate by Name",
                   "Input the name of the candidate in the text field below along with the corresponding election period");
        
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
        filter_container_cards.show(candidate_filters, str);
        current_filter_card = str;
    }
    
    
    private void update_table_model()
    {
        Object[][] data   = null;
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

        } else
        {
            String query_pattern = name_text_field.getText();
            ArrayList<Object[]> search_results = controller.candidate_controller
                                                        .query_candidates_by_name_elecper(query_pattern, elecper_id);

            data = new Object[search_results.size()][4];

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
            }
            
            System.out.println("filter by name");
        }
        
        DefaultTableModel model = new DefaultTableModel(data, colNames);
        
        search_results_table.setModel(model);
        search_results_table.getColumnModel().getColumn(0).setCellRenderer(search_results_table.getDefaultRenderer(ImageIcon.class));
        search_results_table.getColumnModel().getColumn(0).setMaxWidth(120);
        search_results_table.getColumnModel().getColumn(0).setMinWidth(120);
        search_results_table.setRowHeight(120);
    }
    
    public class UserCard extends JPanel {
        Color  bgColor;
        Insets westInsets;
        Center center;

        public UserCard() {
            bgColor = new Color(255, 255, 255);
            center  = new Center();

            this.setLayout(new BorderLayout());
            this.add(center, BorderLayout.CENTER);
            
            center.setBackground(bgColor);

            pack();
            setSize(1620, 1000);
            this.setVisible(true);
        }

        //VIEW CANDIDATE?
        class Center extends JPanel 
        {
            JTable table;

            Center() {
                this.setLayout(new BorderLayout());
                this.setBorder(new EmptyBorder(243, 60, 60, 60));
                this.setOpaque(true);
                this.setBackground(new Color(33, 97, 140));

               
            }

        }
    }
    
    private void set_header(String title, String subtitle)
    {
        header_container.removeAll();
        header_container.add(new HeaderPanel(title, subtitle), BorderLayout.NORTH);
    }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel13 = new javax.swing.JPanel();
        vCandidate = new javax.swing.JPanel();
        sp1LBL = new javax.swing.JLabel();
        vParty = new javax.swing.JPanel();
        sp1LBL1 = new javax.swing.JLabel();
        vPosition = new javax.swing.JPanel();
        sp1LBL2 = new javax.swing.JLabel();
        vMenu = new javax.swing.JPanel();
        sp2LBL3 = new javax.swing.JLabel();
        Logout = new javax.swing.JPanel();
        sp2LBL4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
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
        elecper_filter_container = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        eper_combo_box = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(33, 97, 140));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 1080));

        jPanel14.setBackground(new java.awt.Color(33, 97, 140));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(33, 97, 140));
        jPanel13.setLayout(new java.awt.GridLayout(5, 1));

        vCandidate.setBackground(new java.awt.Color(33, 97, 140));
        vCandidate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vCandidateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vCandidateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vCandidateMouseExited(evt);
            }
        });

        sp1LBL.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 16)); // NOI18N
        sp1LBL.setForeground(new java.awt.Color(255, 255, 255));
        sp1LBL.setText(" VIEW CANDIDATES");

        javax.swing.GroupLayout vCandidateLayout = new javax.swing.GroupLayout(vCandidate);
        vCandidate.setLayout(vCandidateLayout);
        vCandidateLayout.setHorizontalGroup(
            vCandidateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vCandidateLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(sp1LBL, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        vCandidateLayout.setVerticalGroup(
            vCandidateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp1LBL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jPanel13.add(vCandidate);

        vParty.setBackground(new java.awt.Color(33, 97, 140));
        vParty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vPartyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vPartyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vPartyMouseExited(evt);
            }
        });

        sp1LBL1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 16)); // NOI18N
        sp1LBL1.setForeground(new java.awt.Color(255, 255, 255));
        sp1LBL1.setText("VIEW CANDIDATES BY PARTY");

        javax.swing.GroupLayout vPartyLayout = new javax.swing.GroupLayout(vParty);
        vParty.setLayout(vPartyLayout);
        vPartyLayout.setHorizontalGroup(
            vPartyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vPartyLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(sp1LBL1)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        vPartyLayout.setVerticalGroup(
            vPartyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp1LBL1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jPanel13.add(vParty);

        vPosition.setBackground(new java.awt.Color(33, 97, 140));
        vPosition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vPositionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vPositionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vPositionMouseExited(evt);
            }
        });

        sp1LBL2.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 16)); // NOI18N
        sp1LBL2.setForeground(new java.awt.Color(255, 255, 255));
        sp1LBL2.setText("VIEW CANDIDATES BY POSITION");

        javax.swing.GroupLayout vPositionLayout = new javax.swing.GroupLayout(vPosition);
        vPosition.setLayout(vPositionLayout);
        vPositionLayout.setHorizontalGroup(
            vPositionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vPositionLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(sp1LBL2)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        vPositionLayout.setVerticalGroup(
            vPositionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp1LBL2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jPanel13.add(vPosition);

        vMenu.setBackground(new java.awt.Color(33, 97, 140));
        vMenu.setPreferredSize(new java.awt.Dimension(250, 69));
        vMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vMenuMouseExited(evt);
            }
        });

        sp2LBL3.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 16)); // NOI18N
        sp2LBL3.setForeground(new java.awt.Color(255, 255, 255));
        sp2LBL3.setText("BACK TO MAIN MENU");

        javax.swing.GroupLayout vMenuLayout = new javax.swing.GroupLayout(vMenu);
        vMenu.setLayout(vMenuLayout);
        vMenuLayout.setHorizontalGroup(
            vMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vMenuLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(sp2LBL3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        vMenuLayout.setVerticalGroup(
            vMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp2LBL3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jPanel13.add(vMenu);

        Logout.setBackground(new java.awt.Color(33, 97, 140));
        Logout.setPreferredSize(new java.awt.Dimension(250, 69));
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LogoutMouseExited(evt);
            }
        });

        sp2LBL4.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 16)); // NOI18N
        sp2LBL4.setForeground(new java.awt.Color(255, 255, 255));
        sp2LBL4.setText("LOGOUT");

        javax.swing.GroupLayout LogoutLayout = new javax.swing.GroupLayout(Logout);
        Logout.setLayout(LogoutLayout);
        LogoutLayout.setHorizontalGroup(
            LogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LogoutLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(sp2LBL4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LogoutLayout.setVerticalGroup(
            LogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp2LBL4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jPanel13.add(Logout);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new java.awt.BorderLayout());

        header_container.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout header_containerLayout = new javax.swing.GroupLayout(header_container);
        header_container.setLayout(header_containerLayout);
        header_containerLayout.setHorizontalGroup(
            header_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2012, Short.MAX_VALUE)
        );
        header_containerLayout.setVerticalGroup(
            header_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1139, Short.MAX_VALUE)
        );

        MainPanel.add(header_container, java.awt.BorderLayout.NORTH);

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
        gridBagConstraints.weightx = 0.75;
        position_filter.add(position_combo_box, gridBagConstraints);

        candidate_filters.add(position_filter, "position_filter");

        filter_container.add(candidate_filters);

        elecper_filter_container.setBackground(new java.awt.Color(255, 255, 255));
        elecper_filter_container.setLayout(new java.awt.GridBagLayout());

        jLabel5.setLabelFor(eper_combo_box);
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
        gridBagConstraints.weightx = 0.75;
        elecper_filter_container.add(eper_combo_box, gridBagConstraints);

        filter_container.add(elecper_filter_container);

        results_container.add(filter_container, java.awt.BorderLayout.NORTH);

        MainPanel.add(results_container, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 2378, Short.MAX_VALUE)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void vCandidateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vCandidateMouseClicked
        setCard("name_filter");
        set_header("Filter Candidate by Name",
                   "Input the name of the candidate in the text field below along with the corresponding election period");
        update_table_model();
    }//GEN-LAST:event_vCandidateMouseClicked

    private void vCandidateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vCandidateMouseEntered
        // TODO add your handling code here:
        vCandidate.setBackground(hoverMENU);
    }//GEN-LAST:event_vCandidateMouseEntered

    private void vCandidateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vCandidateMouseExited
        // TODO add your handling code here:
        vCandidate.setBackground(byeMENU);
    }//GEN-LAST:event_vCandidateMouseExited

    private void vPartyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vPartyMouseClicked
        // TODO add your handling code here:
        setCard("party_filter");
        set_header("Filter Candidate by Party",
                   "Select the party from the dropdown below along with the corresponding election period");
        update_table_model();
    }//GEN-LAST:event_vPartyMouseClicked

    private void vPartyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vPartyMouseEntered
        // TODO add your handling code here:
        vParty.setBackground(hoverMENU);
    }//GEN-LAST:event_vPartyMouseEntered

    private void vPartyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vPartyMouseExited
        // TODO add your handling code here:
        vParty.setBackground(byeMENU);
    }//GEN-LAST:event_vPartyMouseExited

    private void vPositionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vPositionMouseClicked
        // TODO add your handling code here:
        setCard("position_filter");
        set_header("Filter Candidate by Position",
                   "Select the position from the dropdown below along with the corresponding election period");
        update_table_model();
    }//GEN-LAST:event_vPositionMouseClicked

    private void vPositionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vPositionMouseEntered
        // TODO add your handling code here:
        vPosition.setBackground(hoverMENU);
    }//GEN-LAST:event_vPositionMouseEntered

    private void vPositionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vPositionMouseExited
        // TODO add your handling code here:
        vPosition.setBackground(byeMENU);
    }//GEN-LAST:event_vPositionMouseExited

    private void vMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vMenuMouseClicked
        // TODO add your handling code here:
        menu = new UserMenu();
        menu.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_vMenuMouseClicked

    private void vMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vMenuMouseEntered
        // TODO add your handling code here:
        vMenu.setBackground(hoverMENU);
    }//GEN-LAST:event_vMenuMouseEntered

    private void vMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vMenuMouseExited
        // TODO add your handling code here:
        vMenu.setBackground(byeMENU);
    }//GEN-LAST:event_vMenuMouseExited

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        // TODO add your handling code here:
        login = new Frame_Login();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LogoutMouseClicked

    private void LogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseEntered
        // TODO add your handling code here:
        Logout.setBackground(hoverMENU);
    }//GEN-LAST:event_LogoutMouseEntered

    private void LogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseExited
        // TODO add your handling code here:
        Logout.setBackground(byeMENU);
    }//GEN-LAST:event_LogoutMouseExited

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void party_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_party_combo_boxActionPerformed
        // TODO add your handling code here:
        update_table_model();
    }//GEN-LAST:event_party_combo_boxActionPerformed

    private void position_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_position_combo_boxActionPerformed
        // TODO add your handling code here:
        update_table_model();
    }//GEN-LAST:event_position_combo_boxActionPerformed

    private void name_text_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_text_fieldActionPerformed
        // TODO add your handling code here:
        update_table_model();
    }//GEN-LAST:event_name_text_fieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Logout;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel candidate_filters;
    private javax.swing.JPanel elecper_filter_container;
    private javax.swing.JComboBox<String> eper_combo_box;
    private javax.swing.JPanel filter_container;
    private javax.swing.JPanel header_container;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel name_filter;
    private javax.swing.JTextField name_text_field;
    private javax.swing.JComboBox<String> party_combo_box;
    private javax.swing.JPanel party_filter;
    private javax.swing.JComboBox<String> position_combo_box;
    private javax.swing.JPanel position_filter;
    private javax.swing.JPanel results_container;
    private javax.swing.JLabel sp1LBL;
    private javax.swing.JLabel sp1LBL1;
    private javax.swing.JLabel sp1LBL2;
    private javax.swing.JLabel sp2LBL3;
    private javax.swing.JLabel sp2LBL4;
    private javax.swing.JPanel table_container;
    private javax.swing.JPanel vCandidate;
    private javax.swing.JPanel vMenu;
    private javax.swing.JPanel vParty;
    private javax.swing.JPanel vPosition;
    // End of variables declaration//GEN-END:variables
}