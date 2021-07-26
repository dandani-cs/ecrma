/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
<<<<<<< HEAD
import javax.swing.JButton;
import javax.swing.JCheckBox;
=======
>>>>>>> dde5968 (merged with main project folder)
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author dandani-cs
 */
public class Frame_Login extends JFrame{
    JPanel login_panel, about_panel, input_panel;
    ImagePanel bgImagePanel;
    BufferedImage bgImage;
    
<<<<<<< HEAD

=======
>>>>>>> dde5968 (merged with main project folder)
    JLabel lbl_email, lbl_password, lbl_about, lbl_logo, lbl_header;
    JTextField txt_email, txt_password;
    JButton btn_login, btn_empty;
   
    public Frame_Login() {    
        Color main = new Color(33, 97, 140);
        login_panel = new JPanel(new BorderLayout());
        
        login_panel.setMinimumSize(new Dimension(720, 1080));
        login_panel.setPreferredSize(new Dimension(720, 1080));
        login_panel.setBackground(main);
        Border login_margin = new EmptyBorder(150, 100, 250, 100);
        login_panel.setBorder(login_margin);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Admin\\Desktop\\ecrma-main\\logo.png");
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
        txt_password = new JTextField();
        txt_password.setText("Password...");
        txt_password.setBackground(main);
        txt_password.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        
        JCheckBox checkBox1 = new JCheckBox("Show Password");
        checkBox1.setBounds(100,100, 50,50);
        checkBox1.setBackground(main);
        checkBox1.setForeground(Color.WHITE);
        
        btn_login = new JButton("LOG IN");
        btn_login.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn_login.setBackground(Color.WHITE);
        btn_login.setForeground(main);
        
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
        
        
        try {
            bgImage = ImageIO.read(new File("\\Users\\Admin\\Desktop\\ecrma-main\\bg.jpg"));
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
        setSize(1920, 1080);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
