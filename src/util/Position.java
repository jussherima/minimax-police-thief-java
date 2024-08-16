package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import terrain.Terrain;

public class Position extends Point {
    public Point2D.Double[] liste;

    public Position() {
        int echelle = Terrain.echelle;
        liste = new Point2D.Double[21];

        // les coordonnes des intersection ou position du milieu
        liste[0] = new Point2D.Double(3 * echelle, 3 * echelle);
        liste[1] = new Point2D.Double(2.5 * echelle, 3 * echelle);
        liste[2] = new Point2D.Double(3 * echelle, 2.5 * echelle);
        liste[3] = new Point2D.Double(3.5 * echelle, 3 * echelle);
        liste[4] = new Point2D.Double(3 * echelle, 3.5 * echelle);

        // dessiner les point de gauche
        liste[5] = new Point2D.Double(1 * echelle, 3 * echelle);
        liste[6] = new Point2D.Double((1 * echelle) + Math.cos(Math.toRadians(75)) * echelle,
                (3 * echelle) - Math.sin(Math.toRadians(75)) * echelle);
        liste[7] = new Point2D.Double(2 * echelle, 3 * echelle);
        liste[8] = new Point2D.Double((1 * echelle) + Math.cos(Math.toRadians(75)) * echelle,
                (3 * echelle) + Math.sin(Math.toRadians(75)) * echelle);

        // dessiner les point d'en haut
        liste[9] = new Point2D.Double(244, 151);
        liste[10] = new Point2D.Double(3 * echelle, echelle);
        liste[11] = new Point2D.Double(476, 152);
        liste[12] = new Point2D.Double(3 * echelle, 2 * echelle);

        // dessiner les point de droite
        liste[13] = new Point2D.Double(4 * echelle, 3 * echelle);
        liste[14] = new Point2D.Double(568, 244);
        liste[15] = new Point2D.Double(5 * echelle, 3 * echelle);
        liste[16] = new Point2D.Double(568, 474);

        // dessiner les points d'en bas
        liste[17] = new Point2D.Double(245, 567);
        liste[18] = new Point2D.Double(3 * echelle, 4 * echelle);
        liste[19] = new Point2D.Double(477, 569);
        liste[20] = new Point2D.Double(3 * echelle, 5 * echelle);
    }

    public Position(int i) {
        i = 0;

    }

    public Point2D.Double distance(Point2D.Double moi) {
        for (Point2D.Double po : liste) {
            double x1 = po.getX();
            double x2 = moi.getX();

            double y1 = po.getY();
            double y2 = moi.getY();

            double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
            if (distance < 0.25 * Terrain.echelle) {
                return po;
            }
        }
        return new Point2D.Double(-1 * Terrain.echelle, -1 * Terrain.echelle);
    }

    public void draw(Graphics2D g) {
        int echelle = Terrain.echelle;
        g.setColor(Color.RED);
        for (int i = 0; i < liste.length; i++) {
            Ellipse2D.Double shape = new Ellipse2D.Double((liste[i].getX() - 0.125) * echelle,
                    (liste[i].getY() - 0.125) * echelle, 0.25 * echelle,
                    0.25 * echelle);
            g.fill(shape);
        }
    }

    public static void main(String[] args) {
        Position p = new Position();
        if (p.distance(new Point2D.Double(1 * Terrain.echelle, 1 * Terrain.echelle)).getX() > -1 * Terrain.echelle) {
            System.out.println(p.distance(new Point2D.Double(1 * Terrain.echelle, 1 * Terrain.echelle)).getX());
            System.out.println(p.distance(new Point2D.Double(1 * Terrain.echelle, 1 * Terrain.echelle)).getY());
            System.out.println("existe");
        } else {
            System.out.println("no existe");
        }
    }
}
