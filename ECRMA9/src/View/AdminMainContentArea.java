/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.FormEvent;
import Controller.FormListener;
import Controller.MainController;
import Model.Candidate;
import Model.ElecPer;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Rheeeiiii
 */
public class AdminMainContentArea extends javax.swing.JFrame{

    AdminMenu menu;
    Frame_Login login;

    private Color hoverMENU = new Color(33,82,117);
    private Color byeMENU = new Color(33,97,140);

    AdminViewCandidates myPanel;
    CandidateDetailsPanel viewCandidateDetails;
    ArchiveGUIPanel archivePanel;
    AddGUIPanel addCandidatePanel, editCandidatePanel;
    AddElectionPeriodPanel addElecPerPanel;
    AddCampaignPanel addCampaignPanel;
    CardLayout card;

    MainController program_main_controller;

    public AdminMainContentArea(MainController passed_mc) {
        initComponents();
        this.pack();

        this.setSize(new Dimension(1920,1080));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        /*Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize, ysize);*/
        this.setLocationRelativeTo(null);
        this.setTitle("Election Candidates Record Management");
        
        program_main_controller = passed_mc;
        
        panelLogo.setIcon(new ImageIcon("src\\Icons\\ecrmaLogo.png"));
        
        card = (CardLayout)MainPanel.getLayout();

        myPanel = new AdminViewCandidates(program_main_controller);
        
        addCandidatePanel = new AddGUIPanel();
        cardAddCandidate.add(addCandidatePanel);
        
        editCandidatePanel = new AddGUIPanel();
        cardEdit.add(editCandidatePanel);
        
        addElecPerPanel = new AddElectionPeriodPanel();
        addElecPerPanel.setFormListener(new FormListener() {
            @Override
            public void formEventOccurred(FormEvent e) {
                program_main_controller.elecper_controller.addElectionPeriod(e);
                JOptionPane.showMessageDialog(null, 
                                                  "Election Period was successfully added.",
                                                  "Successfully Added Election Period",
                                                  JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        cardAddElec.add(addElecPerPanel, BorderLayout.CENTER);

        addCandidatePanel.set_form_listener(new FormListener() {
            @Override
            public void formEventOccurred(FormEvent e) {
                System.out.println("Confirmed editing candidate!");
                Candidate new_info = e.getCandidate();
                boolean is_successful = program_main_controller
                                            .candidate_controller
                                            .add_candidate(e);
                if(!is_successful)
                {
                    JOptionPane.showMessageDialog(null, 
                                                  "This candidate candidate already exists.",
                                                  "Failed to Add Candidate",
                                                  JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, 
                                                  "Candidate information was successfully added.",
                                                  "Successfully Added Candidate",
                                                  JOptionPane.INFORMATION_MESSAGE);
                    
                    myPanel.center.refresh();
                    setCard("cardViewCandidates");
                }
            }
        });
        
        editCandidatePanel.set_form_listener(new FormListener() {
            @Override
            public void formEventOccurred(FormEvent e) {
                System.out.println("Confirmed editing candidate!");
                Candidate new_info = e.getCandidate();
                
                boolean is_successful = program_main_controller
                                            .candidate_controller
                                            .update_candidate(new_info.get_candidate_id(),
                                                              new_info);
                
                if(!is_successful)
                {
                    JOptionPane.showMessageDialog(null, 
                                                  "New information is a duplicate of an existing candidate.",
                                                  "Failed to Update Candidate",
                                                  JOptionPane.ERROR_MESSAGE);
                } else
                {
                    JOptionPane.showMessageDialog(null, 
                                                  "Candidate information was successfully updated.",
                                                  "Successfully Edited Candidate",
                                                  JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        myPanel.setFormListener(new FormListener() {
                           @Override
                           public void formEventOccurred(FormEvent e) {
                               String purpose = e.getPurpose();
                               System.out.println("Listener triggered");
                               System.out.println(purpose);
                               
                               if ("candidate details".equals(purpose)) {
                                   if (viewCandidateDetails != null) {
                                       cardViewDetails.remove(viewCandidateDetails);
                                   }
                                   
                                   viewCandidateDetails = new CandidateDetailsPanel(program_main_controller, e.getCandidate());
                                   cardViewDetails.add(viewCandidateDetails, BorderLayout.CENTER);
                                   
                                   setCard("cardViewDetails");
                               } else if (purpose == "candidate add") {
                                   setCard(e.getText());
                               } else if (purpose == "candidate edit") {
                                   editCandidatePanel.set_candidate_details(e.getCandidate());
                                   setCard("cardEdit");
                               }
                           } // form event occurred
                       }); // form listener

        cardViewCandidates.add(myPanel, BorderLayout.CENTER);

        archivePanel = new ArchiveGUIPanel(program_main_controller);
        cardViewElec.add(archivePanel);
        
        archivePanel.setFormListener(new FormListener() {
            @Override
                           public void formEventOccurred(FormEvent e) {
                               setCard(e.getText());
                           }
        });
        
        addCampaignPanel = new AddCampaignPanel();
        addCampaignPanel.setFormListener(new FormListener() {
            @Override
            public void formEventOccurred(FormEvent e) {
                program_main_controller.campaign_controller.addCampaign(e);
            }
        });


    }

    public void setCard(String str){
        card.show(MainPanel, str);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SidePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelLogo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        SidePanelContent = new javax.swing.JPanel();
        vCandidate = new javax.swing.JPanel();
        sp1LBL = new javax.swing.JLabel();
        vElection = new javax.swing.JPanel();
        sp2LBL2 = new javax.swing.JLabel();
        vMenu = new javax.swing.JPanel();
        sp2LBL3 = new javax.swing.JLabel();
        Logout = new javax.swing.JPanel();
        sp2LBL4 = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        cardAddCandidate = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        vcpartyTitle5 = new javax.swing.JLabel();
        vcpartySubtitle5 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        cardAddElec = new javax.swing.JPanel();
        cardEdit = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        vcpartyTitle6 = new javax.swing.JLabel();
        vcpartySubtitle6 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        cardViewCandidates = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        vcpartyTitle4 = new javax.swing.JLabel();
        vcpartySubtitle4 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        cardViewDetails = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        vcpartyTitle3 = new javax.swing.JLabel();
        vcpartySubtitle3 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        cardViewElec = new javax.swing.JPanel();
        AdminBackground = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(33, 97, 140));

        SidePanel.setBackground(new java.awt.Color(33, 97, 140));
        SidePanel.setPreferredSize(new java.awt.Dimension(100, 100));
        SidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(33, 97, 140));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(panelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        SidePanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 300, 120));
        SidePanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 260, 40));

        SidePanelContent.setBackground(new java.awt.Color(33, 97, 140));
        SidePanelContent.setLayout(new java.awt.GridLayout(4, 0));

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
            .addComponent(sp1LBL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        SidePanelContent.add(vCandidate);

        vElection.setBackground(new java.awt.Color(33, 97, 140));
        vElection.setPreferredSize(new java.awt.Dimension(250, 69));
        vElection.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vElectionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vElectionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vElectionMouseExited(evt);
            }
        });

        sp2LBL2.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 16)); // NOI18N
        sp2LBL2.setForeground(new java.awt.Color(255, 255, 255));
        sp2LBL2.setText(" VIEW ELECTIONS");

        javax.swing.GroupLayout vElectionLayout = new javax.swing.GroupLayout(vElection);
        vElection.setLayout(vElectionLayout);
        vElectionLayout.setHorizontalGroup(
            vElectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vElectionLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(sp2LBL2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        vElectionLayout.setVerticalGroup(
            vElectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp2LBL2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        SidePanelContent.add(vElection);

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
            .addComponent(sp2LBL3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        SidePanelContent.add(vMenu);

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
            .addComponent(sp2LBL4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        SidePanelContent.add(Logout);

        SidePanel.add(SidePanelContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 187, 300, 310));

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new java.awt.CardLayout());

        cardAddCandidate.setBackground(new java.awt.Color(255, 255, 255));
        cardAddCandidate.setLayout(new java.awt.BorderLayout());

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        vcpartyTitle5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 30)); // NOI18N
        vcpartyTitle5.setForeground(new java.awt.Color(33, 82, 117));
        vcpartyTitle5.setText("ADD CANDIDATE");

        vcpartySubtitle5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        vcpartySubtitle5.setForeground(new java.awt.Color(33, 82, 117));
        vcpartySubtitle5.setText("Add candidate details");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(655, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(vcpartySubtitle5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(316, 316, 316))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(vcpartyTitle5)
                        .addGap(0, 1314, Short.MAX_VALUE))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(vcpartyTitle5)
                .addGap(12, 12, 12)
                .addComponent(vcpartySubtitle5)
                .addGap(55, 55, 55)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cardAddCandidate.add(jPanel17, java.awt.BorderLayout.NORTH);

        MainPanel.add(cardAddCandidate, "cardAddCandidate");

        cardAddElec.setLayout(new java.awt.BorderLayout());
        MainPanel.add(cardAddElec, "cardAddElec");

        cardEdit.setBackground(new java.awt.Color(255, 255, 255));
        cardEdit.setLayout(new java.awt.BorderLayout());

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        vcpartyTitle6.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 30)); // NOI18N
        vcpartyTitle6.setForeground(new java.awt.Color(33, 82, 117));
        vcpartyTitle6.setText("EDIT CANDIDATES");

