/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author dandani-cs
 */
public class HeaderPanel extends JPanel {
    private JLabel lbl_header,
            lbl_description;
    
    public HeaderPanel(String header, String description) {
        setLayout(new GridLayout(2, 1));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(50, 50, 75, 50));
        
        lbl_header = new JLabel(header);
        lbl_header.setFont(new Font("Bebas Neue", Font.BOLD, 35));
        lbl_header.setForeground(StaticResources.getMainColor());
        
        lbl_description = new JLabel(description);
        lbl_description.setFont(new Font("Open Sans", Font.PLAIN, 20));
        lbl_description.setForeground(StaticResources.getMainColor());
        
        add(lbl_header);
        add(lbl_description);
    }
}
