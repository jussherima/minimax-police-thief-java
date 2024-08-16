package joueur;

import java.awt.geom.Point2D;
import java.util.List;

import util.Noeud;

public class Voleur extends Joueur {
    public Voleur(Point2D.Double position) {
        super(position);
    }

    public boolean issetPolice(List<Police> police, Noeud noeud) {
        for (Police police2 : police) {
            if (noeud.position.getX() == police2.getPosition().getX()) {
                if (noeud.position.getY() == police2.getPosition().getY()) {
                    return true;
                }
            }
        }
        return false;
    }

}
