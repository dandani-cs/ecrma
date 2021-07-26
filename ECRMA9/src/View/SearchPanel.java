package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SearchPanel extends JPanel {
    Color bgColor;
    
    North north;
    West west;
    Insets westInsets;
    Center center;
    South south;
    
    public SearchPanel() {
        bgColor = new Color(255,255,255);
        
        
        this.setLayout(new BorderLayout());
        north = new North();
        north.setBorder(new EmptyBorder(10,10,10,10));
        westInsets = new Insets(5,5,5,5);
        west = new West();
        west.setBorder(new CompoundBorder(new EmptyBorder(westInsets),
                    new MatteBorder(0, 0, 0, 1, Color.black))                );

        center = new Center();
        
        this.add(north, BorderLayout.NORTH);
        this.add(west, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);
        
        this.setBackground(bgColor);
    }
    
    class North extends JPanel {
        JLabel title;
        
        North() {
            this.setLayout(new BorderLayout());
            
            title = new JLabel("Election Candidates Record Management Application");
            title.setFont(new Font("CALIBRI", Font.PLAIN, 24));
            title.setForeground(Color.white);
            this.add(title, BorderLayout.WEST);
            this.setPreferredSize(new Dimension(50,50));
            this.setBackground(Color.decode("#21618C"));
            
        }
    }
    
    class West extends JPanel {
       
        JPanel center;
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
        JLabel candDetails;
        
        
        JLabel backButton;

        
        West() {
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(250,250));
            this.setLayout(new BorderLayout());
            
            
            center = new JPanel();
            center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
            
            candNameJTF = new JTextField();
            candNameJTF.setFont(new Font("CALIBRI", Font.PLAIN, 14));
            candNameJP = panelize(candNameJTF,-1,25,0,0);
            partyJL = new JList(new Object[] {"PartyA","PartyB","PartyC","PartyD"});          
            partyJL.setFont(new Font("CALIBRI", Font.PLAIN, 14));
            partyJL.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            partyJSP = new JScrollPane(partyJL);
            partyJP = panelize(partyJSP,-1,100,0,0);
            partyJP.setVisible(false);
            positionJL = new JList(new Object[] {"PositionA","PositionB","PositionC","PositionD"});
            positionJL.setFont(new Font("CALIBRI", Font.PLAIN, 14));
            positionJL.setBorder(BorderFactory.createLineBorder(Color.lightGray));            
            positionJSP = new JScrollPane(positionJL);
            positionJP = panelize(positionJSP,-1,100,0,0);            
            positionJP.setVisible(false);
            searchBy = new JLabel("Search By:");
            searchBy.setFont(new Font("CALIBRI", Font.PLAIN, 18));
            
            String[] searchesSTR = new String[] {"Candidate Name", "Party", "Position"};
            searchesBG = new ButtonGroup();
            searches = new JRadioButton[3];
            for(int i = 0; i < 3; i++) {
                searches[i] = new JRadioButton(searchesSTR[i]);
                searches[i].setFont(new Font("CALIBRI", Font.PLAIN, 14));
                searches[i].setForeground(Color.black);
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
            candDetails = new JLabel("View Candidate Details");
            candDetails.setFont(new Font("CALIBRI", Font.PLAIN, 18));
            
            center.add(candNameJP);
            center.add(partyJP);
            center.add(positionJP);
            
            center.add(panelize(searchBy));
            for(int i = 0; i < 3; i++) {
                center.add(panelize(searches[i]));
            }
            center.add(panelize(separator,-1,2,0,0));
            center.add(panelize(candDetails,-1,-1,0,10));
            center.setOpaque(false);
            
            
            backButton = new JLabel("BACK BUTTON");
            backButton.setFont(new Font("CALIBRI", Font.PLAIN, 18));
            
            this.add(center, BorderLayout.CENTER);
            this.add(backButton, BorderLayout.SOUTH);
            
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
            this.setOpaque(false);
            
            String[] colNames = {"","Name", "Party", "Position"};
            ImageIcon[] img = new ImageIcon[24];
            for(int i = 0; i < 24; i++) {
                Random rand = new Random();
                String str = "C:\\Users\\apmbonifacio\\Documents\\GitHub\\ecrma\\ECRMA9\\src\\Icons\\pic"+ (rand.nextInt(4) + 1) +".jpg";
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
            this.revalidate();
            this.repaint();
            this.add(sp);
        }
        
    }
    
    class South extends JPanel {
        
    }
}
