/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

/**
 *
 * @author dandani-cs
 */
public class TestFrame extends JFrame {
    private AddCampaignPanel add_period_panel;
    
    public TestFrame() {
       add_period_panel = new AddCampaignPanel();
       
       add(add_period_panel, BorderLayout.CENTER);
       
       this.pack();
       setSize(1920, 1080);
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
