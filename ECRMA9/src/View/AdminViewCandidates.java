/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Controller.FormEvent;
import Controller.FormListener;
import Controller.MainController;
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
import java.awt.event.WindowEvent;
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
    MainController main_controller;
    FormListener formListener;
    
    public AdminViewCandidates(MainController passed_mc) {
        bgColor = new Color(255,255,255);
        
        main_controller = passed_mc;

        this.setLayout(new BorderLayout());
        Border ogg_border = this.getBorder();
        Border margin1 = new EmptyBorder(0,-28, 531, 60);
        this.setBorder(new CompoundBorder(ogg_border, margin1));
        center = new Center();
        
        this.add(center, BorderLayout.CENTER);
        
        this.setBackground(bgColor);
        
        setVisible(true);
        setSize(new Dimension(1620,1080));
        
    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
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
    
    public class Center extends JPanel {
        JTable table;
        JButton btn_add;
        TableCellRenderer button_renderer;
        AdminViewCandidatesTableModel model;
        
        Center() {
            this.setLayout(new BorderLayout());
            this.setBorder(new EmptyBorder(60, 101, 60, 60));
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
   
            model.setData(main_controller.candidate_controller.query_all_candidates());
            
            Object[][] main_list;
            main_list = main_controller.candidate_controller.query_all_candidates_for_admin_view();

            table = new JTable() {
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    return false;
                }
            };
            
             
            table.setModel(model);
            System.out.println("settablemodel");
            table.getColumnModel().getColumn(0).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
            table.getColumnModel().getColumn(0).setMaxWidth(140);
            table.getColumnModel().getColumn(0).setMinWidth(140);
   
            
            /*DefaultTableCellRenderer cellpad = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
                ob, boolean b1, boolean b2, int row, int column) {
                super.getTableCellRendererComponent(
                    table, ob, b1, b2, row, column);
                setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
                return this;
            }
        };*/
            
            
            table.setGridColor(new Color(216,216,216));
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            
            DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
            tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
            tableCellRenderer.setBackground(Color.white);
            
            for(int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);            
            }

            
            //table.setDefaultRenderer(JButton.class, new JTableButtonRenderer());
            table.getColumnModel().getColumn(3).setCellRenderer(new JTableButtonRenderer());
            table.getColumnModel().getColumn(4).setCellRenderer(new JTableButtonRenderer());
            
            table.setIntercellSpacing(new Dimension(50, 50)); //?
            
            table.getColumnModel().getColumn(0).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
            table.getColumnModel().getColumn(0).setMaxWidth(120);
            table.getColumnModel().getColumn(0).setMinWidth(120);
            
            table.setRowHeight(120);
            
            table.getTableHeader().setFont(new Font("CALIBRI", Font.PLAIN,18));
            table.setFont(new Font("CALIBRI", Font.PLAIN, 18));
            
             
            table.addMouseListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent e) {
                   int col = table.columnAtPoint(e.getPoint());
                   int row = table.rowAtPoint(e.getPoint());
                   if (col == 0 || col == 1 || col == 2) {
                       FormEvent ev = new FormEvent(e, 
                               main_controller.candidate_controller.query_candidate_by_id(model.getCandidateID(row)));
                       ev.setPurpose("candidate details");
                       formListener.formEventOccurred(ev);
                   }
                   
                    
                   if (col == 3) {
                       // open EditCandidate
                       FormEvent ev = new FormEvent(e, 
                               main_controller.candidate_controller.query_candidate_by_id(model.getCandidateID(row)));
                       ev.setPurpose("candidate edit");
                       formListener.formEventOccurred(ev);
                       
                   } else if (col == 4) {
                       // open DeleteCandidate
                       System.out.println("DELETE CANDIDATE");
                       DeleteGUI delete_candidate = new DeleteGUI(main_controller, main_controller.candidate_controller.query_candidate_by_id(model.getCandidateID(row)));
                       delete_candidate.setVisible(true);
                       
                       delete_candidate.setFormListener(new FormListener() {
                           @Override
                           public void formEventOccurred(FormEvent e) {
                               if (e.getPurpose().equals("candidate delete")) {
                                   refresh();
//                                   
                                   delete_candidate.dispatchEvent(new WindowEvent(delete_candidate, WindowEvent.WINDOW_CLOSING));
                               }
                           }
                       });
                       
                       System.out.println("Delete candidate: " + table.getValueAt(row, 2) + " " + table.getValueAt(row, 1));
                       System.out.println("Candidate ID: " + model.getCandidateID(row));
                   }
               }
            });
            
            JScrollPane sp = new JScrollPane(table);
            

            //add button
            btn_add = new JButton("<html><center>ADD CANDIDATES</center></html>");
            btn_add.setBackground(new Color(51,55,69));
            btn_add.setFont(new Font("CALIBRI", Font.BOLD, 18));
            btn_add.setForeground(Color.WHITE);
            btn_add.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                String card_name = "cardAddCandidate";
                //tPanel.ta.setText(Integer.toString(num*num));
                FormEvent ev=new FormEvent(this,card_name);
                ev.setPurpose("candidate add");
                
                if(formListener != null){
                    formListener.formEventOccurred(ev);
                }
            }
            });
            
            this.add(sp);
            this.add(btn_add, BorderLayout.SOUTH);
            
        }
        
        public void refresh() {
            model.setData(main_controller.candidate_controller.query_all_candidates());
            model.fireTableDataChanged();
        }
        
    }
    
    private class JTableButtonRenderer implements TableCellRenderer {       

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Border padding = BorderFactory.createEmptyBorder(60,60,60,60);
            
            JButton button = (JButton) value;
            
            if (button != null) {
                button.setFont(new Font("Tahoma", Font.PLAIN, 14));
            }
            
            return button;  
        }
    }
    
    
    
}

    
