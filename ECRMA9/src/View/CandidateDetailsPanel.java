/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcandidatedetails;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

/**
 *
 * @author Uriel Federez
 */
public class CandidateDetailsPanel extends JPanel {
    // TODO: Actual candidate data, replace later on w/ class
    private static final String first_name = "Juan";
    private static final String last_name  = "Dela Cruz";
    private static final String position   = "President";
    private static final String party      = "Party";
    private static final String platform   = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                                            + "Donec elementum tellus diam, a tempus est sodales sit amet."
                                            + "Sed sit amet turpis eu felis ullamcorper aliquam ac id ex."
                                            + "Vivamus purus erat, mollis eu semper a, dignissim nec odio. "
                                            + "Fusce augue sapien, fringilla sit amet nulla quis, porta "
                                            + "venenatis sapien. Phasellus id interdum mauris. Suspendisse "
                                            + "sollicitudin velit mattis dolor hendrerit, ac tristique ante "
                                            + "faucibus. Aliquam at tincidunt quam, id feugiat tellus. Aliquam "
                                            + "euismod malesuada porta. Nulla eu arcu sed tortor tincidunt rutrum ac quis purus.\n" +
                                              "\n" +
                                              "Pellentesque ut eros id purus dapibus tincidunt. "
                                            + "Cras vitae lobortis tortor. Ut cursus tellus eu arcu accumsan, "
                                            + "a sodales leo luctus. Duis euismod nibh a malesuada suscipit. "
                                            + "Vestibulum posuere interdum aliquam. Pellentesque imperdiet leo id "
                                            + "molestie pellentesque. Praesent vestibulum luctus ipsum, at finibus turpis pulvinar\"";
    
    private ArrayList<Object[]> personal_info_list;
    private ArrayList<Object[]> education_bg_list;
    
    private static final Color primary_blue = new Color(33, 97, 140);
    private static final Color primary_bg   = Color.white;
    
    private final JPanel header_panel  = new JPanel();
    private final JPanel content_panel = new JPanel();
    private JPanel pers_info_content   = new JPanel();
    private JPanel educ_content        = new JPanel();
    
    private final Font title_font = new Font("Calibri", Font.BOLD,  14);
    private final Font info_font  = new Font("Calibri", Font.PLAIN, 18);
    private final Font info_sub   = new Font("Calibri", Font.BOLD,  16);
    
    private final DateFormat date_format = new SimpleDateFormat("MMMMM dd, yyyy");
        
    private final float header_weighty = 0.25f;
    
    public void set_education_bg(ArrayList<Object[]> new_educ_bg) {
        this.education_bg_list = new_educ_bg;
        
        educ_content.removeAll();
        educ_content.setLayout(new GridLayout(0, 2));
        for(int i = 0; i < education_bg_list.size(); i++) {
            JPanel institute_info      = new JPanel();
            JPanel institute_container = new JPanel();
            
            institute_container.setLayout(new BorderLayout());
            institute_container.setBackground(primary_bg);
            institute_info.setBackground(primary_bg);
            institute_info.setLayout(new GridLayout(2, 1));
            
            String degree_str     = (String) education_bg_list.get(i)[0];
            String university_str = (String) education_bg_list.get(i)[1];
            
            String gradudation_date_str = date_format.format(((Date) education_bg_list.get(i)[2]));
            
            JLabel degree_label   = new JLabel(degree_str);
            JLabel institute_name = new JLabel(university_str);
            
            degree_label.setFont(info_font);
            
            institute_name.setForeground(primary_blue);
            institute_name.setFont(info_sub);
            
            institute_info.add(degree_label);
            institute_info.add(institute_name);
            institute_container.add(institute_info, BorderLayout.NORTH);
            educ_content.add(institute_container);
            
            JLabel date_label = new JLabel(gradudation_date_str);
            date_label.setFont(info_font);
            
            JPanel date_panel = new JPanel();
            date_panel.setBackground(primary_bg);
            date_panel.setLayout(new BorderLayout());
            date_panel.add(date_label, BorderLayout.NORTH);
            educ_content.add(date_panel);
        }
        educ_content.revalidate();
    }
    
