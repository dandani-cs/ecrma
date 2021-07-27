package View;

import java.awt.*;
import javax.swing.*;

public class SearchFrame extends JFrame {
    AdminViewCandidates adminviewcand;
    
    public SearchFrame() {
        adminviewcand = new AdminViewCandidates();
        this.add(adminviewcand);
        
        this.setSize(new Dimension(1920,1080));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new SearchFrame();
    }
}
