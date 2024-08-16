package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JOptionPane;

import auto_move.Jeu;
import main.Main;

public class Joueur_Key_Listener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        Main m = (Main) e.getComponent();
        if (e.getKeyChar() == 'a') {
            System.out.println("!!!!==== TOUR Du VOLEUR====!!!!");
            m.best_move(false);
        } else if (e.getKeyChar() == 'q') {
            int[] minimax = m.j.minimax2(0, false);
            JOptionPane.showConfirmDialog(m, "io ny valeur an le minimax " + minimax[0]);
        } else if (e.getKeyChar() == 's') {
            System.out.println("!!!!==== TOUR Du VOLEUR====!!!!");
            m.best_move(false);
        } else if (e.getKeyChar() == 'd') {
            System.out.println("================ chemin ===============");
            List<Jeu> liste_jeu = m.chemin_winner();
            int i = 0;
            boolean tour_du_police = true;
            for (Jeu jeu : liste_jeu) {
                jeu.afficher_jeu();
                jeu.affiche_frame(i + " tour du police");
                i++;
            }

        } else {
            // System.out.println("toucher");
            m.best_move(true);
        }
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