    public CandidateDetailsPanel() {
        // Dummy data
        personal_info_list = new ArrayList<>();
        education_bg_list  = new ArrayList<>();
        
        education_bg_list.add( 
            new Object[]{ 
                "Masters of Science in Computer Science", "Mapua University", new Date(105, 06, 13)
            }
        );
        
        personal_info_list.add(new Object[] { "January 12, 1980", "1st Birthday" });
        personal_info_list.add(new Object[] { "January 16, 1980", "2nd Birthday" });
        personal_info_list.add(new Object[] { "January 21, 1980", "3rd Birthday" });
        
        setBackground(primary_bg);
        header_panel.setBackground(primary_bg);
        content_panel.setBackground(primary_bg);
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints main_gbc = new GridBagConstraints();
        main_gbc.anchor     = GridBagConstraints.FIRST_LINE_START;
        main_gbc.fill       = GridBagConstraints.BOTH;
        main_gbc.gridwidth  = 1;
        main_gbc.gridheight = 1;
        
        // Header panel configuration and layout
        main_gbc.weightx = 1.0f;
        main_gbc.weighty = header_weighty;
        main_gbc.gridx   = 0;
        main_gbc.gridy   = 0;
        
        // Header panel content
        JPanel image_panel     = new JPanel();
        JPanel main_info_panel = new JPanel();  // name, pos, party, edit/delete buttons
        
        header_panel.setLayout(new GridBagLayout());
        header_panel.setBackground(primary_bg);
        GridBagConstraints header_gbc = new GridBagConstraints();
        
        float img_weightx = 0.25f;
        int   inset       = 50;
        
        header_gbc.anchor     = GridBagConstraints.FIRST_LINE_START;
        header_gbc.fill       = GridBagConstraints.BOTH;
        header_gbc.gridwidth  = 1;
        header_gbc.gridheight = 1;
        
        header_gbc.weightx    = img_weightx;
        header_gbc.weighty    = 1.0f;
        header_gbc.gridx      = 0;
        header_gbc.gridy      = 0;
        header_gbc.insets     = new Insets(inset/2, inset, inset / 2, inset / 2);
        
        AvatarImagePanel image_test   = new AvatarImagePanel("img/avatar-icon.png");
        
        image_test.setBackground(primary_bg);
        image_panel.setLayout(new BorderLayout());
        image_panel.add(image_test, BorderLayout.CENTER);
        
        header_panel.add(image_panel, header_gbc);
        
        header_gbc.weightx    = 1.0f - img_weightx;
        header_gbc.weighty    = 1.0f;
        header_gbc.gridx      = 1;
        header_gbc.gridy      = 0;
        header_gbc.insets     = new Insets(inset, 0, inset/2, inset);
        //main_info_panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        // Main info panel content
        int full_name_size = 30;
        JPanel main_info_content = new JPanel();
        
        main_info_panel.setLayout(new BorderLayout());
        main_info_panel.setBackground(primary_bg);
        
        main_info_content.setBackground(primary_bg);
        main_info_content.setLayout(new GridBagLayout());
        
        JLabel full_name_label = new JLabel((last_name + ", " + first_name).toUpperCase());
        JLabel position_label  = new JLabel(position);
        JLabel party_label     = new JLabel(party);
        
        full_name_label.setFont(new Font(Font.DIALOG, Font.BOLD, full_name_size));
        full_name_label.setForeground(primary_blue);
        
        position_label.setFont(new Font(Font.DIALOG, Font.BOLD, (int)(full_name_size * 0.6f)));
        party_label.setFont   (new Font(Font.DIALOG, Font.PLAIN,(int)(full_name_size * 0.5f)));
        
        JButton edit_button   = new JButton("EDIT");
        JButton delete_button = new JButton("DELETE");
        
        Font button_font = new Font(Font.DIALOG, Font.BOLD, 18);
        
        edit_button.setFont(button_font);
        edit_button.setBackground(primary_blue);
        edit_button.setForeground(primary_bg);
        
        delete_button.setFont(button_font);
        delete_button.setBackground(primary_blue);
        delete_button.setForeground(primary_bg);
        
        JPanel main_info_buttons = new JPanel();
        main_info_buttons.setBackground(primary_bg);
        
        main_info_buttons.setLayout(new GridLayout(1, 2, 10, 0));
        main_info_buttons.add(edit_button);
        main_info_buttons.add(delete_button);
        
        GridBagConstraints minfo_gbc = new GridBagConstraints();
        minfo_gbc.anchor     = GridBagConstraints.FIRST_LINE_START;
        minfo_gbc.gridwidth  = 1;
        minfo_gbc.gridheight = 1;
        minfo_gbc.weightx    = 1.0f;
        minfo_gbc.gridx      = 0;
        minfo_gbc.gridy      = 0;
        minfo_gbc.insets     = new Insets(1, 0, 2, 5);
        main_info_content.add(full_name_label, minfo_gbc);
        
        minfo_gbc.gridx      = 0;
        minfo_gbc.gridy      = 1;
        main_info_content.add(position_label, minfo_gbc);
        
        minfo_gbc.gridx      = 0;
        minfo_gbc.gridy      = 2;
        main_info_content.add(party_label, minfo_gbc);
        
        minfo_gbc.gridx      = 0;
        minfo_gbc.gridy      = 3;
        main_info_content.add(main_info_buttons, minfo_gbc);
        
        main_info_panel.add(main_info_content, BorderLayout.SOUTH);
        
        header_panel.add(main_info_panel, header_gbc);
        
        //header_panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        add(header_panel, main_gbc);
        
        // Content panel configuration and layout
        main_gbc.weightx = 1.0f;
        main_gbc.weighty = 1.0f - header_weighty;
        main_gbc.gridx   = 0;
        main_gbc.gridy   = 1;
        
        content_panel.setLayout(new GridBagLayout());
        
        GridBagConstraints content_gbc = new GridBagConstraints();
        content_gbc.anchor     = GridBagConstraints.FIRST_LINE_START;
        content_gbc.fill       = GridBagConstraints.BOTH;
        content_gbc.gridwidth  = 1;
        content_gbc.gridheight = 1;
        content_gbc.insets     = new Insets(5, inset, 5, inset);

        // Personal Information
        content_gbc.weightx = 1.0f;
        content_gbc.weighty = 0.33f;
        content_gbc.gridx   = 0;
        content_gbc.gridy   = 0;
        
        
        JPanel pers_info_panel   = new JPanel();
        //pers_info_panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        JLabel pers_info_title   = new JLabel("Personal Information");
        
        pers_info_panel.setBackground(primary_bg);
        pers_info_panel.setLayout(new BorderLayout());
        
        pers_info_title.setForeground(primary_blue);
        pers_info_title.setFont(title_font);
        
        pers_info_content.setBackground(primary_bg);
        pers_info_content.setLayout(new GridBagLayout());
        pers_info_content.setBorder(BorderFactory.createEmptyBorder(inset / 4, inset, 0, 0));
        
        GridBagConstraints pers_gbc = new GridBagConstraints();
        pers_gbc.anchor     = GridBagConstraints.PAGE_START;
        pers_gbc.fill       = GridBagConstraints.HORIZONTAL;
        pers_gbc.gridwidth  = 1;
        pers_gbc.gridheight = 1;
        
        for(int i = 0; i < personal_info_list.size(); i++) 
        {
            JPanel pers_info = new JPanel();
            pers_info.setLayout(new BorderLayout());
            
            pers_gbc.gridx   = i % 2;
            pers_gbc.gridy   = i / 2;
            pers_gbc.weightx = 0.5f;
            pers_gbc.weighty = 0.5f;
            
            JLabel pers_info_head = new JLabel((String) personal_info_list.get(i)[0]);
            JLabel pers_info_sub  = new JLabel((String) personal_info_list.get(i)[1]);
            
            pers_info_head.setFont(info_font);
            pers_info_sub.setFont(info_sub);
            pers_info_sub.setForeground(primary_blue);
            
            pers_info.setBackground(primary_bg);
            pers_info.add(pers_info_head, BorderLayout.CENTER);
            pers_info.add(pers_info_sub,  BorderLayout.SOUTH);
            
            pers_info_content.add(pers_info, pers_gbc);
        }
        
        JPanel pers_info_header = new JPanel();
        pers_info_header.setBackground(primary_bg);
        pers_info_header.setLayout(new BorderLayout());
        pers_info_header.add(pers_info_title, BorderLayout.CENTER);
        pers_info_header.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
        
        pers_info_panel.add(pers_info_content, BorderLayout.CENTER);
        pers_info_panel.add(pers_info_header, BorderLayout.NORTH);
        
        
        content_panel.add(pers_info_panel, content_gbc);
        
        // Educational Background
        content_gbc.weightx = 1.0f;
        content_gbc.weighty = 0.33f;
        content_gbc.gridx   = 0;
        content_gbc.gridy   = 1;
        
        JPanel educ_panel   = new JPanel();
        
        educ_panel.setLayout(new BorderLayout());
        
        educ_content.setLayout(new GridLayout(2, 4, 0, 15));
        educ_content.setBackground(primary_bg);
        educ_content.setBorder(BorderFactory.createEmptyBorder(inset / 4, inset, 0, 0));
        
        set_education_bg(education_bg_list);
        
        JLabel educ_title  = new JLabel("Educational Background");
        JPanel educ_header = new JPanel();
        educ_header.setBackground(primary_bg);
        educ_header.setLayout(new BorderLayout());
        educ_header.add(educ_title, BorderLayout.CENTER);
        educ_header.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
        
        educ_panel.setBackground(primary_bg);
        educ_panel.setLayout(new BorderLayout());
        
        educ_title.setForeground(primary_blue);
        educ_title.setFont(title_font);
        educ_panel.add(educ_header, BorderLayout.NORTH);
        educ_panel.add(educ_content, BorderLayout.CENTER);
        
        content_panel.add(educ_panel, content_gbc);
        
        // Platform
        content_gbc.weightx = 1.0f;
        content_gbc.weighty = 0.33f;
        content_gbc.gridx   = 0;
        content_gbc.gridy   = 2;
        
        JTextArea platform_text_area = new JTextArea(0, 1);
        JLabel platform_title  = new JLabel("Platform");
        JScrollPane scrollpane = new JScrollPane(platform_text_area);
        JPanel platform_header = new JPanel();
        
        platform_title.setFont(title_font);
        platform_title.setForeground(primary_blue);
        
        platform_header.setLayout(new BorderLayout());
        platform_header.setBackground(primary_bg);
        platform_header.add(platform_title, BorderLayout.CENTER);
        platform_header.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
        
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        platform_text_area.setOpaque(true);
        platform_text_area.setEditable(false);
        platform_text_area.setLineWrap(true);
        platform_text_area.setWrapStyleWord(true);
        platform_text_area.setText(platform);
        platform_text_area.setFont(new Font("Calibri", Font.PLAIN, 16));
        
        JPanel platform_panel = new JPanel();
        platform_panel.setLayout(new BorderLayout(0, 10));
        platform_panel.setBackground(primary_bg);
        //platform_panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        platform_panel.add(platform_header, BorderLayout.NORTH);
        platform_panel.add(scrollpane, BorderLayout.CENTER);
        
        //content_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        content_panel.add(platform_panel, content_gbc);
        //content_panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        add(content_panel, main_gbc);
    }
}

class AvatarImagePanel extends JPanel {
    private BufferedImage image;

    public AvatarImagePanel(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch(IOException e) {
            System.err.printf("[ERROR] Image (%s) could not be loaded\n", path);
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(2, 2);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Dimension parent_size = getParent().getSize();
        
        if(image != null) {
            // Maintain square aspect-ratio
            int side_length = (parent_size.width < parent_size.height ? parent_size.width : 
                                                                        parent_size.height);
            g.drawImage(image, 0, 0, side_length, side_length, null);
        }
    }
}