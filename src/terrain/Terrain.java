package terrain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

// import util.Position;

public class Terrain {
    public static int echelle = 120;
    public static int width = 6 * echelle;
    public static int length = 6 * echelle;
    // List<Position> liste_position;

    public Terrain() {
        // liste_position = null;
    }

    public void drawTerrain(Graphics2D g) {
        g.setColor(Color.white);
        // dessiner le grand cercle principale
        g.drawOval(1 * echelle, 1 * echelle, 4 * echelle, 4 * echelle);

        // dessiner le 1 cercle
        g.drawArc(0 * echelle, 2 * echelle, 2 * echelle, 2 * echelle, -75, 150);

        // dessiner le 2 eme cercle
        g.drawArc(2 * echelle, 0 * echelle, 2 * echelle, 2 * echelle, -165, 150);

        // dessiner le 3 eme cercle
        g.drawArc(2 * echelle, 4 * echelle, 2 * echelle, 2 * echelle, 15, 150);

        // dessiner le 4 eme cercle
        g.drawArc(4 * echelle, 2 * echelle, 2 * echelle, 2 * echelle, -255, 150);

        // dessiner le cercle du millieu
        Ellipse2D.Double cercle = new Ellipse2D.Double(2.5 * echelle, 2.5 * echelle, 1 * echelle, 1 * echelle);
        g.draw(cercle);

        // dessiner le grand plus sur le terrain
        g.drawLine(1 * echelle, 3 * echelle, 5 * echelle, 3 * echelle);
        g.drawLine(3 * echelle, 1 * echelle, 3 * echelle, 5 * echelle);
    }
}
