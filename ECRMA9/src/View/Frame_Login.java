/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MainController;
import Controller.UserController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author dandani-cs
 */
public class Frame_Login extends JFrame implements ActionListener{
    AdminMenu adminmenu;
    UserMenu usermenu;
    
    MainController main_controller = new MainController();
    
    JPanel login_panel, about_panel, input_panel;
    ImagePanel bgImagePanel;
    BufferedImage bgImage;
    JLabel lbl_email, lbl_password, lbl_about, lbl_logo, lbl_header;
    JTextField txt_email;
    JPasswordField txt_password;
    JButton btn_login, btn_empty, btn_loginUser;
    
   
    public Frame_Login() {    
        
        this.setTitle("Election Candidates Record Management");
        
        Color main = new Color(33, 97, 140);
        login_panel = new JPanel(new BorderLayout());
        
        login_panel.setMinimumSize(new Dimension(720, 1080));
        login_panel.setPreferredSize(new Dimension(720, 1080));
        login_panel.setBackground(main);
        Border login_margin = new EmptyBorder(150, 100, 250, 100);
        login_panel.setBorder(login_margin);
        
        ImageIcon icon = new ImageIcon("src/Icons/ecrmaLogo.png");
        lbl_logo = new JLabel(icon);
        
        
        
        lbl_header = new JLabel("Login Your Account");
        lbl_header.setFont(new Font("Tahoma", Font.BOLD, 30));
        lbl_header.setForeground(Color.WHITE);
        lbl_header.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_header.setBorder(new EmptyBorder(80,0,100,0));//top,left,bottom,right
        
        //input start
        input_panel = new JPanel(new GridLayout(5, 1, 0, 0));
        input_panel.setMaximumSize(new Dimension(250, 270));
        input_panel.setPreferredSize(new Dimension(250, 270));
        Border input_margin = new EmptyBorder(0, 20, 20, 20);
        input_panel.setBorder(input_margin);
        input_panel.setBackground(new Color(33, 97, 140));
        input_panel.setForeground(Color.WHITE);
        
        //lbl_email = new JLabel("Email:");
        txt_email = new JTextField();
        txt_email.setText("Email Address...");
        txt_email.setBackground(main);
        txt_email.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        
        //lbl_password = new JLabel("Password:");
        txt_password = new JPasswordField(8);
        txt_password.setText("Password...");
        txt_password.setBackground(main);
        txt_password.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        
        txt_password.setEchoChar('*');
        
        JCheckBox checkBox1 = new JCheckBox("Show Password");
        checkBox1.setBounds(100,100, 50,50);
        checkBox1.setBackground(main);
        checkBox1.setForeground(Color.WHITE);
        
        btn_login = new JButton("LOG IN");
        btn_login.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn_login.setBackground(Color.WHITE);
        btn_login.setForeground(main);
        btn_login.addActionListener(this);
        
        //login as guest
        btn_loginUser = new JButton("or LOGIN AS GUEST");
        btn_loginUser.setFont(new Font("Tahoma", Font.ITALIC, 12));
        btn_loginUser.setBackground(main);
        btn_loginUser.setForeground(Color.WHITE);
        btn_loginUser.setHorizontalAlignment(SwingConstants. CENTER);
        btn_loginUser.setBorder(null);
        btn_loginUser.addActionListener(this);
        
        txt_email.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txt_password.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        txt_email.setForeground(Color.WHITE);
        txt_password.setForeground(Color.WHITE);
        
 //       input_panel.add(lbl_email);
        input_panel.add(txt_email);
 //       input_panel.add(lbl_password);
        input_panel.add(txt_password);
        input_panel.add(checkBox1);
        input_panel.add(btn_login);
        input_panel.add(btn_loginUser);
        
        login_panel.add(lbl_logo, BorderLayout.NORTH);
        login_panel.add(lbl_header, BorderLayout.CENTER);
        login_panel.add(input_panel, BorderLayout.SOUTH);
        
        validate();
        
        about_panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        about_panel.setMinimumSize(new Dimension(720, 1080));
        about_panel.setMaximumSize(new Dimension(720, 1080));
        about_panel.setPreferredSize(new Dimension(720, 1080));
        about_panel.setOpaque(false);
        
        lbl_about = new JLabel("<html><b><font size='100'>Know your leaders.<br />"
                + "Make informed decisions.<br />"
                + "Secure your future.</font></b>"
                + "<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />"
                + "<b><font size='30'>Vote Records</font></b><br />"
                + "Election Candidates Records Management"
                + "</html>");
        lbl_about.setForeground(Color.WHITE);
        lbl_about.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Border lbl_about_border = new EmptyBorder(250, 50, 50, 50);
        lbl_about.setBorder(lbl_about_border);
        
        txt_email.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                txt_email.setText("");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
        
        txt_password.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                txt_password.setText("");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
        
    
        checkBox1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //checkbox has been selected //do selected action... 
                    txt_password.setEchoChar((char)0);
                } else {
                    //checkbox has been deselected //do deselected action... 
                    txt_password.setEchoChar('*');
                }
            }
        });
        
        
        try {
            System.out.println(new File(".").getCanonicalPath() + "src\\img\\bg.jpg");
            System.out.println("path: " + getClass().getResource("Icons\\add128px.png"));
            bgImage = ImageIO.read(new File(new File(".").getCanonicalPath() + "\\img\\bg.jpg"));
            bgImagePanel = new ImagePanel(bgImage);
            bgImagePanel.setLayout(new BorderLayout());
            setContentPane(bgImagePanel);
        } catch (IOException e) {
            about_panel.setBackground(Color.WHITE);
            about_panel.setForeground(Color.BLACK);
        }
        
        about_panel.add(lbl_about);
        
        bgImagePanel.add(login_panel, BorderLayout.WEST);
        bgImagePanel.add(about_panel, BorderLayout.EAST);
        
        this.pack();
        this.setSize(new Dimension(1920,1080));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_login) {
            String username = txt_email.getText();
            String password = String.valueOf(txt_password.getPassword());
            
            if(main_controller.user_controller.isAuthorized(username, password))
            {
                if(main_controller.user_controller.isAdmin())
                {
                    adminmenu = new AdminMenu(main_controller);
                    adminmenu.setVisible(true);
                    this.setVisible(false);    
                } else
                {
                    usermenu = new UserMenu();
                    usermenu.set_main_controller(main_controller);
                    usermenu.setVisible(true);
                    this.setVisible(false);
                }
            } else
            {
                // TODO: possibly change this if there is a better error msg/design
                JOptionPane.showMessageDialog(null, 
                                              "The email and/or password is incorrect.",
                                              "Login Failed!", 
                                              JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == btn_loginUser) {
            
        }
    }
}

class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}


