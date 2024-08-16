package auto_move;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JFrame;

import joueur.Police;
import joueur.Voleur;
import listener.Joueur_Key_Listener;
import listener.Joueur_listener;
import terrain.Terrain;
import util.Position;

public class Conception extends JFrame {
    Terrain t;
    Voleur voleur;
    List<Police> police;

    public Conception(String titre, Jeu jeu) {
        // donner de jeu
        t = new Terrain();
        police = jeu.police;
        voleur = jeu.voleur;

        // proprieter d la frame
        this.addMouseListener(new Joueur_listener(voleur, police));
        this.addKeyListener(new Joueur_Key_Listener());
        this.setTitle("chemin " + titre);
        this.setSize(Terrain.width, Terrain.length);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        t.drawTerrain((Graphics2D) g);
        voleur.draw((Graphics2D) g, Terrain.echelle);
        for (Police po : police) {
            po.draw((Graphics2D) g, Terrain.echelle);
        }
        Position p = new Position();
        p.draw((Graphics2D) g);
    }

}
