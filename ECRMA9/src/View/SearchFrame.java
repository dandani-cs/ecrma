package View;

import java.awt.*;
import javax.swing.*;

public class SearchFrame extends JFrame {
    SearchPanel searchPanel;
    
    public SearchFrame() {
        searchPanel = new SearchPanel();
        this.add(searchPanel);
        
        this.setSize(new Dimension(1920,1080));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
}
