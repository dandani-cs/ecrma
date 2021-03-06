/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package card;

import card.All.AdminViewCandidates.Center;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rheeeiiii
 */
public class All extends javax.swing.JFrame{
    
    private Color hoverMENU = new Color(33,82,117);
    private Color byeMENU = new Color(33,97,140);
    
    AdminViewCandidates myPanel = new AdminViewCandidates();

    public All() {
        initComponents(); 
        setSize(1920, 1080);
        this.setTitle("Election Candidates Record Management");
        cardViewCandidates.add(myPanel);
    }
    
   public void reset(){
         jTextField1.setText("NAME");
         jTextField3.setText("Position");
         jTextField2.setText(" ");
         jTextField4.setText(" ");
         jTextField5.setText(" ");
         jTextField6.setText("Course");
         jTextField7.setText("University");
         jTextField8.setText("Date");
         jTextField9.setText("Course");
         jTextField10.setText("University");
         jTextField11.setText("Date");
         jTextArea1.setText(" ");
     }
   
   public void reset1(){
         jTextField56.setText("NAME");
         jTextField57.setText("Position");
         jTextField58.setText(" ");
         jTextField59.setText(" ");
         jTextField60.setText(" ");
         jTextField61.setText("Course");
         jTextField62.setText("University");
         jTextField63.setText("Date");
         jTextField64.setText("Course");
         jTextField65.setText("University");
         jTextField66.setText("Date");
         jTextArea6.setText(" ");
   }

   
public class AdminViewCandidates extends JPanel implements ActionListener{
    
    Color bgColor;
    
    Center center;

    
    public AdminViewCandidates(){
        bgColor = new Color(255,255,255);
        
        this.setLayout(new BorderLayout());
        Border ogg_border = this.getBorder();
        Border margin1 = new EmptyBorder(0,-30, 0, 30);
        this.setBorder(new CompoundBorder(ogg_border, margin1));
    
        center = new Center();
        center.btn_add.addActionListener(this);
        
        this.add(center, BorderLayout.CENTER);

        this.setBackground(bgColor);
        
        setVisible(true);
        setSize(new Dimension(1360,880));
    }
 @Override
    public void actionPerformed(ActionEvent e) {
        cardAddElec.setVisible(false);
        this.remove(cardAddElec);
        cardViewCandidates.setVisible(false);
        this.remove(cardViewCandidates);

        cardAddCandidate.setVisible(true);
        cardAddCandidate.repaint();
        cardAddCandidate.revalidate();
        
         setSize(new Dimension(1360,850));
    }
    
    
    class Center extends JPanel {
        JTable table;
        JButton btn_add;
        JButton btn_edit;
        
        Center() {
            this.setLayout(new BorderLayout());
            this.setBorder(new EmptyBorder(60,60,60,60));
            this.setOpaque(false);
            
            
            String[] colNames = {"","Name", "Party", "Position"};
            ImageIcon[] img = new ImageIcon[24];
            for(int i = 0; i < 24; i++) {
                Random rand = new Random();
                String str = "C:\\Users\\Admin\\Documents\\GitHub\\ecrma\\ECRMA9\\src\\Icons\\pic"+ (rand.nextInt(4) + 1) +".jpg";
                img[i] = new ImageIcon(str);
                Image imagestr = img[i].getImage();
                imagestr = imagestr.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
                img[i] = new ImageIcon(imagestr);
            }
                 
            Object[][] data = {{img[0],"Esther Hamer","Right","Senator"},
                            {img[1],"Fred Leach","Right","Vice President"},
                            {img[2],"Aaisha Coles","Left","Vice President"},
                            {img[3],"Carl Carpenter","Center","President"},
                            {img[4],"Malcolm Mcknight","Center","Vice President"},
                            {img[5],"Bridget Everett","Left","Senator"},
                            {img[6],"Malaki Grant","Party Party","Prime Minister"},
                            {img[7],"Poppy-Rose Fellows","Party Party","President"},
                            {img[8],"Barbara Emery","Right","Prime Minister"},
                            {img[9],"Keeva Vance","Party Party","Senator"},
                            {img[10],"Saif Southern","Center","Senator"},
                            {img[11],"Jo Zhang","Right","President"},
                            {img[12],"Piotr Wolf","Right","Prime Minister"},
                            {img[13],"Kali Dorsey","Center","Senator"},
                            {img[14],"Abdul Mccabe","Left","President"},
                            {img[15],"Hakeem Hilton","Left","Vice President"},
                            {img[16],"Kieren Khan","Party Party","President"},
                            {img[17],"Romany Wells","Right","Prime Minister"},
                            {img[18],"Emmanuella Hayden","Left","Prime Minister"},
                            {img[19],"Naseem Marshall","Center","Vice President"},
                            {img[20],"Frank Alford","Center","Senator"},
                            {img[21],"Tobey Lim","Party Party","President"},
                            {img[22],"Kareena Palmer","Left","Prime Minister"},
                            {img[23],"Izzy Harris","Party Party","Vice President"},
            };
            
            DefaultTableModel model;
            model = new DefaultTableModel(data,colNames);

            table = new JTable() {
//               public boolean editCellAt(int row, int column, java.util.EventObject e) {
//                    return false;
//                }
            };
            table.setModel(model);
            table.getColumnModel().getColumn(0).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
            
            table.getColumnModel().getColumn(0).setMaxWidth(120);
            table.getColumnModel().getColumn(0).setMinWidth(120);
            
            table.setRowHeight(120);
           
   
            table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN,24));
            table.setFont(new Font("TAHOMA", Font.PLAIN, 18));
            
