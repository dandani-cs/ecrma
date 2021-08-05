package View;

import Controller.MainController;
import java.awt.*;
import javax.swing.*;

public class SearchFrame extends JFrame {
    CandidateDetailsPanel adminviewcand;
    MainController main_controller = new MainController();
    
    public SearchFrame() {
        adminviewcand = new CandidateDetailsPanel(main_controller, main_controller.candidate_controller.query_candidate_by_id(1));
        
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
