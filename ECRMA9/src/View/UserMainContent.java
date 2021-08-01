/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
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
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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
public class UserMainContent extends javax.swing.JFrame {
    
    UserMenu menu;
    Frame_Login login;
    
    UserCard myPanel = new UserCard();
    UserCard1 myPanel1 = new UserCard1();
    UserCard3 myPanel2 = new UserCard3();

    private Color hoverMENU = new Color(33,82,117);
    private Color byeMENU = new Color(33,97,140);
    
    /**
     * Creates new form UserCard2
     */
    public UserMainContent() {
        initComponents(); 
        setSize(1920, 1080);
        this.setTitle("Election Candidates Record Management");
        cardViewAll.add(myPanel);
        ByParty.add(myPanel1);
        ByPosition.add(myPanel2);
    }
    
    
public class UserCard extends JPanel{
    Color bgColor;
    
    North north;
    West west;
    Insets westInsets;
    Center center;

    public UserCard() {
        bgColor = new Color(255,255,255);
      
        this.setLayout(new BorderLayout());
        north = new North();
        north.setBorder(new EmptyBorder(10,10,10,10));
        westInsets = new Insets(5,5,5,5);
        west = new West();
        west.setBorder(new CompoundBorder(new EmptyBorder(westInsets),
                    new MatteBorder(0, 0, 0, 0, Color.black))                );

        center = new Center();    
        
        //this.add(north, BorderLayout.NORTH);
        center.add(west, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        
        center.setBackground(bgColor);
        
        pack();
        
        setSize(1300, 880);
        this.setVisible(true);
    }
    
    //add header here
    class North extends JPanel { 
            
//        JLabel title;
        
        North() {
        
//            this.setLayout(new BorderLayout());
//            
//            title = new JLabel("View Candidates");
//            title.setFont(new Font("CALIBRI", Font.PLAIN, 24));
//            title.setForeground(Color.white);
//            this.add(title, BorderLayout.WEST);
//            this.setPreferredSize(new Dimension(50,80));
//            this.setBackground(Color.decode("#21618C"));
            
        }
    }
    
    class West extends JPanel{
       
        JPanel center;
        JPanel midCenter;
        JTextField candNameJTF;
        JPanel candNameJP;
        JList partyJL;
        JScrollPane partyJSP;
        JPanel partyJP;
        JList positionJL;
        JScrollPane positionJSP;
        JPanel positionJP;
        
        JLabel searchBy;
        ButtonGroup searchesBG;
        JRadioButton[] searches;
        
        JSeparator separator;
        JLabel viewAllCand;
        JLabel viewByParty;
        JLabel viewByPosition;
        JLabel mainMenu;
        JLabel logOut;

        
        JLabel backButton;

        
        West() {
            this.setOpaque(true);
            this.setPreferredSize(new Dimension(300,200));
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(255, 255, 255));
            
            
            center = new JPanel();
            center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
            
            candNameJTF = new JTextField();
            candNameJTF.setFont(new Font("CALIBRI", Font.PLAIN, 16));
            candNameJP = panelize(candNameJTF,-1,25,0,0);
            partyJL = new JList(new Object[] {"PartyA","PartyB","PartyC","PartyD"});          
            partyJL.setFont(new Font("CALIBRI", Font.PLAIN, 16));
            partyJL.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            partyJSP = new JScrollPane(partyJL);
            partyJP = panelize(partyJSP,-1,100,0,0);
            partyJP.setVisible(false);
            positionJL = new JList(new Object[] {"PositionA","PositionB","PositionC","PositionD"});
            positionJL.setFont(new Font("CALIBRI", Font.PLAIN, 16));
            positionJL.setBorder(BorderFactory.createLineBorder(Color.lightGray));            
            positionJSP = new JScrollPane(positionJL);
            positionJP = panelize(positionJSP,-1,100,0,0);            
            positionJP.setVisible(false);
            searchBy = new JLabel("Search By:");
            searchBy.setForeground(new Color(33, 97, 140));
            searchBy.setFont(new Font("CALIBRI", Font.PLAIN, 18));
            
            String[] searchesSTR = new String[] {"Candidate Name", "Party", "Position"};
            searchesBG = new ButtonGroup();
            searches = new JRadioButton[3];
            for(int i = 0; i < 3; i++) {
                searches[i] = new JRadioButton(searchesSTR[i]);
                searches[i].setFont(new Font("CALIBRI", Font.PLAIN, 16));
                searches[i].setForeground(new Color(33, 97, 140));
                searches[i].setOpaque(false);
                searchesBG.add(searches[i]);               
            }
            
            searches[0].setSelected(true);
            searches[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    candNameJP.setVisible(true);
                    partyJP.setVisible(false);
                    positionJP.setVisible(false);
                    refresh();
                }
            });

