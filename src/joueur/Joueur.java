package joueur;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import util.Noeud;

public class Joueur {
    Point2D.Double position;

    public Joueur(Point2D.Double position) {
        this.position = position;
    }

    public void draw(Graphics2D g, int echelle) {
        if (this instanceof Voleur) {
            g.setColor(Color.BLUE);

        } else {
            g.setColor(Color.RED);
        }
        Ellipse2D.Double elipse = new Ellipse2D.Double(position.getX() - 0.125 * echelle,
                position.getY() - 0.125 * echelle, 0.25 * echelle,
                0.25 * echelle);
        g.fill(elipse);
    }

    public Point2D.Double getPosition() {
        return position;
    }

    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    public boolean issetJoueur(Noeud n, Joueur v) {
        if (v.getPosition().getX() == n.position.getX()) {
            if (v.getPosition().getY() == n.position.getY()) {
                return true;
            }
        }
        return false;
    }

}
