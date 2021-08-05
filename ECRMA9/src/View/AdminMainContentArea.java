/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MainController;
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
    
    AdminViewCandidates myPanel = new AdminViewCandidates();

    CardLayout card; 
    
    MainController main_controller = new MainController();
    
   
    public AdminMainContentArea() {
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
        panelLogo.setIcon(new ImageIcon("C:\\Users\\Admin\\Documents\\GitHub\\ecrma\\ECRMA9\\src\\Icons\\candidate2_128px.png"));
        cardViewCandidates.add(myPanel);
        
        card = (CardLayout)MainPanel.getLayout();
        
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < campaigntable.getColumnModel().getColumnCount(); i++) {
            campaigntable.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);           
            
        JTableHeader campaignheader = campaigntable.getTableHeader();
        
        campaignheader.setForeground(hoverMENU);
        campaignheader.setBackground(Color.WHITE);
        campaignheader.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        setSize(1620,1000);
        
        
        }
     
    }
    
    public void setCard(String str){
        card.show(MainPanel, str);
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
//         jTextField56.setText("NAME");
//         jTextField57.setText("Position");
//         jTextField58.setText(" ");
//         jTextField59.setText(" ");
//         jTextField60.setText(" ");
//         jTextField61.setText("Course");
//         jTextField62.setText("University");
//         jTextField63.setText("Date");
//         jTextField64.setText("Course");
//         jTextField65.setText("University");
//         jTextField66.setText("Date");
//         jTextArea6.setText(" ");
   }

   
public class AdminViewCandidates extends JPanel implements ActionListener{
    
    Color bgColor;
    
    Center center;
    
    AdminViewCandidates adminViewCandidatesPanel;

    
    public AdminViewCandidates(){
        //adminViewCandidatesPanel = new AdminViewCandidates();
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
        setSize(new Dimension(1620,1000));
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
        
         setSize(new Dimension(1620,1000));
    }
    
    
    class Center extends JPanel {
        JTable table;
        JButton btn_add;
        JButton btn_edit;
        JLabel lbl_header, lbl_description;
        JPanel header;
        TableCellRenderer button_renderer;
        AdminViewCandidatesTableModel model;
        
        
        Center() {
            this.setLayout(new BorderLayout());
            this.setBorder(new EmptyBorder(243,103,60,60));
            this.setOpaque(true);
            button_renderer = new JTableButtonRenderer();
            bgColor = new Color(255,255,255);
            this.setBackground(bgColor);
            
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
                 
            model = new AdminViewCandidatesTableModel();
            
            //model.setData(main_controller.candidate_controller.query_all_candidates());

            table = new JTable() {
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    
                    return false;
                }
            };
            /*table.setModel(model);
            table.getColumnModel().getColumn(0).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
            table.getColumnModel().getColumn(0).setMaxWidth(120);
            table.getColumnModel().getColumn(0).setMinWidth(120);
            
            table.setDefaultRenderer(JButton.class, new JTableButtonRenderer());
            table.getColumnModel().getColumn(3).setCellRenderer(new JTableButtonRenderer());
            table.getColumnModel().getColumn(4).setCellRenderer(new JTableButtonRenderer());*/
            
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
                    