            searches[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    candNameJP.setVisible(false);
                    partyJP.setVisible(true);
                    positionJP.setVisible(false);
                    refresh();
                }
            });
            
            searches[2].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    candNameJP.setVisible(false);
                    partyJP.setVisible(false);
                    positionJP.setVisible(true);
                    refresh();
                }
            });
            
            
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            
            ///////////////////////////////////////////////////////
        
            center.add(candNameJP);
            center.add(partyJP);
            center.add(positionJP);
           
            center.add(panelize(searchBy));
            for(int i = 0; i < 3; i++) {
                center.add(panelize(searches[i]));
            }
            
            center.add(panelize(separator,-1,2,0,0));
            center.setOpaque(false);
            
           
   
            this.add(center, BorderLayout.EAST);
   
             
        }
        
        private void refresh() {
            this.center.revalidate();
            this.center.repaint();
        }
        
        private JPanel panelize(Component cmp) {
            FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
            layout.setHgap(0);
            layout.setVgap(0);
            
            JPanel panel = new JPanel(layout);
            panel.add(cmp);
            panel.setOpaque(false);
            panel.setMaximumSize(new Dimension((this.getPreferredSize().width - westInsets.left - westInsets.right),cmp.getPreferredSize().height));
            return panel;
        }

        private JPanel panelize(Component cmp, int x, int y, int gapx, int gapy) {
            Insets parentInset = this.getInsets();
            
            FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
            layout.setHgap(gapx);
            
            layout.setVgap(gapy);
            
            JPanel panel = new JPanel(layout);
            
            if(x != -1) {
                if( y != -1) {
                    cmp.setMaximumSize(new Dimension(x,y));
                } else {
                    cmp.setMaximumSize(new Dimension(x,cmp.getPreferredSize().height));
                }
            } else if ( y != -1 ) {
                cmp.setMaximumSize(new Dimension((this.getPreferredSize().width - westInsets.left*2 - westInsets.right*1),y));
            }
            cmp.setPreferredSize(cmp.getMaximumSize());
            panel.add(cmp);
            panel.setOpaque(false);
            panel.setMaximumSize(new Dimension(this.getPreferredSize().width - westInsets.left - westInsets.right - gapx,cmp.getMaximumSize().height + gapy*2));
            panel.setPreferredSize(panel.getMaximumSize());
            return panel;
        }


    }
    
    class Center extends JPanel {
        JTable table;

        Center() {
            
            this.setLayout(new BorderLayout());
            this.setBorder(new EmptyBorder(60,60,60,60));
            this.setOpaque(true);
            this.setBackground(new Color(33, 97, 140));

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
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    return false;
                }
            };
            table.setModel(model);
            table.getColumnModel().getColumn(0).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
            
            table.getColumnModel().getColumn(0).setMaxWidth(120);
            table.getColumnModel().getColumn(0).setMinWidth(120);
            
            table.setRowHeight(120);
            
            
            table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN,24));
            table.setFont(new Font("CALIBRI", Font.PLAIN, 18));

            JScrollPane sp = new JScrollPane(table);
            
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.rowAtPoint(evt.getPoint());
                    int col = table.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                         cardViewAll.setVisible(false);
                         remove(cardViewAll);

                        cardDetails.setVisible(true);
                        cardDetails.repaint();
                        cardDetails.revalidate();
                        setSize(new Dimension(1720,1080));
                    }
                }   
    });
         
            this.revalidate();
            this.repaint();
            this.add(sp);
        }
        }
        
    }
     //ByParty
    public class UserCard1 extends JPanel{
    Color bgColor;
    
    North north;
    West west;
    Insets westInsets;
    Center center;

    public UserCard1() {
        bgColor = new Color(255,255,255);
      
        this.setLayout(new BorderLayout());
        north = new North();
        north.setBorder(new EmptyBorder(10,10,10,10));
        westInsets = new Insets(5,5,5,5);
        west = new West();
        west.setBorder(new CompoundBorder(new EmptyBorder(westInsets),
                    new MatteBorder(0, 0, 0, 0, Color.black))                );

        center = new Center();    
        
        //this.add(north, BorderLayout.NORTH);
        center.add(west, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        
        center.setBackground(bgColor);
        
        pack();
        
        setSize(1300, 880);
        this.setVisible(true);
    }
    
    //add header here
    class North extends JPanel { 
            
//        JLabel title;
        
        North() {
        
//            this.setLayout(new BorderLayout());
//            
//            title = new JLabel("View Candidates");
//            title.setFont(new Font("CALIBRI", Font.PLAIN, 24));
//            title.setForeground(Color.white);
//            this.add(title, BorderLayout.WEST);
//            this.setPreferredSize(new Dimension(50,80));
//            this.setBackground(Color.decode("#21618C"));
            
        }
    }
    
    class West extends JPanel{
       
        JPanel center;
        JPanel midCenter;
        JTextField candNameJTF;
        JPanel candNameJP;
        JList partyJL;
        JScrollPane partyJSP;
        JPanel partyJP;
        JList positionJL;
        JScrollPane positionJSP;
        JPanel positionJP;
        
        JLabel searchBy;
        ButtonGroup searchesBG;
        JRadioButton[] searches;
        
        JSeparator separator;
        JLabel viewAllCand;
        JLabel viewByParty;
        JLabel viewByPosition;
        JLabel mainMenu;
        JLabel logOut;

        
        JLabel backButton;

        
        West() {
            this.setOpaque(true);
            this.setPreferredSize(new Dimension(300,200));
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(255, 255, 255));
            
            
            center = new JPanel();
            center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
            
            candNameJTF = new JTextField();
            candNameJTF.setFont(new Font("CALIBRI", Font.PLAIN, 16));
            candNameJP = panelize(candNameJTF,-1,25,0,0);
            partyJL = new JList(new Object[] {"PartyA","PartyB","PartyC","PartyD"});          
            partyJL.setFont(new Font("CALIBRI", Font.PLAIN, 16));
            partyJL.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            partyJSP = new JScrollPane(partyJL);
            partyJP = panelize(partyJSP,-1,100,0,0);
            partyJP.setVisible(false);
            positionJL = new JList(new Object[] {"PositionA","PositionB","PositionC","PositionD"});
            positionJL.setFont(new Font("CALIBRI", Font.PLAIN, 16));
            positionJL.setBorder(BorderFactory.createLineBorder(Color.lightGray));            
            positionJSP = new JScrollPane(positionJL);
            positionJP = panelize(positionJSP,-1,100,0,0);            
            positionJP.setVisible(false);
            searchBy = new JLabel("Search By:");
            searchBy.setForeground(new Color(33, 97, 140));
            searchBy.setFont(new Font("CALIBRI", Font.PLAIN, 18));
            
            String[] searchesSTR = new String[] {"Candidate Name", "Party", "Position"};
            searchesBG = new ButtonGroup();
            searches = new JRadioButton[3];
            for(int i = 0; i < 3; i++) {
                searches[i] = new JRadioButton(searchesSTR[i]);
                searches[i].setFont(new Font("CALIBRI", Font.PLAIN, 16));
                searches[i].setForeground(new Color(33, 97, 140));
                searches[i].setOpaque(false);
                searchesBG.add(searches[i]);               
            }
            
            searches[0].setSelected(true);
            searches[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    candNameJP.setVisible(true);
                    partyJP.setVisible(false);
                    positionJP.setVisible(false);
                    refresh();
                }
            });

            searches[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    candNameJP.setVisible(false);
                    partyJP.setVisible(true);
                    positionJP.setVisible(false);
                    refresh();
                }
            });
            
            searches[2].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    candNameJP.setVisible(false);
                    partyJP.setVisible(false);
                    positionJP.setVisible(true);
                    refresh();
                }
            });
            
            
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            
            ///////////////////////////////////////////////////////
        
            center.add(candNameJP);
            center.add(partyJP);
            center.add(positionJP);
           
            center.add(panelize(searchBy));
            for(int i = 0; i < 3; i++) {
                center.add(panelize(searches[i]));
            }
            
            center.add(panelize(separator,-1,2,0,0));
            center.setOpaque(false);
            
           
   
            this.add(center, BorderLayout.EAST);
   
             
        }
        
        private void refresh() {
            this.center.revalidate();
            this.center.repaint();
        }
        
        private JPanel panelize(Component cmp) {
            FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
            layout.setHgap(0);
            layout.setVgap(0);
            
            JPanel panel = new JPanel(layout);
            panel.add(cmp);
            panel.setOpaque(false);
            panel.setMaximumSize(new Dimension((this.getPreferredSize().width - westInsets.left - westInsets.right),cmp.getPreferredSize().height));
            return panel;
        }

        private JPanel panelize(Component cmp, int x, int y, int gapx, int gapy) {
            Insets parentInset = this.getInsets();
            
            FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
            layout.setHgap(gapx);
            
            layout.setVgap(gapy);
            
            JPanel panel = new JPanel(layout);
            
            if(x != -1) {
                if( y != -1) {
                    cmp.setMaximumSize(new Dimension(x,y));
                } else {
                    cmp.setMaximumSize(new Dimension(x,cmp.getPreferredSize().height));
                }
            } else if ( y != -1 ) {
                cmp.setMaximumSize(new Dimension((this.getPreferredSize().width - westInsets.left*2 - westInsets.right*1),y));
            }
            cmp.setPreferredSize(cmp.getMaximumSize());
            panel.add(cmp);
            panel.setOpaque(false);
            panel.setMaximumSize(new Dimension(this.getPreferredSize().width - westInsets.left - westInsets.right - gapx,cmp.getMaximumSize().height + gapy*2));
            panel.setPreferredSize(panel.getMaximumSize());
            return panel;
        }


    }
    
    class Center extends JPanel {
        JTable table;

        Center() {
            
            this.setLayout(new BorderLayout());
            this.setBorder(new EmptyBorder(60,60,60,60));
            this.setOpaque(true);
            this.setBackground(new Color(33, 97, 140));

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
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    return false;
                }
            };
            table.setModel(model);
            table.getColumnModel().getColumn(0).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
            
            table.getColumnModel().getColumn(0).setMaxWidth(120);
            table.getColumnModel().getColumn(0).setMinWidth(120);
            
            table.setRowHeight(120);
            
            
            table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN,24));
            table.setFont(new Font("CALIBRI", Font.PLAIN, 18));

            JScrollPane sp = new JScrollPane(table);
            
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.rowAtPoint(evt.getPoint());
                    int col = table.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                         ByParty.setVisible(false);
                         remove(ByParty);

                        cardDetails.setVisible(true);
                        cardDetails.repaint();
                        cardDetails.revalidate();
                        setSize(new Dimension(1720,1080));
                    }
                }   
    });
         
            this.revalidate();
            this.repaint();
            this.add(sp);
           
        }
    }
    }
    
    
     //ByPosition
    public class UserCard3 extends JPanel{
    Color bgColor;
    
    North north;
    West west;
    Insets westInsets;
    Center center;

    public UserCard3() {
        bgColor = new Color(255,255,255);
      
        this.setLayout(new BorderLayout());
        north = new North();
        north.setBorder(new EmptyBorder(10,10,10,10));
        westInsets = new Insets(5,5,5,5);
        west = new West();
        west.setBorder(new CompoundBorder(new EmptyBorder(westInsets),
                    new MatteBorder(0, 0, 0, 0, Color.black))                );

        center = new Center();    
        
        //this.add(north, BorderLayout.NORTH);
        center.add(west, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        
        center.setBackground(bgColor);
        
        pack();
        
        setSize(1300, 880);
        this.setVisible(true);
    }
    
    //add header here
    class North extends JPanel { 
            
//        JLabel title;
        
        North() {
        
//            this.setLayout(new BorderLayout());
//            
//            title = new JLabel("View Candidates");
//            title.setFont(new Font("CALIBRI", Font.PLAIN, 24));
//            title.setForeground(Color.white);
//            this.add(title, BorderLayout.WEST);
//            this.setPreferredSize(new Dimension(50,80));
//            this.setBackground(Color.decode("#21618C"));
            
        }
    }
    
    class West extends JPanel{
       
        JPanel center;
        JPanel midCenter;
        JTextField candNameJTF;
        JPanel candNameJP;
        JList partyJL;
        JScrollPane partyJSP;
        JPanel partyJP;
        JList positionJL;
        JScrollPane positionJSP;
        JPanel positionJP;
        
        JLabel searchBy;
        ButtonGroup searchesBG;
        JRadioButton[] searches;
        
        JSeparator separator;
        JLabel viewAllCand;
        JLabel viewByParty;
        JLabel viewByPosition;
        JLabel mainMenu;
        JLabel logOut;

        
        JLabel backButton;

        
        West() {
            this.setOpaque(true);
            this.setPreferredSize(new Dimension(300,200));
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(255, 255, 255));
            
            
            center = new JPanel();
            center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
            
            candNameJTF = new JTextField();
            candNameJTF.setFont(new Font("CALIBRI", Font.PLAIN, 16));
            candNameJP = panelize(candNameJTF,-1,25,0,0);
            partyJL = new JList(new Object[] {"PartyA","PartyB","PartyC","PartyD"});          
            partyJL.setFont(new Font("CALIBRI", Font.PLAIN, 16));
            partyJL.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            partyJSP = new JScrollPane(partyJL);
            partyJP = panelize(partyJSP,-1,100,0,0);
            partyJP.setVisible(false);
            positionJL = new JList(new Object[] {"PositionA","PositionB","PositionC","PositionD"});
            positionJL.setFont(new Font("CALIBRI", Font.PLAIN, 16));
            positionJL.setBorder(BorderFactory.createLineBorder(Color.lightGray));            
            positionJSP = new JScrollPane(positionJL);
            positionJP = panelize(positionJSP,-1,100,0,0);            
            positionJP.setVisible(false);
            searchBy = new JLabel("Search By:");
            searchBy.setForeground(new Color(33, 97, 140));
            searchBy.setFont(new Font("CALIBRI", Font.PLAIN, 18));
            
            String[] searchesSTR = new String[] {"Candidate Name", "Party", "Position"};
            searchesBG = new ButtonGroup();
            searches = new JRadioButton[3];
            for(int i = 0; i < 3; i++) {
                searches[i] = new JRadioButton(searchesSTR[i]);
                searches[i].setFont(new Font("CALIBRI", Font.PLAIN, 16));
                searches[i].setForeground(new Color(33, 97, 140));
                searches[i].setOpaque(false);
                searchesBG.add(searches[i]);               
            }
            
            searches[0].setSelected(true);
            searches[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    candNameJP.setVisible(true);
                    partyJP.setVisible(false);
                    positionJP.setVisible(false);
                    refresh();
                }
            });

            searches[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    candNameJP.setVisible(false);
                    partyJP.setVisible(true);
                    positionJP.setVisible(false);
                    refresh();
                }
            });
            
            searches[2].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    candNameJP.setVisible(false);
                    partyJP.setVisible(false);
                    positionJP.setVisible(true);
                    refresh();
                }
            });
            
            
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            
            ///////////////////////////////////////////////////////
        
            center.add(candNameJP);
            center.add(partyJP);
            center.add(positionJP);
           
            center.add(panelize(searchBy));
            for(int i = 0; i < 3; i++) {
                center.add(panelize(searches[i]));
            }
            
            center.add(panelize(separator,-1,2,0,0));
            center.setOpaque(false);
            
           
   
            this.add(center, BorderLayout.EAST);
   
             
        }
        
        private void refresh() {
            this.center.revalidate();
            this.center.repaint();
        }
        
        private JPanel panelize(Component cmp) {
            FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
            layout.setHgap(0);
            layout.setVgap(0);
            
            JPanel panel = new JPanel(layout);
            panel.add(cmp);
            panel.setOpaque(false);
            panel.setMaximumSize(new Dimension((this.getPreferredSize().width - westInsets.left - westInsets.right),cmp.getPreferredSize().height));
            return panel;
        }

        private JPanel panelize(Component cmp, int x, int y, int gapx, int gapy) {
            Insets parentInset = this.getInsets();
            
            FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
            layout.setHgap(gapx);
            
            layout.setVgap(gapy);
            
            JPanel panel = new JPanel(layout);
            
            if(x != -1) {
                if( y != -1) {
                    cmp.setMaximumSize(new Dimension(x,y));
                } else {
                    cmp.setMaximumSize(new Dimension(x,cmp.getPreferredSize().height));
                }
            } else if ( y != -1 ) {
                cmp.setMaximumSize(new Dimension((this.getPreferredSize().width - westInsets.left*2 - westInsets.right*1),y));
            }
            cmp.setPreferredSize(cmp.getMaximumSize());
            panel.add(cmp);
            panel.setOpaque(false);
            panel.setMaximumSize(new Dimension(this.getPreferredSize().width - westInsets.left - westInsets.right - gapx,cmp.getMaximumSize().height + gapy*2));
            panel.setPreferredSize(panel.getMaximumSize());
            return panel;
        }


    }
    
    class Center extends JPanel {
        JTable table;

        Center() {
            
            this.setLayout(new BorderLayout());
            this.setBorder(new EmptyBorder(60,60,60,60));
            this.setOpaque(true);
            this.setBackground(new Color(33, 97, 140));

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
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    return false;
                }
            };
            table.setModel(model);
            table.getColumnModel().getColumn(0).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
            
            table.getColumnModel().getColumn(0).setMaxWidth(120);
            table.getColumnModel().getColumn(0).setMinWidth(120);
            
            table.setRowHeight(120);
            
            
            table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN,24));
            table.setFont(new Font("CALIBRI", Font.PLAIN, 18));

            JScrollPane sp = new JScrollPane(table);
            
             table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.rowAtPoint(evt.getPoint());
                    int col = table.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                         ByPosition.setVisible(false);
                         remove(ByPosition);

                        cardDetails.setVisible(true);
                        cardDetails.repaint();
                        cardDetails.revalidate();
                        setSize(new Dimension(1720,1080));
                    }
                }   
    });
         
            this.revalidate();
            this.repaint();
            this.add(sp);
           
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

        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        jPanel2 = new javax.swing.JPanel();
        ByParty = new javax.swing.JPanel();
        ByPosition = new javax.swing.JPanel();
        cardDetails = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cardViewAll = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(33, 97, 140));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 100));

        jPanel14.setBackground(new java.awt.Color(33, 97, 140));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\GitHub\\ecrma\\ecrmaLogo.png")); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addContainerGap())
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
            .addComponent(sp1LBL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
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
            .addComponent(sp1LBL1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
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
        sp1LBL2.setText("VIEW CANDIDATES BY PARTY");

        javax.swing.GroupLayout vPositionLayout = new javax.swing.GroupLayout(vPosition);
        vPosition.setLayout(vPositionLayout);
        vPositionLayout.setHorizontalGroup(
            vPositionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vPositionLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(sp1LBL2)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        vPositionLayout.setVerticalGroup(
            vPositionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp1LBL2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
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
            .addComponent(sp2LBL3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
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
            .addComponent(sp2LBL4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );

        jPanel13.add(Logout);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout ByPartyLayout = new javax.swing.GroupLayout(ByParty);
        ByParty.setLayout(ByPartyLayout);
        ByPartyLayout.setHorizontalGroup(
            ByPartyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        ByPartyLayout.setVerticalGroup(
            ByPartyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1102, Short.MAX_VALUE)
        );

        jPanel2.add(ByParty, "card3");

        javax.swing.GroupLayout ByPositionLayout = new javax.swing.GroupLayout(ByPosition);
        ByPosition.setLayout(ByPositionLayout);
        ByPositionLayout.setHorizontalGroup(
            ByPositionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        ByPositionLayout.setVerticalGroup(
            ByPositionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1102, Short.MAX_VALUE)
        );

        jPanel2.add(ByPosition, "card2");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 97, 140)));

        jLabel21.setBackground(new java.awt.Color(33, 97, 140));
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(33, 97, 140));
        jLabel21.setText("Personal Information");

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
        gridBagConstraints.insets = new java.awt.Insets(11, 32, 0, 0);
        jPanel7.add(jTextField2, gridBagConstraints);

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
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
        jPanel7.add(jTextField4, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(33, 97, 140));
        jLabel7.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 533, 0, 0);
        jPanel7.add(jLabel7, gridBagConstraints);

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 32, 0, 0);
        jPanel7.add(jTextField5, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(33, 97, 140));
        jLabel8.setText("Party List");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 108, 0, 0);
        jPanel7.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(33, 97, 140));
        jLabel9.setText("Date of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 85, 0, 0);
        jPanel7.add(jLabel9, gridBagConstraints);

        jTextField12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 201;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 445, 0, 24);
        jPanel7.add(jTextField12, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(33, 97, 140));
        jLabel13.setText("Religion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 518, 0, 0);
        jPanel7.add(jLabel13, gridBagConstraints);

        jLabel22.setBackground(new java.awt.Color(33, 97, 140));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(33, 97, 140));
        jLabel22.setText("Educational Background");

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

        jLabel23.setBackground(new java.awt.Color(33, 97, 140));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(33, 97, 140));
        jLabel23.setText("Platform");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(33, 97, 140));
        jLabel1.setText("NAME");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Position");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Party");

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane2.setToolTipText("");
        jScrollPane2.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                            .addComponent(jSeparator5)
                            .addComponent(jSeparator4)
                            .addComponent(jLabel22)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)))
                            .addComponent(jLabel21)
                            .addComponent(jLabel23))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel21))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cardDetailsLayout = new javax.swing.GroupLayout(cardDetails);
        cardDetails.setLayout(cardDetailsLayout);
        cardDetailsLayout.setHorizontalGroup(
            cardDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cardDetailsLayout.setVerticalGroup(
            cardDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(cardDetails, "card5");

        javax.swing.GroupLayout cardViewAllLayout = new javax.swing.GroupLayout(cardViewAll);
        cardViewAll.setLayout(cardViewAllLayout);
        cardViewAllLayout.setHorizontalGroup(
            cardViewAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        cardViewAllLayout.setVerticalGroup(
            cardViewAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1102, Short.MAX_VALUE)
        );

        jPanel2.add(cardViewAll, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void vCandidateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vCandidateMouseClicked
        // TODO add your handling code here:
        cardViewAll.setVisible(false);
        ByParty.setVisible(false);
       
        this.remove(cardViewAll);
        this.remove(ByParty);

        cardViewAll.setVisible(true);
        cardViewAll.repaint();
        cardViewAll.revalidate();
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
        cardViewAll.setVisible(false);
        ByPosition.setVisible(false);
       
        this.remove(cardViewAll);
        this.remove(ByParty);

        ByParty.setVisible(true);
        ByParty.repaint();
        ByParty.revalidate();
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
        cardViewAll.setVisible(false);
        ByParty.setVisible(false);
       
        this.remove(cardViewAll);
        this.remove(ByParty);

        ByPosition.setVisible(true);
        ByPosition.repaint();
        ByPosition.revalidate();
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
            java.util.logging.Logger.getLogger(UserMainContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserMainContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserMainContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMainContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserMainContent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ByParty;
    private javax.swing.JPanel ByPosition;
    private javax.swing.JPanel Logout;
    private javax.swing.JPanel cardDetails;
    private javax.swing.JPanel cardViewAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel sp1LBL;
    private javax.swing.JLabel sp1LBL1;
    private javax.swing.JLabel sp1LBL2;
    private javax.swing.JLabel sp2LBL3;
    private javax.swing.JLabel sp2LBL4;
    private javax.swing.JPanel vCandidate;
    private javax.swing.JPanel vMenu;
    private javax.swing.JPanel vParty;
    private javax.swing.JPanel vPosition;
    // End of variables declaration//GEN-END:variables
}
