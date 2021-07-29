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
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Admin
 */
public class AdminViewCandidates extends JPanel{
    
    Color bgColor;
    
    North north;
    West west;
    Insets westInsets;
    Center center;

    public AdminViewCandidates(){
        bgColor = new Color(255,255,255);
        
        this.setLayout(new BorderLayout());
        north = new North();
        north.setBorder(new EmptyBorder(10,10,10,10));
        westInsets = new Insets(5,5,5,5);
        west = new West();
        west.setBorder(new CompoundBorder(new EmptyBorder(westInsets),
                    new MatteBorder(0, 0, 0, 0, Color.black))                );
        center = new Center();
        
        this.add(north, BorderLayout.NORTH);
        this.add(west, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);
        
        this.setBackground(bgColor);
    }
    
    class North extends JPanel{
        North() {
            //add header here
        }
    }
    
    //side panel
    class West extends JPanel{
        JPanel center;
        JLabel viewAllCand;
        JLabel viewElections;
        JLabel mainMenu;
        JLabel logOut;
        
        West() {
            this.setOpaque(true);
            this.setPreferredSize(new Dimension(300,250));
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(33, 97, 140));
            
            center = new JPanel(new GridLayout(12, 1, 0, 0));
            center.setMaximumSize(new Dimension(280, 10));
            center.setPreferredSize(new Dimension(280, 10));
            center.setBackground(new Color(33, 97, 140));
            center.setForeground(Color.WHITE);
        
            //view all candidates
            ImageIcon People = new ImageIcon("C:\\Users\\Admin\\Documents\\GitHub\\ecrma\\ECRMA9\\src\\Icons\\tao.png");
            viewAllCand = new JLabel("VIEW ALL CANDIDATES", People, SwingConstants.LEFT);
            viewAllCand.setForeground(Color.WHITE);
            viewAllCand.setFont(new Font("BERLIN SANS FB DEMI", Font.BOLD, 16));
                    
            //view candidates by party
            ImageIcon Party = new ImageIcon("C:\\Users\\Admin\\Documents\\GitHub\\ecrma\\ECRMA9\\src\\Icons\\EYE.png");
            viewElections = new JLabel("VIEW ELECTIONS", Party, SwingConstants.LEFT);
            viewElections.setForeground(Color.WHITE);
            viewElections.setFont(new Font("BERLIN SANS FB DEMI", Font.BOLD, 16));
            
            //go back to main menu
            ImageIcon Menu = new ImageIcon("C:\\Users\\Admin\\Documents\\GitHub\\ecrma\\ECRMA9\\src\\Icons\\Home 2.png");
            mainMenu = new JLabel("BACK TO MAIN MENU", Menu, SwingConstants.LEFT);
            mainMenu.setForeground(Color.WHITE);
            mainMenu.setFont(new Font("BERLIN SANS FB DEMI", Font.BOLD, 16));
            
            //logout
            ImageIcon logout = new ImageIcon("C:\\Users\\Admin\\Documents\\GitHub\\ecrma\\ECRMA9\\src\\Icons\\exit.png");
            logOut = new JLabel("LOGOUT", logout, SwingConstants.LEFT);
            logOut.setForeground(Color.WHITE);
            logOut.setFont(new Font("BERLIN SANS FB DEMI", Font.BOLD, 16));

            center.setOpaque(false);
            
            center.add(viewAllCand);
            center.add(viewElections);
            center.add(mainMenu);
            center.add(logOut);
            
            this.add(center, BorderLayout.CENTER);
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
        JButton btn_add;
        
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
            
            //add button
            btn_add = new JButton("<html><center>ADD CANDIDATES</center></html>");
            btn_add.setBackground(new Color(51,55,69));
            btn_add.setFont(new Font("CALIBRI", Font.BOLD, 18));
            btn_add.setForeground(Color.WHITE);
            
            
            this.revalidate();
            this.repaint();
            this.add(sp);
            this.add(btn_add, BorderLayout.SOUTH);
        }
        
    }
    
}