        vcpartySubtitle6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        vcpartySubtitle6.setForeground(new java.awt.Color(33, 82, 117));
        vcpartySubtitle6.setText("Edit Candidate Details");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(655, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(vcpartySubtitle6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(316, 316, 316))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(vcpartyTitle6)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(vcpartyTitle6)
                .addGap(12, 12, 12)
                .addComponent(vcpartySubtitle6)
                .addGap(55, 55, 55)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardEdit.add(jPanel19, java.awt.BorderLayout.NORTH);

        MainPanel.add(cardEdit, "cardEdit");

        cardViewCandidates.setBackground(new java.awt.Color(255, 255, 255));
        cardViewCandidates.setOpaque(false);
        cardViewCandidates.setLayout(new java.awt.BorderLayout());

        //jPanel14 = new CandidateDetailsPanel();
        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        vcpartyTitle4.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 30)); // NOI18N
        vcpartyTitle4.setForeground(new java.awt.Color(33, 82, 117));
        vcpartyTitle4.setText("VIEW ALL CANDIDATES");

        vcpartySubtitle4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        vcpartySubtitle4.setForeground(new java.awt.Color(33, 82, 117));
        vcpartySubtitle4.setText("View candidates");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(655, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(vcpartySubtitle4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(316, 316, 316))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(vcpartyTitle4)
                        .addGap(0, 1229, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(vcpartyTitle4)
                .addGap(12, 12, 12)
                .addComponent(vcpartySubtitle4)
                .addGap(25, 25, 25)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardViewCandidates.add(jPanel14, java.awt.BorderLayout.NORTH);

        MainPanel.add(cardViewCandidates, "cardViewCandidates");

        cardViewDetails.setBackground(new java.awt.Color(255, 255, 255));
        cardViewDetails.setPreferredSize(new java.awt.Dimension(1620, 1080));
        cardViewDetails.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        vcpartyTitle3.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 30)); // NOI18N
        vcpartyTitle3.setForeground(new java.awt.Color(33, 82, 117));
        vcpartyTitle3.setText("VIEW CANDIDATE");

        vcpartySubtitle3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        vcpartySubtitle3.setForeground(new java.awt.Color(33, 82, 117));
        vcpartySubtitle3.setText("View Candidate Details");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(655, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(vcpartySubtitle3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(316, 316, 316))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(vcpartyTitle3)
                        .addGap(0, 1302, Short.MAX_VALUE))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(vcpartyTitle3)
                .addGap(12, 12, 12)
                .addComponent(vcpartySubtitle3)
                .addGap(55, 55, 55)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardViewDetails.add(jPanel12, java.awt.BorderLayout.NORTH);

        MainPanel.add(cardViewDetails, "cardViewDetails");

        javax.swing.GroupLayout cardViewElecLayout = new javax.swing.GroupLayout(cardViewElec);
        cardViewElec.setLayout(cardViewElecLayout);
        cardViewElecLayout.setHorizontalGroup(
            cardViewElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1629, Short.MAX_VALUE)
        );
        cardViewElecLayout.setVerticalGroup(
            cardViewElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1454, Short.MAX_VALUE)
        );

        MainPanel.add(cardViewElec, "cardViewElec");

        AdminBackground.setBackground(new java.awt.Color(255, 255, 255));
        AdminBackground.setOpaque(false);
        AdminBackground.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AdminBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(AdminBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vCandidateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vCandidateMouseEntered
        // TODO add your handling code here:
        vCandidate.setBackground(hoverMENU);
    }//GEN-LAST:event_vCandidateMouseEntered

    private void vCandidateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vCandidateMouseExited
        // TODO add your handling code here:
        vCandidate.setBackground(byeMENU);
    }//GEN-LAST:event_vCandidateMouseExited

    private void vElectionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vElectionMouseEntered
        // TODO add your handling code here:
        vElection.setBackground(hoverMENU);
    }//GEN-LAST:event_vElectionMouseEntered

    private void vElectionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vElectionMouseExited
        // TODO add your handling code here:
        vElection.setBackground(byeMENU);
    }//GEN-LAST:event_vElectionMouseExited

    private void vMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vMenuMouseEntered
        // TODO add your handling code here:
        vMenu.setBackground(hoverMENU);
    }//GEN-LAST:event_vMenuMouseEntered

    private void vMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vMenuMouseExited
        // TODO add your handling code here:
        vMenu.setBackground(byeMENU);
    }//GEN-LAST:event_vMenuMouseExited

    private void LogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseEntered
        // TODO add your handling code here:
        Logout.setBackground(hoverMENU);
    }//GEN-LAST:event_LogoutMouseEntered

    private void LogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseExited
        // TODO add your handling code here:
        Logout.setBackground(byeMENU);
    }//GEN-LAST:event_LogoutMouseExited

    private void vCandidateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vCandidateMouseClicked
        // TODO add your handling code here:
        System.out.println("CALLED MOUSE CLICK");
        try {
            System.out.println("before called vCandidate");
            card.show(MainPanel, "cardViewCandidates");
            System.out.println("after called vCandidate");
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED");
        }
    }//GEN-LAST:event_vCandidateMouseClicked

    private void vElectionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vElectionMouseClicked
        // TODO add your handling code here:
        card.show(MainPanel, "cardViewElec");
    }//GEN-LAST:event_vElectionMouseClicked

    private void vMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vMenuMouseClicked
        // TODO add your handling code here:
        menu = new AdminMenu(program_main_controller);
        menu.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_vMenuMouseClicked

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        // TODO add your handling code here:
        login = new Frame_Login();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LogoutMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminBackground;
    private javax.swing.JPanel Logout;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JPanel SidePanelContent;
    private javax.swing.JPanel cardAddCandidate;
    private javax.swing.JPanel cardAddElec;
    private javax.swing.JPanel cardEdit;
    private javax.swing.JPanel cardViewCandidates;
    private javax.swing.JPanel cardViewDetails;
    private javax.swing.JPanel cardViewElec;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JLabel panelLogo;
    private javax.swing.JLabel sp1LBL;
    private javax.swing.JLabel sp2LBL2;
    private javax.swing.JLabel sp2LBL3;
    private javax.swing.JLabel sp2LBL4;
    private javax.swing.JPanel vCandidate;
    private javax.swing.JPanel vElection;
    private javax.swing.JPanel vMenu;
    private javax.swing.JLabel vcpartySubtitle3;
    private javax.swing.JLabel vcpartySubtitle4;
    private javax.swing.JLabel vcpartySubtitle5;
    private javax.swing.JLabel vcpartySubtitle6;
    private javax.swing.JLabel vcpartyTitle3;
    private javax.swing.JLabel vcpartyTitle4;
    private javax.swing.JLabel vcpartyTitle5;
    private javax.swing.JLabel vcpartyTitle6;
    // End of variables declaration//GEN-END:variables
}
