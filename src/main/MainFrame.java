package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import terrain.Terrain;

public class MainFrame extends JFrame {
    JPanel panel_actuelle;

    public MainFrame() {
        panel_actuelle = new WelcomFrame();
        add(panel_actuelle);
        this.setTitle("police et voleur");
        this.setSize(Terrain.width, Terrain.length);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