                    if (col == 3) {
                       // open EditCandidate
                       System.out.println("Edit candidate: " + table.getValueAt(row, 2) + " " + table.getValueAt(row, 1));
                       System.out.println("Candidate ID: " + model.getCandidateID(row));
                   } else if (col == 4) {
                       // open DeleteCandidate
                       System.out.println("Delete candidate: " + table.getValueAt(row, 2) + " " + table.getValueAt(row, 1));
                       System.out.println("Candidate ID: " + model.getCandidateID(row));
                   }
                }   
    });
            //add button
            btn_add = new JButton("<html><center>ADD CANDIDATE</center></html>");
            btn_add.setBackground(new Color(33,97,140)); //(new Color(33,97,140))
            btn_add.setFont(new Font("CALIBRI", Font.BOLD, 18));
            btn_add.setForeground(Color.WHITE);
            
            btn_edit = new JButton("<html><center>EDIT CANDIDATE</center></html>"); 
            btn_edit.setBackground((new Color(33,97,140))); //(new Color(33,97,140))
            btn_edit.setFont(new Font("CALIBRI", Font.BOLD, 18));
            btn_edit.setForeground(Color.WHITE);
            
            
            
            this.revalidate();
            this.repaint();
            this.add(sp);
            
            this.add(btn_add, BorderLayout.SOUTH);
        }
       
    }
    
    private class JTableButtonRenderer implements TableCellRenderer {        
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Border padding = BorderFactory.createEmptyBorder(50, 50, 50, 50);

            JButton button = (JButton) value;
            
            if (button != null) {
                button.setBorder(BorderFactory.createCompoundBorder(getBorder(), padding));
            }
                
                
            return button;  
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
        jPanel2 = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        firstNameTxt = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        religionTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        birthDateSpinner = new javax.swing.JSpinner();
        sexComboBox = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        degreeTxt = new javax.swing.JTextField();
        universityTxt = new javax.swing.JTextField();
        gradDateSpinner = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jSeparator13 = new javax.swing.JSeparator();
        cancelBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        confirmBtn = new javax.swing.JButton();
        lastNameTxt = new javax.swing.JTextField();
        midInitialTxt = new javax.swing.JTextField();
        browseImageBtn = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        vcpartyTitle5 = new javax.swing.JLabel();
        vcpartySubtitle5 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
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
        jPanel15 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
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
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        campaigntable = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        religionTxt1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        birthDateSpinner1 = new javax.swing.JSpinner();
        sexComboBox1 = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        vcpartyTitle3 = new javax.swing.JLabel();
        vcpartySubtitle3 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        cardViewElec = new javax.swing.JPanel();
        archivecardBG = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        datatable = new javax.swing.JTable();
        archivetitle = new javax.swing.JLabel();
        archivesubtitle = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        addElecBTN = new javax.swing.JButton();
        archiveBTN = new javax.swing.JButton();
        AdminBackground = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(33, 97, 140));

        SidePanel.setBackground(new java.awt.Color(33, 97, 140));
        SidePanel.setPreferredSize(new java.awt.Dimension(100, 100));
        SidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(33, 97, 140));

        panelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ecrmaLogo.png"))); // NOI18N

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 97, 140)));

        firstNameTxt.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        firstNameTxt.setForeground(new java.awt.Color(33, 97, 140));
        firstNameTxt.setText("First name");
        firstNameTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel20.setBackground(new java.awt.Color(33, 97, 140));
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(33, 97, 140));
        jLabel20.setText("Personal Information");

        jTextField12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTextField12.setText("Position");
        jTextField12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(33, 97, 140));
        jLabel10.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel9.add(jLabel10, gridBagConstraints);

        religionTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        religionTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        religionTxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel9.add(religionTxt, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(33, 97, 140));
        jLabel11.setText("Religion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.33;
        jPanel9.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(33, 97, 140));
        jLabel12.setText("Date of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 30);
        jPanel9.add(jLabel12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 5, 30);
        jPanel9.add(birthDateSpinner, gridBagConstraints);

        sexComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 30);
        jPanel9.add(sexComboBox, gridBagConstraints);

        jLabel21.setBackground(new java.awt.Color(33, 97, 140));
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(33, 97, 140));
        jLabel21.setText("Educational Background");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        degreeTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        degreeTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        degreeTxt.setText("Degree");
        degreeTxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 15, 30);
        jPanel10.add(degreeTxt, gridBagConstraints);

        universityTxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        universityTxt.setForeground(new java.awt.Color(33, 97, 140));
        universityTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        universityTxt.setText("University");
        universityTxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 120, 0, 120);
        jPanel10.add(universityTxt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jPanel10.add(gradDateSpinner, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(33, 97, 140));
        jLabel13.setText("Graduation Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        jPanel10.add(jLabel13, gridBagConstraints);

        jLabel22.setBackground(new java.awt.Color(33, 97, 140));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(33, 97, 140));
        jLabel22.setText("Platform");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane5.setViewportView(jTextArea2);

        cancelBtn.setBackground(new java.awt.Color(33, 97, 140));
        cancelBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Cancel");
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setOpaque(true);
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        resetBtn.setBackground(new java.awt.Color(33, 97, 140));
        resetBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        resetBtn.setForeground(new java.awt.Color(255, 255, 255));
        resetBtn.setText("Reset");
        resetBtn.setContentAreaFilled(false);
        resetBtn.setOpaque(true);
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        confirmBtn.setBackground(new java.awt.Color(33, 97, 140));
        confirmBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        confirmBtn.setForeground(new java.awt.Color(255, 255, 255));
        confirmBtn.setText("Confirm");
        confirmBtn.setContentAreaFilled(false);
        confirmBtn.setOpaque(true);
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        lastNameTxt.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lastNameTxt.setForeground(new java.awt.Color(33, 97, 140));
        lastNameTxt.setText("Last name");
        lastNameTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        midInitialTxt.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        midInitialTxt.setForeground(new java.awt.Color(33, 97, 140));
        midInitialTxt.setText("Middle initial");
        midInitialTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        browseImageBtn.setText("Browse Image");
        browseImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseImageBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(browseImageBtn)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(firstNameTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(lastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(midInitialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator13, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cancelBtn)
                                .addGap(18, 18, 18)
                                .addComponent(resetBtn)
                                .addGap(18, 18, 18)
                                .addComponent(confirmBtn))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(592, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(midInitialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(browseImageBtn)))
                .addGap(41, 41, 41)
                .addComponent(jLabel20)
                .addGap(15, 15, 15)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(414, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout cardAddCandidateLayout = new javax.swing.GroupLayout(cardAddCandidate);
        cardAddCandidate.setLayout(cardAddCandidateLayout);
        cardAddCandidateLayout.setHorizontalGroup(
            cardAddCandidateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cardAddCandidateLayout.setVerticalGroup(
            cardAddCandidateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardAddCandidateLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        MainPanel.add(cardAddCandidate, "cardAddCandidate");

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

        cancelBTN.setBackground(new java.awt.Color(33, 97, 140));
        cancelBTN.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        cancelBTN.setForeground(new java.awt.Color(255, 255, 255));
        cancelBTN.setText("Cancel");
        cancelBTN.setContentAreaFilled(false);
        cancelBTN.setOpaque(true);
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

        saveBTN.setBackground(new java.awt.Color(33, 97, 140));
        saveBTN.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        saveBTN.setForeground(new java.awt.Color(255, 255, 255));
        saveBTN.setText("Save");
        saveBTN.setContentAreaFilled(false);
        saveBTN.setOpaque(true);
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
                .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardBGLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(formsubtitle)
                            .addComponent(formtitle)
                            .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(cardBGLayout.createSequentialGroup()
                                    .addComponent(cancelBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(23, 23, 23)
                                    .addComponent(saveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cardBGLayout.createSequentialGroup()
                                    .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel26)
                                        .addComponent(jLabel27))
                                    .addGap(91, 91, 91)
                                    .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(cardBGLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 1130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(448, Short.MAX_VALUE))
        );
        cardBGLayout.setVerticalGroup(
            cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardBGLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(formtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formsubtitle)
                .addGap(34, 34, 34)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
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
                .addGap(78, 78, 78)
                .addGroup(cardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 725, Short.MAX_VALUE))
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

        MainPanel.add(cardAddElec, "cardAddElec");

        cardEdit.setBackground(new java.awt.Color(255, 255, 255));

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
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel17))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel19))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(13, 13, 13)
                                .addComponent(jButton2)
                                .addGap(9, 9, 9)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(397, 397, 397))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel18)
                .addGap(6, 6, 6)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel19)
                .addGap(6, 6, 6)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout cardEditLayout = new javax.swing.GroupLayout(cardEdit);
        cardEdit.setLayout(cardEditLayout);
        cardEditLayout.setHorizontalGroup(
            cardEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardEditLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(cardEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        cardEditLayout.setVerticalGroup(
            cardEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardEditLayout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(414, Short.MAX_VALUE))
        );

        MainPanel.add(cardEdit, "cardEdit");

        cardViewCandidates.setBackground(new java.awt.Color(255, 255, 255));
        cardViewCandidates.setOpaque(false);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        vcpartyTitle4.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 30)); // NOI18N
        vcpartyTitle4.setForeground(new java.awt.Color(33, 82, 117));
        vcpartyTitle4.setText("VIEW ALL CANDIDATES");

        vcpartySubtitle4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        vcpartySubtitle4.setForeground(new java.awt.Color(33, 82, 117));
        vcpartySubtitle4.setText("Subtitle");

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
                .addGap(55, 55, 55)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cardViewCandidatesLayout = new javax.swing.GroupLayout(cardViewCandidates);
        cardViewCandidates.setLayout(cardViewCandidatesLayout);
        cardViewCandidatesLayout.setHorizontalGroup(
            cardViewCandidatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cardViewCandidatesLayout.setVerticalGroup(
            cardViewCandidatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardViewCandidatesLayout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1253, Short.MAX_VALUE))
        );

        MainPanel.add(cardViewCandidates, "cardViewCandidates");

        cardViewDetails.setBackground(new java.awt.Color(255, 255, 255));
        cardViewDetails.setPreferredSize(new java.awt.Dimension(1620, 1080));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 97, 140)));

        jLabel29.setBackground(new java.awt.Color(33, 97, 140));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(33, 97, 140));
        jLabel29.setText("Personal Information");

        jPanel16.setLayout(new java.awt.BorderLayout());

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
        jLabel56.setText("Campaign");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(33, 97, 140));
        jLabel57.setText("NAME");

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

        campaigntable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        campaigntable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Election Period", "Party", "Position", "Platform"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        campaigntable.setFocusable(false);
        campaigntable.setGridColor(new java.awt.Color(33, 82, 117));
        campaigntable.setSelectionBackground(new java.awt.Color(33, 82, 117));
        jScrollPane6.setViewportView(campaigntable);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(33, 97, 140));
        jLabel15.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel13.add(jLabel15, gridBagConstraints);

        religionTxt1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        religionTxt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        religionTxt1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel13.add(religionTxt1, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(33, 97, 140));
        jLabel16.setText("Religion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.33;
        jPanel13.add(jLabel16, gridBagConstraints);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(33, 97, 140));
        jLabel23.setText("Date of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 30);
        jPanel13.add(jLabel23, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 5, 30);
        jPanel13.add(birthDateSpinner1, gridBagConstraints);

        sexComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 30);
        jPanel13.add(sexComboBox1, gridBagConstraints);

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
                        .addGap(76, 76, 76)
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
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jButton16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton17))))
                            .addComponent(jLabel29)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                            .addComponent(jLabel56)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
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
                        .addGap(61, 61, 61)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton16)
                            .addComponent(jButton17))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(461, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout cardViewDetailsLayout = new javax.swing.GroupLayout(cardViewDetails);
        cardViewDetails.setLayout(cardViewDetailsLayout);
        cardViewDetailsLayout.setHorizontalGroup(
            cardViewDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardViewDetailsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(cardViewDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        cardViewDetailsLayout.setVerticalGroup(
            cardViewDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardViewDetailsLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.add(cardViewDetails, "cardViewDetails");

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

        archivetitle.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 30)); // NOI18N
        archivetitle.setForeground(new java.awt.Color(33, 82, 117));
        archivetitle.setText("MANAGE DATA BY ELECTION PERIOD");

        archivesubtitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        archivesubtitle.setForeground(new java.awt.Color(33, 82, 117));
        archivesubtitle.setText("Select the Election Period/s to archive from the data table below.");

        addElecBTN.setBackground(new java.awt.Color(33, 97, 140));
        addElecBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addElecBTN.setForeground(new java.awt.Color(255, 255, 255));
        addElecBTN.setText("Add Election");
        addElecBTN.setContentAreaFilled(false);
        addElecBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addElecBTN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addElecBTN.setOpaque(true);
        addElecBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addElecBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addElecBTNMouseExited(evt);
            }
        });
        addElecBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addElecBTNActionPerformed(evt);
            }
        });

        archiveBTN.setBackground(new java.awt.Color(33, 97, 140));
        archiveBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        archiveBTN.setForeground(new java.awt.Color(255, 255, 255));
        archiveBTN.setText("Archive Election");
        archiveBTN.setContentAreaFilled(false);
        archiveBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        archiveBTN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        archiveBTN.setOpaque(true);
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
                .addGap(116, 116, 116)
                .addGroup(archivecardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(archivecardBGLayout.createSequentialGroup()
                        .addComponent(addElecBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(archiveBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1031, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 482, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, archivecardBGLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 1140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(430, 430, 430))
        );
        archivecardBGLayout.setVerticalGroup(
            archivecardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, archivecardBGLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(archivetitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(archivesubtitle)
                .addGap(32, 32, 32)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(archivecardBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addElecBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(archiveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(798, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cardViewElecLayout = new javax.swing.GroupLayout(cardViewElec);
        cardViewElec.setLayout(cardViewElecLayout);
        cardViewElecLayout.setHorizontalGroup(
            cardViewElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1629, Short.MAX_VALUE)
            .addGroup(cardViewElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cardViewElecLayout.createSequentialGroup()
                    .addComponent(archivecardBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        cardViewElecLayout.setVerticalGroup(
            cardViewElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1454, Short.MAX_VALUE)
            .addGroup(cardViewElecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(archivecardBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.add(cardViewElec, "cardViewElec");

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
        card.show(MainPanel, "cardViewCandidates");
  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cancelBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBTNMouseEntered
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cancelBTNMouseEntered

    private void cancelBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBTNMouseExited
        // TODO add your handling code here:
      
    }//GEN-LAST:event_cancelBTNMouseExited

    private void cancelBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBTNActionPerformed
        // TODO add your handling code here:
          card.show(MainPanel, "cardViewElec");
    }//GEN-LAST:event_cancelBTNActionPerformed

    private void saveBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveBTNMouseEntered
        // TODO add your handling code here:
     
    }//GEN-LAST:event_saveBTNMouseEntered

    private void saveBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveBTNMouseExited
      
    }//GEN-LAST:event_saveBTNMouseExited

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void jTextField67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField67ActionPerformed

    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseClicked
        // TODO add your handling code here:
        card.show(MainPanel, "cardEdit");
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
        card.show(MainPanel, "cardViewCandidates");
    }//GEN-LAST:event_vCandidateMouseClicked

    private void vElectionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vElectionMouseClicked
        // TODO add your handling code here:
        card.show(MainPanel, "cardViewElec");
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

    private void vMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vMenuMouseClicked
        // TODO add your handling code here:
        menu = new AdminMenu();
        menu.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_vMenuMouseClicked

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        card.show(MainPanel, "cardViewCandidates");
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_resetBtnActionPerformed

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
//        if(!validate_input())
//        {
//            // TODO: possibly change message if there is a better message/design
//            JOptionPane.showMessageDialog(null,
//                "Invalid information entered on some fields",
//                "Add Candidate failed!",
//                JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        String first_name  = firstNameTxt.getText();
//        String last_name   = lastNameTxt.getText();
//        char mid_initial   = midInitialTxt.getText().charAt(0);
//        String religion    = religionTxt.getText();
//        Date birth_date    = (Date) birthDateSpinner.getValue();
//
//        String degree      = degreeTxt.getText();
//        String univeristy  = universityTxt.getText();
//        Date grad_date     = (Date) gradDateSpinner.getValue();
//
//        candidate_info = new Candidate();
//        candidate_info.set_birth_date(birth_date);
//        candidate_info.set_first_name(first_name);
//        candidate_info.set_last_name(last_name);
//        candidate_info.set_middle_initial(mid_initial);
//        candidate_info.set_religion(religion);
//        candidate_info.set_degree(degree);
//        candidate_info.set_university(univeristy);
//        candidate_info.set_grad_date(grad_date);
//        candidate_info.set_sex((String) sexComboBox.getSelectedItem());
//        candidate_info.set_image_path(image_path);
//
//        if(form_listener != null)
//        {
//            FormEvent form_event = new FormEvent(this);
//            form_event.setCandidate(candidate_info);
//            form_listener.formEventOccurred(form_event);
//        }
        System.out.println("added candidate");
        
        card.show(MainPanel, "cardViewCandidates");
    }//GEN-LAST:event_confirmBtnActionPerformed

    private void browseImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseImageBtnActionPerformed
//        int return_val = image_chooser.showDialog(null, "Select");
//
//        if(return_val == JFileChooser.APPROVE_OPTION)
//        {
//            File selected_file = image_chooser.getSelectedFile();
//            try {
//                image_path = "img/" + selected_file.getName();
//
//                Files.copy(selected_file.toPath(),
//                    new File(image_path).toPath(),
//                    StandardCopyOption.REPLACE_EXISTING);
//
//                set_image_label(image_path);
//
//            } catch (IOException ex) {
//                Logger.getLogger(AddGUI.class.getName()).log(Level.ALL.SEVERE, null, ex);
//            }
//        }
    }//GEN-LAST:event_browseImageBtnActionPerformed

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        // TODO add your handling code here:
        login = new Frame_Login();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LogoutMouseClicked

    private void addElecBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addElecBTNMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_addElecBTNMouseEntered

    private void addElecBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addElecBTNMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_addElecBTNMouseExited

    private void addElecBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addElecBTNActionPerformed
        // TODO add your handling code here:
        card.show(MainPanel, "cardAddElec");

    }//GEN-LAST:event_addElecBTNActionPerformed

    private void archiveBTNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archiveBTNMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_archiveBTNMouseEntered

    private void archiveBTNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archiveBTNMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_archiveBTNMouseExited

    private void archiveBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveBTNActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(AdminMainContentArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminMainContentArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminMainContentArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminMainContentArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
         
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                
                new AdminMainContentArea().setVisible(true);    
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminBackground;
    private javax.swing.JPanel Logout;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JPanel SidePanelContent;
    private javax.swing.JButton addElecBTN;
    private javax.swing.JButton archiveBTN;
    private javax.swing.JPanel archivecardBG;
    private javax.swing.JLabel archivesubtitle;
    private javax.swing.JLabel archivetitle;
    private javax.swing.JSpinner birthDateSpinner;
    private javax.swing.JSpinner birthDateSpinner1;
    private javax.swing.JButton browseImageBtn;
    private javax.swing.JTable campaigntable;
    private javax.swing.JButton cancelBTN;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel cardAddCandidate;
    private javax.swing.JPanel cardAddElec;
    private javax.swing.JPanel cardBG;
    private javax.swing.JPanel cardEdit;
    private javax.swing.JPanel cardViewCandidates;
    private javax.swing.JPanel cardViewDetails;
    private javax.swing.JPanel cardViewElec;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JTable datatable;
    private javax.swing.JTextField degreeTxt;
    private javax.swing.JTextField firstNameTxt;
    private javax.swing.JLabel formsubtitle;
    private javax.swing.JLabel formtitle;
    private javax.swing.JSpinner gradDateSpinner;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField lastNameTxt;
    private javax.swing.JTextField midInitialTxt;
    private javax.swing.JLabel panelLogo;
    private javax.swing.JTextField religionTxt;
    private javax.swing.JTextField religionTxt1;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton saveBTN;
    private javax.swing.JComboBox<String> sexComboBox;
    private javax.swing.JComboBox<String> sexComboBox1;
    private javax.swing.JLabel sp1LBL;
    private javax.swing.JLabel sp2LBL2;
    private javax.swing.JLabel sp2LBL3;
    private javax.swing.JLabel sp2LBL4;
    private javax.swing.JTextField universityTxt;
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
