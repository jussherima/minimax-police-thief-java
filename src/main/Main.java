package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import auto_move.Jeu;
import joueur.Police;
import joueur.Voleur;
import listener.Joueur_Key_Listener;
import listener.Joueur_listener;
import terrain.Terrain;
import util.Position;

public class Main extends JPanel {
    Terrain t;
    Voleur voleur;
    List<Police> police;
    public Jeu j;

    public Main() throws Exception {
        JLabel backgroundLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon("../image/game_bac.jpg");

        // Redimensionner l'image
        Image image = originalIcon.getImage();
        Image resizedImage = image.getScaledInstance(800, 750, Image.SCALE_SMOOTH); // Ajuster la taille souhaitée
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        add(backgroundLabel);

        // Définir l'icône redimensionnée sur le JLabel
        backgroundLabel.setIcon(resizedIcon);
        backgroundLabel.setBounds(0, 0, 800, 710);

        // donner de jeu
        int echelle = Terrain.echelle;
        t = new Terrain();

        police = new ArrayList<>();
        police.add(new Police(new Point2D.Double(2.5 * echelle, 3 * echelle)));
        police.add(new Police(new Point2D.Double(3 * echelle, 3 * echelle)));
        police.add(new Police(new Point2D.Double(477, 569)));

        voleur = new Voleur(new Point2D.Double(3 * Terrain.echelle, 4 * Terrain.echelle));
        j = new Jeu(police, voleur);

        // proprieter d la frame
        this.addMouseListener(new Joueur_listener(voleur, police));
        this.addKeyListener(new Joueur_Key_Listener());
        this.setSize(Terrain.width, Terrain.length);
    }

    public void best_move(boolean tour_du_police) {
        // liste de jeux possible
        List<Jeu> jeus = new ArrayList<>();
        jeus = j.action_possible();

        // gerer les actions possible si c'est le tour du voleur
        if (!tour_du_police) {
            jeus = j.action_possible(voleur);
        }

        // les indice a retourner
        int[] meilleur = { 0, 0 };
        int indice_jeu = 0;

        // meilleur
        int index = 0; // index a incrementer
        for (Jeu jeu : jeus) {
            int[] minimax = jeu.minimax2(0, tour_du_police);
            // System.out.println("====== jeu a l'indice = " + index + " avec minimax = " +
            // minimax[0]
            // + " et profondeur = " + minimax[1]);
            // jeu.afficher_jeu();
            // System.out.println("====== fin ===================");
            // raha ohatra ka misy profondeur mbole kely kokoa
            if (minimax[0] == meilleur[0]) {
                if (minimax[1] < meilleur[1]) {
                    meilleur = minimax;
                    indice_jeu = index;
                }
            }

            // raha ohatra ka toron'le police
            if (tour_du_police) {
                if (minimax[0] > meilleur[0]) {
                    meilleur = minimax;
                    indice_jeu = index;
                }
            } else {
                System.out.println("tour du voleur ");
                if (minimax[0] < meilleur[0]) {
                    meilleur = minimax;
                    indice_jeu = index;
                }
            }
            index++;
        }
        System.out.println("indice nalaina " + indice_jeu);

        // indice_jeu=4;
        j = jeus.get(indice_jeu);
        police = j.police;
        voleur = j.voleur;
        try {
            int t = j.terminal_state();
            if (t != 0) {
                JOptionPane.showMessageDialog(this, "fin du jeu ");
            }
        } catch (Exception e) {
        }

        // pronstique apres minimax
        System.out.println("possibilite  =" + meilleur[0]);
        System.out.println("prodondeur   =" + meilleur[1]);

        repaint();
    }

    public void best_move(boolean tour_du_police, int profondeur) {
        // liste de jeux possible
        List<Jeu> jeus = new ArrayList<>();
        jeus = j.action_possible();

        // gerer les actions possible si c'est le tour du voleur
        if (!tour_du_police) {
            jeus = j.action_possible(voleur);
        }

        // les indice a retourner
        int[] meilleur = { 0, 0 };
        int indice_jeu = 0;

        // meilleur
        int index = 0; // index a incrementer
        for (Jeu jeu : jeus) {
            int[] minimax = jeu.minimax2(profondeur, tour_du_police);

            if (minimax[0] == meilleur[0]) {
                if (minimax[1] < meilleur[1]) {
                    meilleur = minimax;
                    indice_jeu = index;
                }
            }

            // raha ohatra ka toron'le police
            if (tour_du_police) {
                if (minimax[0] > meilleur[0]) {
                    meilleur = minimax;
                    indice_jeu = index;
                }
            } else {
                System.out.println("tour du voleur ");
                if (minimax[0] < meilleur[0]) {
                    meilleur = minimax;
                    indice_jeu = index;
                }
            }
            index++;
        }
        System.out.println("indice nalaina " + indice_jeu);

        // indice_jeu=4;
        j = jeus.get(indice_jeu);
        police = j.police;
        voleur = j.voleur;
        try {
            int t = j.terminal_state();
            if (t != 0) {
                JOptionPane.showMessageDialog(this, "fin du jeu ");
            }
        } catch (Exception e) {
        }

        // pronstique apres minimax
        System.out.println("possibilite  =" + meilleur[0]);
        System.out.println("prodondeur   =" + meilleur[1]);

        repaint();
    }

    public List<Jeu> chemin_winner() {
        boolean tour_du_police = true;
        Jeu jeu_cup = j.clone();
        List<Jeu> liste_jeu = new ArrayList<>();
        liste_jeu.add(j.clone());
        int[] minimax = j.minimax2(0, tour_du_police);
        int profondeur = 0;
        for (int i = 0; i < minimax[1]; i++) {
            best_move(tour_du_police, profondeur);
            liste_jeu.add(j.clone());
            tour_du_police = !tour_du_police;
            profondeur++;
        }
        j = jeu_cup;
        return liste_jeu;
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

    // public static void main(String[] args) {
    // try {
    // new Main();
    // } catch (Exception e) {
    // // TODO: handle exception
    // System.out.println(e.getMessage());
    // }
    // }
}
