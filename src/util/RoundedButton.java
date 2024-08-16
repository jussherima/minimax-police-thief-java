package util;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class RoundedButton extends JButton {
    private static final int ARC_WIDTH = 20; // Largeur du rayon d'arc
    private static final int ARC_HEIGHT = 20; // Hauteur du rayon d'arc

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false); // Désactiver le remplissage par défaut
        setFocusPainted(false); // Désactiver le contour de focus
        setBorderPainted(false); // Désactiver le contour du bouton
        setForeground(Color.WHITE); // Couleur du texte
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.DARK_GRAY); // Couleur lorsque le bouton est pressé
        } else if (getModel().isRollover()) {
            g.setColor(Color.LIGHT_GRAY); // Couleur lorsque le bouton est survolé
        } else {
            g.setColor(Color.RED); // Couleur par défaut
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);

        // Dessiner le texte
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Pas de bordure
    }
}
