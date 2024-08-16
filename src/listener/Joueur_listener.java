package listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.List;

import auto_move.Jeu;
import joueur.Joueur;
import joueur.Police;
import main.Main;
import terrain.Terrain;
import util.Noeud;

public class Joueur_listener implements MouseListener {
    Noeud node_actuelle;
    Joueur joueur;
    List<Police> liste;

    public Joueur_listener(Joueur joueur, List<Police> liste) {
        this.joueur = joueur;
        this.liste = liste;
        List<Noeud> all_noeud = Noeud.get_all();
        node_actuelle = all_noeud.get(20);
    }

    private boolean afaka(Noeud n) {
        boolean existe = true;
        int index = 0;
        for (Police p : liste) {
            if (n.position.getX() == p.getPosition().getX() && n.position.getY() == p.getPosition().getY()) {
                existe = false;
                System.out.println("misy police a l'indice:" + index);
            }
            index++;
        }
        return existe;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component frame = e.getComponent();
        Noeud destination = Noeud.getNoeud(Noeud.distance(joueur.getPosition()),
                new Point2D.Double(e.getX(), e.getY()));
        System.out.println("io ny destination any " + destination.position.getX() / Terrain.echelle + " et y = "
                + destination.position.getY() / Terrain.echelle);
        if (destination != null) {
            System.out.println("afaka " + afaka(destination));
            if (afaka(destination)) {
                joueur.setPosition(destination.position);
                node_actuelle = destination;
                Thread th = new Thread(() -> {
                    // Main m = (Main)e.getComponent();
                    System.out.println("nouveau jeu " + liste.get(0).getPosition().getX() / Terrain.echelle + "et y = "
                            + liste.get(0).getPosition().getY() / Terrain.echelle);
                    ((Main) frame).best_move(true);
                    System.out.println("afak mietsika amzay");
                    Jeu j = ((Main) frame).j;
                    liste = j.police;
                    System.out.println("nouveau jeu " + liste.get(0).getPosition().getX() / Terrain.echelle + "et y = "
                            + liste.get(0).getPosition().getY() / Terrain.echelle);
                });
                th.start();
                System.out.println("efa redessiner le izy");
                frame.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseExited'");
    }

}
