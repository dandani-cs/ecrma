/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MainController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


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
    MainController main_controller = new MainController();

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
        TableCellRenderer button_renderer;
        AdminViewCandidatesTableModel model;
        
        Center() {
            this.setLayout(new BorderLayout());
            this.setBorder(new EmptyBorder(60,60,60,60));
            this.setOpaque(false);
            button_renderer = new JTableButtonRenderer();
            
            
            
            
//            String[] colNames = {"","Name", "Party", "Position"};
//            ImageIcon[] img = new ImageIcon[24];
//            for(int i = 0; i < 24; i++) {
//                Random rand = new Random();
//                String str = "C:\\Users\\Admin\\Documents\\GitHub\\ecrma\\ECRMA9\\src\\Icons\\pic"+ (rand.nextInt(4) + 1) +".jpg";
//                img[i] = new ImageIcon(str);
//                Image imagestr = img[i].getImage();
//                imagestr = imagestr.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
//                img[i] = new ImageIcon(imagestr);
//            }
                        
            model = new AdminViewCandidatesTableModel();
            
            model.setData(main_controller.candidate_controller.query_all_candidates_for_admin_view());

            table = new JTable() {
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    
                    return false;
                }
            };
            table.setModel(model);
            table.getColumnModel().getColumn(0).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
            table.getColumnModel().getColumn(0).setMaxWidth(120);
            table.getColumnModel().getColumn(0).setMinWidth(120);
            
            table.setDefaultRenderer(JButton.class, new JTableButtonRenderer());
            table.getColumnModel().getColumn(3).setCellRenderer(new JTableButtonRenderer());
            table.getColumnModel().getColumn(4).setCellRenderer(new JTableButtonRenderer());
            
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
    
    private class JTableButtonRenderer implements TableCellRenderer {        
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Border padding = BorderFactory.createEmptyBorder(50, 50, 50, 50);

            JButton button = (JButton) value;
            
            if (button != null) {
                button.setFont(new Font("Tahoma", Font.BOLD, 30));
                button.setBorder(BorderFactory.createCompoundBorder(getBorder(), padding));
                System.out.println("BUTTON IS NOT NULL");
                System.out.println(getBorder());
            }
                
                
            return button;  
        }
    }
    
}
