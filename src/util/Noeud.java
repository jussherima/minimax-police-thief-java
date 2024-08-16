package util;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import terrain.Terrain;

public class Noeud {
    int numero;
    public Point2D.Double position;
    public List<Noeud> voisin;

    public Noeud(int numero, Point2D.Double position, List<Noeud> voisin) {
        this.numero = numero;
        this.position = position;
        this.voisin = voisin;
    }

    public static List<Noeud> get_all() {
        Position p = new Position();
        List<Noeud> liste = new ArrayList<>();
        // initialiser les noeud de centre
        Noeud centre0 = new Noeud(0, p.liste[0], null);
        Noeud centre1 = new Noeud(1, p.liste[1], liste);
        Noeud centre2 = new Noeud(2, p.liste[2], liste);
        Noeud centre3 = new Noeud(3, p.liste[3], liste);
        Noeud centre4 = new Noeud(4, p.liste[4], liste);

        // intialiser les noeuds de gauche
        Noeud gauche1 = new Noeud(5, p.liste[5], null);
        Noeud gauche2 = new Noeud(6, p.liste[6], null);
        Noeud gauche3 = new Noeud(7, p.liste[7], null);
        Noeud gauche4 = new Noeud(8, p.liste[8], null);

        // initialiser les haut de droite
        Noeud haut1 = new Noeud(9, p.liste[9], null);
        Noeud haut2 = new Noeud(10, p.liste[10], null);
        Noeud haut3 = new Noeud(11, p.liste[11], null);
        Noeud haut4 = new Noeud(12, p.liste[12], null);

        // initialiser les point de droite
        Noeud droite1 = new Noeud(13, p.liste[13], null);
        Noeud droite2 = new Noeud(14, p.liste[14], null);
        Noeud droite3 = new Noeud(15, p.liste[15], null);
        Noeud droite4 = new Noeud(16, p.liste[16], null);

        // initialiser les points de bas
        Noeud bas1 = new Noeud(17, p.liste[17], null);
        Noeud bas2 = new Noeud(18, p.liste[18], null);
        Noeud bas3 = new Noeud(19, p.liste[19], null);
        Noeud bas4 = new Noeud(20, p.liste[20], null);

        // setters les voisin du noeud

        // centre
        centre0.voisin = new ArrayList<>();
        centre1.voisin = new ArrayList<>();
        centre2.voisin = new ArrayList<>();
        centre3.voisin = new ArrayList<>();
        centre4.voisin = new ArrayList<>();

        centre0.voisin.addAll(Arrays.asList(centre1, centre2, centre3, centre4));
        centre1.voisin.addAll(Arrays.asList(centre0, centre2, centre4, gauche3));
        centre2.voisin.addAll(Arrays.asList(centre0, centre1, centre3, haut4));
        centre3.voisin.addAll(Arrays.asList(centre0, centre2, centre4, droite1));
        centre4.voisin.addAll(Arrays.asList(centre0, centre1, centre3, bas2));

        // gauche
        gauche1.voisin = new ArrayList<>();
        gauche2.voisin = new ArrayList<>();
        gauche3.voisin = new ArrayList<>();
        gauche4.voisin = new ArrayList<>();

        gauche1.voisin.addAll(Arrays.asList(gauche2, gauche3, gauche4));
        gauche2.voisin.addAll(Arrays.asList(gauche1, gauche3, haut1));
        gauche3.voisin.addAll(Arrays.asList(gauche2, gauche1, gauche4, centre1));
        gauche4.voisin.addAll(Arrays.asList(gauche1, gauche3, bas1));

        // droite
        droite1.voisin = new ArrayList<>();
        droite2.voisin = new ArrayList<>();
        droite3.voisin = new ArrayList<>();
        droite4.voisin = new ArrayList<>();

        droite1.voisin.addAll(Arrays.asList(droite2, droite3, droite4, centre3));
        droite2.voisin.addAll(Arrays.asList(droite1, droite3, haut3));
        droite3.voisin.addAll(Arrays.asList(droite2, droite1, droite4));
        droite4.voisin.addAll(Arrays.asList(droite1, droite3, bas3));

        // bas
        bas1.voisin = new ArrayList<>();
        bas2.voisin = new ArrayList<>();
        bas3.voisin = new ArrayList<>();
        bas4.voisin = new ArrayList<>();

        bas1.voisin.addAll(Arrays.asList(bas2, bas4, gauche4));
        bas2.voisin.addAll(Arrays.asList(bas1, bas3, bas4, centre4));
        bas3.voisin.addAll(Arrays.asList(bas2, bas4, droite4));
        bas4.voisin.addAll(Arrays.asList(bas1, bas2, bas3));

        // haut
        haut1.voisin = new ArrayList<>();
        haut2.voisin = new ArrayList<>();
        haut3.voisin = new ArrayList<>();
        haut4.voisin = new ArrayList<>();

        haut1.voisin.addAll(Arrays.asList(haut2, haut4, gauche2));
        haut2.voisin.addAll(Arrays.asList(haut1, haut3, haut4));
        haut3.voisin.addAll(Arrays.asList(haut2, haut4, droite2));
        haut4.voisin.addAll(Arrays.asList(haut1, haut2, haut3, centre2));

        liste.addAll(Arrays.asList(centre0, centre1, centre2, centre3, centre4,
                gauche1, gauche2, gauche3, gauche4,
                droite1, droite2, droite3, droite4,
                haut1, haut2, haut3, haut4,
                bas1, bas2, bas3, bas4));
        return liste;
    }

    public static Noeud getNoeud(Noeud actuelle, Point2D.Double moi) {
        if (Noeud.distance(moi) != null) {
            Noeud voisin = Noeud.distance(moi);
            // System.out.println(voisin.position);
            // System.out.println(actuelle.voisin.contains(voisin));
            if (actuelle.estVoisin(voisin)) {
                return voisin;
            } else {
                // for (Noeud noeud : actuelle.voisin) {
                // System.out.println(noeud.position + " ");
                // }
                // System.out.println("ne contient pas");
            }
        }
        return null;

    }

    public static Noeud distance(Point2D.Double moi) {
        List<Noeud> liste = Noeud.get_all();
        for (Noeud noeud : liste) {
            double x1 = noeud.position.getX();
            double x2 = moi.getX();

            double y1 = noeud.position.getY();
            double y2 = moi.getY();

            double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
            if (distance < 0.30 * Terrain.echelle) {
                return noeud;
            }
        }
        return null;
    }

    public boolean estVoisin(Noeud noeud) {
        List<Noeud> liste = this.voisin;
        for (Noeud noeud2 : liste) {
            if (noeud.position.getX() == noeud2.position.getX() && noeud.position.getY() == noeud2.position.getY()) {
                return true;
            }
        }
        return false;
    }
}