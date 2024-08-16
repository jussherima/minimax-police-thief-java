package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomFrame extends JPanel implements ActionListener {
    private JLabel backgroundLabel;
    private JButton startGame;

    public WelcomFrame() {
        setLayout(null);

        // Ajouter un label text
        String text = "Hello, welcome to the simple police and ";
        String text2 = "thief 2D game using  Java Swing Libraries";
        JLabel labelText = new JLabel(text);
        JLabel labelText2 = new JLabel(text2);
        labelText.setFont(new Font("Serif", Font.BOLD, 28)); // Réduire la taille de la police pour un meilleur
        labelText2.setFont(new Font("Serif", Font.BOLD, 28)); // Réduire la taille de la police pour un meilleur
        labelText.setForeground(Color.WHITE); // Mettre le texte en blanc pour qu'il soit visible sur un fond sombre
        labelText.setBounds(10, 10, 780, 50); // Ajuster les dimensions pour permettre l'affichage complet du texte
        labelText2.setForeground(Color.WHITE); // Mettre le texte en blanc pour qu'il soit visible sur un fond sombre
        labelText2.setBounds(40, 60, 780, 50); // Ajuster les dimensions pour permettre l'affichage complet du texte

        // Propriétés du bouton arrondi
        startGame = new JButton("Start Game");
        startGame.addActionListener(this);
        startGame.setForeground(Color.WHITE);
        startGame.setBackground(new Color(100, 50, 20));
        startGame.setBounds(300, 500, 200, 75);
        startGame.setBorderPainted(false);
        startGame.setFocusPainted(false);
        startGame.setFont(new Font("Serif", Font.ITALIC, 20));

        // Propriétés du JLabel pour l'image de fond
        backgroundLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon("../image/image_bac_egypte.jpg");

        // Redimensionner l'image
        Image image = originalIcon.getImage();
        Image resizedImage = image.getScaledInstance(800, 750, Image.SCALE_SMOOTH); // Ajuster la taille souhaitée
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Définir l'icône redimensionnée sur le JLabel
        backgroundLabel.setIcon(resizedIcon);
        backgroundLabel.setBounds(0, 0, 800, 710);

        // Ajouter les composants
        add(labelText);
        add(labelText2);
        add(backgroundLabel);
        add(startGame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JFrame f = (JFrame) (((JButton) e.getSource()).getParent().getParent().getParent().getParent().getParent());
            // f.setContentPane(new Main());
            f.setContentPane(new Main());
            System.out.println("reussi ");
        } catch (Exception e1) {
            System.out.println("ereur");
        }
    }

}