            JScrollPane sp = new JScrollPane(table);
            
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.rowAtPoint(evt.getPoint());
                    int col = table.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                         cardViewCandidates.setVisible(false);
                         remove(cardViewCandidates);

        cardViewDetails.setVisible(true);
        cardViewDetails.repaint();
        cardViewDetails.revalidate();
        setSize(new Dimension(1920,1080));
                    }
                }   
    });
            
            //add button
            btn_add = new JButton("<html><center>ADD CANDIDATE</center></html>");
            btn_add.setBackground(new Color(33,97,140));
            btn_add.setFont(new Font("CALIBRI", Font.BOLD, 18));
            btn_add.setForeground(Color.WHITE);
            
            btn_edit = new JButton("<html><center>EDIT CANDIDATE</center></html>"); 
            btn_edit.setBackground(new Color(33,97,140));
            btn_edit.setFont(new Font("CALIBRI", Font.BOLD, 18));
            btn_edit.setForeground(Color.WHITE);
  
            this.revalidate();
            this.repaint();
            this.add(sp);
            this.add(btn_add, BorderLayout.SOUTH);
            //this.add(btn_edit, BorderLayout.SOUTH); //////////aaaa
        }
       
    }
    
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

        AdminBackground = new javax.swing.JPanel();
        SidePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
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
        card1 = new javax.swing.JPanel();
        cardViewCandidates = new javax.swing.JPanel();
        cardViewDetails = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField67 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        cardAddCandidate = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jTextField56 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jTextField57 = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jTextField58 = new javax.swing.JTextField();
        jTextField59 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jTextField60 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jTextField61 = new javax.swing.JTextField();
        jTextField62 = new javax.swing.JTextField();
        jTextField63 = new javax.swing.JTextField();
        jTextField64 = new javax.swing.JTextField();
        jTextField65 = new javax.swing.JTextField();
        jTextField66 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jSeparator21 = new javax.swing.JSeparator();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        cardEdit = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator6 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cardAddElec = new javax.swing.JPanel();
        cardBG = new javax.swing.JPanel();
        formtitle = new javax.swing.JLabel();
        formsubtitle = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        cancelBTN = new javax.swing.JButton();
        saveBTN = new javax.swing.JButton();
        cardViewElec = new javax.swing.JPanel();
        archivecardBG = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        datatable = new javax.swing.JTable();
        archiveBTN = new javax.swing.JButton();
        archivetitle = new javax.swing.JLabel();
        archivesubtitle = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AdminBackground.setLayout(new java.awt.BorderLayout());

        SidePanel.setBackground(new java.awt.Color(33, 97, 140));
        SidePanel.setPreferredSize(new java.awt.Dimension(100, 100));
        SidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        SidePanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 120));
        SidePanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 280, 30));

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
                .addGap(39, 39, 39)
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
                .addGap(42, 42, 42)
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
                .addGap(42, 42, 42)
                .addComponent(sp2LBL4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LogoutLayout.setVerticalGroup(
            LogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp2LBL4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        SidePanelContent.add(Logout);

        SidePanel.add(SidePanelContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 187, 300, 310));

        card1.setBackground(new java.awt.Color(204, 255, 255));
        card1.setLayout(new java.awt.CardLayout());

        cardViewCandidates.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout cardViewCandidatesLayout = new javax.swing.GroupLayout(cardViewCandidates);
        cardViewCandidates.setLayout(cardViewCandidatesLayout);
        cardViewCandidatesLayout.setHorizontalGroup(
            cardViewCandidatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1630, Short.MAX_VALUE)
        );
        cardViewCandidatesLayout.setVerticalGroup(
            cardViewCandidatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1130, Short.MAX_VALUE)
        );

        card1.add(cardViewCandidates, "card5");

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 97, 140)));

        jLabel29.setBackground(new java.awt.Color(33, 97, 140));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(33, 97, 140));
        jLabel29.setText("Personal Information");

        jPanel16.setLayout(new java.awt.BorderLayout());

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new java.awt.GridBagLayout());

        jTextField25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 32, 0, 0);
        jPanel17.add(jTextField25, gridBagConstraints);

        jTextField26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField26.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 445, 0, 24);
        jPanel17.add(jTextField26, gridBagConstraints);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(33, 97, 140));
        jLabel30.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 533, 0, 0);
        jPanel17.add(jLabel30, gridBagConstraints);

        jTextField27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField27.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 32, 0, 0);
        jPanel17.add(jTextField27, gridBagConstraints);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(33, 97, 140));
        jLabel31.setText("Party List");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 108, 0, 0);
        jPanel17.add(jLabel31, gridBagConstraints);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(33, 97, 140));
        jLabel32.setText("Date of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 85, 0, 0);
        jPanel17.add(jLabel32, gridBagConstraints);

        jTextField28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField28.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 445, 0, 24);
        jPanel17.add(jTextField28, gridBagConstraints);

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(33, 97, 140));
        jLabel54.setText("Religion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 518, 0, 0);
        jPanel17.add(jLabel54, gridBagConstraints);

        jLabel55.setBackground(new java.awt.Color(33, 97, 140));
        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(33, 97, 140));
        jLabel55.setText("Educational Background");

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new java.awt.GridBagLayout());

        jTextField29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField29.setText("Course");
        jTextField29.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 474;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 36, 0, 0);
        jPanel18.add(jTextField29, gridBagConstraints);

        jTextField30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField30.setForeground(new java.awt.Color(33, 97, 140));
        jTextField30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField30.setText("University");
        jTextField30.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 378;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 82, 0, 0);
        jPanel18.add(jTextField30, gridBagConstraints);

        jTextField31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField31.setText("Date");
        jTextField31.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 216;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 161, 0, 0);
        jPanel18.add(jTextField31, gridBagConstraints);

        jTextField32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField32.setText("Course");
        jTextField32.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 474;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 36, 0, 0);
        jPanel18.add(jTextField32, gridBagConstraints);

        jTextField33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField33.setForeground(new java.awt.Color(33, 97, 140));
        jTextField33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField33.setText("University");
        jTextField33.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 378;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 82, 0, 0);
        jPanel18.add(jTextField33, gridBagConstraints);

        jTextField67.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField67.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField67.setText("Date");
        jTextField67.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField67ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 217;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 160, 0, 0);
        jPanel18.add(jTextField67, gridBagConstraints);

        jLabel56.setBackground(new java.awt.Color(33, 97, 140));
        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(33, 97, 140));
        jLabel56.setText("Platform");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(33, 97, 140));
        jLabel57.setText("NAME");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel58.setText("Position");

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel59.setText("Party");

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane4.setToolTipText("");
        jScrollPane4.setName(""); // NOI18N

        jButton16.setBackground(new java.awt.Color(33, 97, 140));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("EDIT");
        jButton16.setContentAreaFilled(false);
        jButton16.setOpaque(true);
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton16MouseClicked(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(33, 97, 140));
        jButton17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("DELETE");
        jButton17.setContentAreaFilled(false);
        jButton17.setOpaque(true);
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton17MouseClicked(evt);
            }
        });
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                            .addComponent(jSeparator12)
                            .addComponent(jLabel55)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel58)
                                    .addComponent(jLabel59)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jButton16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton17))))
                            .addComponent(jLabel29)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                            .addComponent(jLabel56))))
                .addGap(0, 430, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel29))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton16)
                            .addComponent(jButton17))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cardViewDetailsLayout = new javax.swing.GroupLayout(cardViewDetails);
        cardViewDetails.setLayout(cardViewDetailsLayout);
        cardViewDetailsLayout.setHorizontalGroup(
            cardViewDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardViewDetailsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardViewDetailsLayout.setVerticalGroup(
            cardViewDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        card1.add(cardViewDetails, "card6");

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 97, 140)));

        jTextField56.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jTextField56.setForeground(new java.awt.Color(33, 97, 140));
        jTextField56.setText("NAME");
        jTextField56.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jLabel48.setBackground(new java.awt.Color(33, 97, 140));
        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(33, 97, 140));
        jLabel48.setText("Personal Information");

        jTextField57.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTextField57.setText("Position");
        jTextField57.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jPanel27.setLayout(new java.awt.BorderLayout());

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(new java.awt.GridBagLayout());

        jTextField58.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField58.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField58.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 41, 0, 0);
        jPanel28.add(jTextField58, gridBagConstraints);

        jTextField59.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField59.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField59.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 120, 0, 0);
        jPanel28.add(jTextField59, gridBagConstraints);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(33, 97, 140));
        jLabel49.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 212, 0, 0);
        jPanel28.add(jLabel49, gridBagConstraints);

        jTextField60.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField60.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField60.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 120, 0, 30);
        jPanel28.add(jTextField60, gridBagConstraints);

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(33, 97, 140));
        jLabel50.setText("Party List");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 196, 0, 0);
        jPanel28.add(jLabel50, gridBagConstraints);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(33, 97, 140));
        jLabel51.setText("Date of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 94, 0, 0);
        jPanel28.add(jLabel51, gridBagConstraints);

        jLabel52.setBackground(new java.awt.Color(33, 97, 140));
        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(33, 97, 140));
        jLabel52.setText("Educational Background");

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setLayout(new java.awt.GridBagLayout());

        jTextField61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField61.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField61.setText("Course");
        jTextField61.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 474;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 36, 0, 0);
        jPanel29.add(jTextField61, gridBagConstraints);

        jTextField62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField62.setForeground(new java.awt.Color(33, 97, 140));
        jTextField62.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField62.setText("University");
        jTextField62.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 378;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 82, 0, 0);
        jPanel29.add(jTextField62, gridBagConstraints);

        jTextField63.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField63.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField63.setText("Date");
        jTextField63.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 216;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 161, 0, 0);
        jPanel29.add(jTextField63, gridBagConstraints);

        jTextField64.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField64.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField64.setText("Course");
        jTextField64.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 474;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 36, 0, 0);
        jPanel29.add(jTextField64, gridBagConstraints);

        jTextField65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField65.setForeground(new java.awt.Color(33, 97, 140));
        jTextField65.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField65.setText("University");
        jTextField65.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 378;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 82, 0, 0);
        jPanel29.add(jTextField65, gridBagConstraints);

        jTextField66.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField66.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField66.setText("Date");
        jTextField66.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 217;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 160, 0, 0);
        jPanel29.add(jTextField66, gridBagConstraints);

        jLabel53.setBackground(new java.awt.Color(33, 97, 140));
        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(33, 97, 140));
        jLabel53.setText("Platform");

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jTextArea6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane7.setViewportView(jTextArea6);

        jButton13.setBackground(new java.awt.Color(33, 97, 140));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Cancel");
        jButton13.setContentAreaFilled(false);
        jButton13.setOpaque(true);
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(33, 97, 140));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Reset");
        jButton14.setContentAreaFilled(false);
        jButton14.setOpaque(true);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(33, 97, 140));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Confirm");
        jButton15.setContentAreaFilled(false);
        jButton15.setOpaque(true);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel30Layout.createSequentialGroup()
                                    .addComponent(jButton13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton15))
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSeparator20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
                                    .addComponent(jSeparator19, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator21, javax.swing.GroupLayout.Alignment.LEADING)))))
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(564, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel48))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton14)
                    .addComponent(jButton13))
                .addContainerGap(315, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cardAddCandidateLayout = new javax.swing.GroupLayout(cardAddCandidate);
        cardAddCandidate.setLayout(cardAddCandidateLayout);
        cardAddCandidateLayout.setHorizontalGroup(
            cardAddCandidateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cardAddCandidateLayout.setVerticalGroup(
            cardAddCandidateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardAddCandidateLayout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        card1.add(cardAddCandidate, "card4");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 97, 140)));

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(33, 97, 140));
        jTextField1.setText("NAME");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(33, 97, 140));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(33, 97, 140));
        jLabel17.setText("Personal Information");

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTextField3.setText("Position");
        jTextField3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 41, 0, 0);
        jPanel7.add(jTextField2, gridBagConstraints);

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 120, 0, 0);
        jPanel7.add(jTextField4, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(33, 97, 140));
        jLabel7.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 212, 0, 0);
        jPanel7.add(jLabel7, gridBagConstraints);

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 120, 0, 30);
        jPanel7.add(jTextField5, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(33, 97, 140));
        jLabel8.setText("Party List");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 196, 0, 0);
        jPanel7.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(33, 97, 140));
        jLabel9.setText("Date of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 94, 0, 0);
        jPanel7.add(jLabel9, gridBagConstraints);

        jLabel18.setBackground(new java.awt.Color(33, 97, 140));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(33, 97, 140));
        jLabel18.setText("Educational Background");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("Course");
        jTextField6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 474;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 36, 0, 0);
        jPanel8.add(jTextField6, gridBagConstraints);

        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(33, 97, 140));
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText("University");
        jTextField7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 378;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 82, 0, 0);
        jPanel8.add(jTextField7, gridBagConstraints);

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText("Date");
        jTextField8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 216;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 161, 0, 0);
        jPanel8.add(jTextField8, gridBagConstraints);

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setText("Course");
        jTextField9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 474;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 36, 0, 0);
        jPanel8.add(jTextField9, gridBagConstraints);

        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(33, 97, 140));
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setText("University");
        jTextField10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 378;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 82, 0, 0);
        jPanel8.add(jTextField10, gridBagConstraints);

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setText("Date");
        jTextField11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 217;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 160, 0, 0);
        jPanel8.add(jTextField11, gridBagConstraints);

        jLabel19.setBackground(new java.awt.Color(33, 97, 140));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(33, 97, 140));
        jLabel19.setText("Platform");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setViewportView(jTextArea1);

        jButton1.setBackground(new java.awt.Color(33, 97, 140));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cancel");
        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(33, 97, 140));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Reset");
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(33, 97, 140));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Confirm");
        jButton3.setContentAreaFilled(false);
        jButton3.setOpaque(true);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel17))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel18))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel19))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(900, 900, 900)
                .addComponent(jButton1)
                .addGap(13, 13, 13)
                .addComponent(jButton2)
                .addGap(9, 9, 9)
                .addComponent(jButton3))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(jLabel17)
                .addGap(6, 6, 6)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel18)
                .addGap(6, 6, 6)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addGap(6, 6, 6)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)))
        );

        javax.swing.GroupLayout cardEditLayout = new javax.swing.GroupLayout(cardEdit);
        cardEdit.setLayout(cardEditLayout);
        cardEditLayout.setHorizontalGroup(
            cardEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardEditLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardEditLayout.setVerticalGroup(
            cardEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        card1.add(cardEdit, "card3");

        cardBG.setBackground(new java.awt.Color(255, 255, 255));

        formtitle.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 30)); // NOI18N
        formtitle.setForeground(new java.awt.Color(33, 82, 117));
        formtitle.setText("ADD ELECTION PERIOD");

        formsubtitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        formsubtitle.setForeground(new java.awt.Color(33, 82, 117));
        formsubtitle.setText("Set the name, starting date, and final date of the Election Period.");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(33, 82, 117));
        jLabel1.setText("Name of Election Period:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(33, 82, 117));
        jLabel3.setText("Candidate:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(33, 82, 117));
        jLabel4.setText("Party:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(33, 82, 117));
        jLabel26.setText("Position:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(33, 82, 117));
        jLabel27.setText("Platform:");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(33, 82, 117));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "", "" }));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(33, 82, 117));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "", "" }));

        jTextField23.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTextField23.setForeground(new java.awt.Color(33, 82, 117));

        jTextField24.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTextField24.setForeground(new java.awt.Color(33, 82, 117));

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTextArea3.setForeground(new java.awt.Color(33, 82, 117));
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jScrollPane1.setViewportView(jTextArea3);

        cancelBTN.setBackground(new java.awt.Color(255, 255, 255));
        cancelBTN.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 15)); // NOI18N
        cancelBTN.setForeground(new java.awt.Color(33, 82, 117));
        cancelBTN.setText("Cancel");
        cancelBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelBTNMouseExited(evt);
            }
        });
        cancelBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBTNActionPerformed(evt);
            }
        });

        saveBTN.setBackground(new java.awt.Color(255, 255, 255));
        saveBTN.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 15)); // NOI18N
        saveBTN.setForeground(new java.awt.Color(33, 82, 117));
        saveBTN.setText("Save");
        saveBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveBTNMouseExited(evt);
            }
        });

        javax.swing.GroupLayout cardBGLayout = new javax.swing.GroupLayout(cardBG);
        cardBG.setLayout(cardBGLayout);
        cardBGLayout.setHorizontalGroup(
            cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardBGLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formsubtitle)
                    .addComponent(formtitle)
                    .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(cardBGLayout.createSequentialGroup()
                            .addComponent(cancelBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(saveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(cardBGLayout.createSequentialGroup()
                            .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel26)
                                .addComponent(jLabel27))
                            .addGap(200, 200, 200)
                            .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField23)
                                .addComponent(jTextField24)
                                .addComponent(jScrollPane1)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 1392, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardBGLayout.setVerticalGroup(
            cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardBGLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(formtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formsubtitle)
                .addGap(52, 52, 52)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112)
                .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 337, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cardAddElecLayout = new javax.swing.GroupLayout(cardAddElec);
        cardAddElec.setLayout(cardAddElecLayout);
        cardAddElecLayout.setHorizontalGroup(
            cardAddElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cardAddElecLayout.setVerticalGroup(
            cardAddElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        card1.add(cardAddElec, "card2");

        archivecardBG.setBackground(new java.awt.Color(255, 255, 255));

        datatable.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        datatable.setForeground(new java.awt.Color(33, 82, 117));
        datatable.setGridColor(new java.awt.Color(0, 51, 51));
        datatable.setRowHeight(20);
        datatable.setSelectionBackground(new java.awt.Color(33, 82, 117));
        datatable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                datatableMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(datatable);

        archiveBTN.setBackground(new java.awt.Color(255, 255, 255));
        archiveBTN.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 15)); // NOI18N
        archiveBTN.setForeground(new java.awt.Color(33, 82, 117));
        archiveBTN.setText("Archive Data");
        archiveBTN.setBorderPainted(false);
        archiveBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        archiveBTN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        archiveBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                archiveBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                archiveBTNMouseExited(evt);
            }
        });
        archiveBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveBTNActionPerformed(evt);
            }
        });

        archivetitle.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 30)); // NOI18N
        archivetitle.setForeground(new java.awt.Color(33, 82, 117));
        archivetitle.setText("MANAGE DATA BY ELECTION PERIOD");

        archivesubtitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        archivesubtitle.setForeground(new java.awt.Color(33, 82, 117));
        archivesubtitle.setText("Select the Election Period/s to archive from the data table below.");

        javax.swing.GroupLayout archivecardBGLayout = new javax.swing.GroupLayout(archivecardBG);
        archivecardBG.setLayout(archivecardBGLayout);
        archivecardBGLayout.setHorizontalGroup(
            archivecardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(archivecardBGLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(archivecardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(archivesubtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(archivecardBGLayout.createSequentialGroup()
                        .addComponent(archivetitle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(archivecardBGLayout.createSequentialGroup()
                .addGroup(archivecardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(archivecardBGLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1031, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(archivecardBGLayout.createSequentialGroup()
                        .addGap(540, 540, 540)
                        .addComponent(archiveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, archivecardBGLayout.createSequentialGroup()
                .addGap(0, 59, Short.MAX_VALUE)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 1571, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        archivecardBGLayout.setVerticalGroup(
            archivecardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, archivecardBGLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(archivetitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(archivesubtitle)
                .addGap(44, 44, 44)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(archiveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(443, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cardViewElecLayout = new javax.swing.GroupLayout(cardViewElec);
        cardViewElec.setLayout(cardViewElecLayout);
        cardViewElecLayout.setHorizontalGroup(
            cardViewElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1630, Short.MAX_VALUE)
            .addGroup(cardViewElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cardViewElecLayout.createSequentialGroup()
                    .addComponent(archivecardBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        cardViewElecLayout.setVerticalGroup(
            cardViewElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1130, Short.MAX_VALUE)
            .addGroup(cardViewElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(archivecardBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        card1.add(cardViewElec, "card7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AdminBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(card1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(AdminBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(card1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cardEdit.setVisible(false);
        cardAddElec.setVisible(false);
        cardViewDetails.setVisible(false);
        cardAddCandidate.setVisible(false);
        cardViewElec.setVisible(false);
        this.remove(cardEdit);
        this.remove(cardAddElec);
        this.remove(cardAddCandidate);
        this.remove(cardViewDetails);

        cardViewCandidates.setVisible(true);
        cardViewCandidates.repaint();
        cardViewCandidates.revalidate();
  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        reset1();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        //new ViewCandidateDetails().setVisible(true);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        cardViewDetails.setVisible(false);
        cardEdit.setVisible(false);
        cardAddElec.setVisible(false);
        cardAddCandidate.setVisible(false);
        cardViewElec.setVisible(false);
        this.remove(cardViewCandidates);
        this.remove(cardViewDetails);
        this.remove(cardAddElec);
        this.remove(cardEdit);

        cardViewCandidates.setVisible(true);
        cardViewCandidates.repaint();
        cardViewCandidates.revalidate();
        setSize(new Dimension(1920,1080));
                   
    }//GEN-LAST:event_jButton13ActionPerformed

    private void cancelBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBTNMouseEntered
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cancelBTNMouseEntered

    private void cancelBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBTNMouseExited
        // TODO add your handling code here:
      
    }//GEN-LAST:event_cancelBTNMouseExited

    private void cancelBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelBTNActionPerformed

    private void saveBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveBTNMouseEntered
        // TODO add your handling code here:
     
    }//GEN-LAST:event_saveBTNMouseEntered

    private void saveBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveBTNMouseExited
      
    }//GEN-LAST:event_saveBTNMouseExited

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void jTextField67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField67ActionPerformed

    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseClicked
        // TODO add your handling code here:
        cardViewCandidates.setVisible(false);
        cardViewDetails.setVisible(false);
        cardAddElec.setVisible(false);
        cardAddCandidate.setVisible(false);
        cardViewElec.setVisible(false);
        this.remove(cardViewDetails);
        this.remove(cardViewCandidates);
        this.remove(cardAddElec);
        this.remove(cardAddCandidate);

        cardEdit.setVisible(true);
        cardEdit.repaint();
        cardEdit.revalidate();
        cardEdit.setSize(new Dimension(960,880));
    }//GEN-LAST:event_jButton16MouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseClicked
        // TODO add your handling code here:
        new DeleteGUI().setVisible(true);
    }//GEN-LAST:event_jButton17MouseClicked

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
        cardEdit.setVisible(false);
        cardAddElec.setVisible(false);
        cardViewDetails.setVisible(false);
        cardAddCandidate.setVisible(false);
        cardViewElec.setVisible(false);
        this.remove(cardEdit);
        this.remove(cardAddElec);
        this.remove(cardAddCandidate);
        this.remove(cardViewDetails);

        cardViewCandidates.setVisible(true);
        cardViewCandidates.repaint();
        cardViewCandidates.revalidate();
    }//GEN-LAST:event_vCandidateMouseClicked

    private void vElectionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vElectionMouseClicked
        // TODO add your handling code here:
        cardEdit.setVisible(false);
        cardViewCandidates.setVisible(false);
        cardViewDetails.setVisible(false);
        cardAddCandidate.setVisible(false);
        cardAddElec.setVisible(false);
        this.remove(cardEdit);
        this.remove(cardAddElec);
        this.remove(cardAddCandidate);
        this.remove(cardViewDetails);

        cardViewElec.setVisible(true);
        cardViewElec.repaint();
        cardViewElec.revalidate();
    }//GEN-LAST:event_vElectionMouseClicked

    private void datatableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datatableMousePressed
        if(evt.getClickCount() == 1) {
            boolean setArchived = false;

            boolean archived = (boolean) datatable.getModel().getValueAt(datatable.getSelectedRow(), 4);
            if(archived == true) {
                datatable.getModel().setValueAt(setArchived, datatable.getSelectedRow(), 4);
            } else {
                setArchived = true;
                datatable.getModel().setValueAt(setArchived, datatable.getSelectedRow(), 4);
            }

            //ElecPer ep = new ElecPer();
            //ep.setElecPerId((int) datatable.getModel().getValueAt(datatable.getSelectedRow(), 0));
            //ep.setArchived(setArchived);
            //FormEvent e = new FormEvent(evt, ep);
            //ElecPerController epc = new ElecPerController();
            //epc.archivedElectionPeriod(e);
        }
    }//GEN-LAST:event_datatableMousePressed

    private void archiveBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archiveBTNMouseEntered
        // TODO add your handling code here:
        Color hoverBTN = new Color(15,43,61);

        archiveBTN.setBackground(hoverBTN);
        archiveBTN.setForeground(Color.WHITE);
    }//GEN-LAST:event_archiveBTNMouseEntered

    private void archiveBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archiveBTNMouseExited
        // TODO add your handling code here:
        Color byeBTN = new Color(0,51,51);

        archiveBTN.setBackground(Color.WHITE);
        archiveBTN.setForeground(byeBTN);
    }//GEN-LAST:event_archiveBTNMouseExited

    private void archiveBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveBTNActionPerformed
        // TODO add your handling code here:
        cardEdit.setVisible(false);
        cardViewCandidates.setVisible(false);
        cardViewDetails.setVisible(false);
        cardAddCandidate.setVisible(false);
        cardViewElec.setVisible(false);
        this.remove(cardEdit);
        this.remove(cardAddElec);
        this.remove(cardAddCandidate);
        this.remove(cardViewDetails);

        cardAddElec.setVisible(true);
        cardAddElec.repaint();
        cardAddElec.revalidate();
        
    }//GEN-LAST:event_archiveBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(All.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(All.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(All.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(All.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
         
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                
                new All().setVisible(true);    
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminBackground;
    private javax.swing.JPanel Logout;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JPanel SidePanelContent;
    private javax.swing.JButton archiveBTN;
    private javax.swing.JPanel archivecardBG;
    private javax.swing.JLabel archivesubtitle;
    private javax.swing.JLabel archivetitle;
    private javax.swing.JPanel back;
    private javax.swing.JButton cancelBTN;
    private javax.swing.JPanel card1;
    private javax.swing.JPanel cardAddCandidate;
    private javax.swing.JPanel cardAddElec;
    private javax.swing.JPanel cardBG;
    private javax.swing.JPanel cardEdit;
    private javax.swing.JPanel cardViewCandidates;
    private javax.swing.JPanel cardViewDetails;
    private javax.swing.JPanel cardViewElec;
    private javax.swing.JTable datatable;
    private javax.swing.JLabel formsubtitle;
    private javax.swing.JLabel formtitle;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton saveBTN;
    private javax.swing.JLabel sp1LBL;
    private javax.swing.JLabel sp2LBL2;
    private javax.swing.JLabel sp2LBL3;
    private javax.swing.JLabel sp2LBL4;
    private javax.swing.JLabel sp3LBL;
    private javax.swing.JPanel vCandidate;
    private javax.swing.JPanel vElection;
    private javax.swing.JPanel vMenu;
    // End of variables declaration//GEN-END:variables
}
