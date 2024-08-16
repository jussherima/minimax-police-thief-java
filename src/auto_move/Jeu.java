package auto_move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import joueur.Police;
import joueur.Voleur;
import terrain.Terrain;
import util.Noeud;

public class Jeu implements Cloneable {
    public List<Police> police;
    public Voleur voleur;

    public Jeu(List<Police> police, Voleur voleur) {
        this.police = police;
        this.voleur = voleur;
    }

    // mijery hoe efa tapitra ve le jeu
    public int terminal_state() throws Exception {
        Noeud noeud = Noeud.distance(voleur.getPosition());
        List<Noeud> voisin = noeud.voisin;
        int etat = 1; // la valeur de l'etat par defaut

        // verifie si le police a gagner
        for (Noeud v : voisin) {
            if (!voleur.issetPolice(police, v)) {
                etat = 0;
                break;
            }
        }

        // verfie si le voleur a gagner
        if (noeud.position.getX() == 3 * Terrain.echelle) {
            if (noeud.position.getY() == 3 * Terrain.echelle) {
                etat = -1;
            }
        }

        if (etat == 0) {
            throw new Exception("jeu n'est pas terminer");
        }

        // retourne l'etat du jeu final
        return etat;
    }

    // action possible pour une seul police
    public List<Jeu> action_possible(Police p1) {
        List<Jeu> liste_jeu = new ArrayList<>(); // liste an'le jeu ho retournena

        List<Noeud> voisin_possible = new ArrayList<>();
        // maka ny mvt possibles rehetra ho an'ny police 1
        List<Noeud> p1_voisin = Noeud.distance(p1.getPosition()).voisin;

        // mijery raha misy joueur eo amin'le voisiany
        for (Noeud voisin : p1_voisin) {
            boolean issetPersonne = false;
            // mijery raha misy voleur
            if (p1.issetJoueur(voisin, voleur)) {
                issetPersonne = true;
            }
            // jerena raha misy police le lalana
            for (Police p : police) {
                if (p1.issetJoueur(voisin, p)) {
                    issetPersonne = true;
                    break;
                }
            }
            // miajouter anay liste raha toa ka afaka aleha le place
            if (!issetPersonne) {
                voisin_possible.add(voisin);
            }
        }

        // mtransformez an'le liste an noeud ho lasa liste jeu
        for (Noeud noeud : voisin_possible) {
            // maka ny liste an'ny police rehetra
            List<Police> police_jeu = new ArrayList<>();

            for (Police police_actuelle : police) {
                if (police_actuelle != p1) {
                    police_jeu.add(new Police(police_actuelle.getPosition()));
                } else {
                    police_jeu.add(new Police(noeud.position));
                }

            }

            Jeu j = new Jeu(police_jeu, voleur);
            liste_jeu.add(j);
        }
        return liste_jeu;
    }

    // action possible pour chaque police
    public List<Jeu> action_possible() {
        List<Jeu> liste_jeu = new ArrayList<>();
        for (Police p : police) {
            liste_jeu.addAll(action_possible(p));
        }
        // System.out.println("io ny action possible ho an'le police
        // "+liste_jeu.size());

        return liste_jeu;
    }

    // action possilbe pour le voleur
    public List<Jeu> action_possible(Voleur v) {
        List<Jeu> liste_jeu = new ArrayList<>(); // liste an'le jeu ho retournena

        List<Noeud> voisin_possible = new ArrayList<>();
        // maka ny mvt possibles rehetra ho an'ny voleur
        List<Noeud> v_voisin = Noeud.distance(v.getPosition()).voisin;

        // mijery raha misy joueur eo amin'le voisiany
        for (Noeud voisin : v_voisin) {
            boolean issetPersonne = false;

            // jerena raha misy police le lalana
            for (Police p : police) {
                if (v.issetJoueur(voisin, p)) {
                    issetPersonne = true;
                    break;
                }
            }
            // miajouter anay liste raha toa ka afaka aleha le place
            if (!issetPersonne) {
                voisin_possible.add(voisin);
            }
        }

        // mtransformez an'le liste an noeud ho lasa liste jeu
        for (Noeud noeud : voisin_possible) { // maka ny noeud possible rehetra
            Jeu j = new Jeu(police, new Voleur(noeud.position));
            liste_jeu.add(j);
        }

        return liste_jeu;
    }

    // function minimax 2
    public int[] minimax2(int profondeur, boolean tour_du_police) {
        int[] retour = new int[2];
        try {
            int state = terminal_state();
            // dans le cas ou il existe un state
            retour[0] = state;
            retour[1] = profondeur;
            // retourner l'etat final du jeu
            return retour;
        } catch (Exception e) {
            // dans le cas ou le jeu est encore en cours
        }
        profondeur++;

        // raha ohatra ka lasa lavitra loatra le izy
        if (profondeur == 8) {
            retour[0] = 0;
            retour[1] = 8;
            return retour;
        }
        // prise de decision de la police
        if (tour_du_police) {
            // valeur par defaut du retour
            int[] valeur = new int[2];
            valeur[0] = Integer.MIN_VALUE;
            valeur[1] = Integer.MIN_VALUE;

            // voir tous les action possible
            // System.out.println("longeur des possibilite du police
            // "+action_possible().size());
            for (Jeu possible : action_possible()) {
                // prendre la valeur maximal avec le meilleur profondeur
                int[] minimax_val = possible.minimax2(profondeur, false);
                // possible.affiche_frame("profondeur = " + profondeur + " et minimax = " +
                // minimax_val[0]);

                if (minimax_val[0] == 1 && minimax_val[1] < 3) {
                    // possible.affiche_frame("profondeur = " + profondeur);
                }
                // maka ny valeur lehibe ndrindra foan
                if (valeur[0] < minimax_val[0]) {
                    // alaina raha misy possiblite
                    valeur = minimax_val;
                }
                // raha tao ka mitovy le valeur nefa le ray mbola tsara kokoa
                if (valeur[0] == minimax_val[0]) {
                    // maka ny prodondeur kely indrindra
                    if (valeur[1] > minimax_val[1]) {
                        valeur = minimax_val;
                    }
                }
            }
            return valeur;
        } else {
            // voir tous les action possible
            int[] valeur = new int[2];
            valeur[0] = Integer.MAX_VALUE;
            valeur[1] = Integer.MAX_VALUE;
            for (Jeu possible : action_possible(voleur)) {
                // prendre la valeur maximal avec le meilleur profondeur
                int[] minimax_val = possible.minimax2(profondeur, true);
                if (valeur[0] == minimax_val[0]) {
                    if (valeur[1] > minimax_val[1]) {
                        valeur = minimax_val;
                    }
                }
                if (valeur[0] > minimax_val[0]) {
                    valeur = minimax_val;
                }
            }
            return valeur;
        }
    }

    public int[] minimaxIteratif(int profondeurMax) {
        Stack<MinimaxNode> stack = new Stack<>();
        Map<Jeu, int[]> memo = new HashMap<>();
        int[] meilleurValeur = { Integer.MIN_VALUE, Integer.MIN_VALUE };

        // Initialisation de la pile avec le jeu actuel
        stack.push(new MinimaxNode(this, 0, true));

        while (!stack.isEmpty()) {
            MinimaxNode currentNode = stack.pop();
            Jeu jeuActuel = currentNode.jeu;
            int profondeur = currentNode.profondeur;
            boolean tour_du_police = currentNode.tour_du_police;

            // Vérifiez si l'état est terminal ou si la profondeur maximale est atteinte
            try {
                int state = jeuActuel.terminal_state();
                int[] valeur = { state, profondeur };
                memo.put(jeuActuel, valeur);
            } catch (Exception e) {
                if (profondeur >= profondeurMax) {
                    int[] valeur = { 0, profondeur };
                    memo.put(jeuActuel, valeur);
                    continue;
                }

                profondeur++;
                List<Jeu> actions = tour_du_police ? jeuActuel.action_possible()
                        : jeuActuel.action_possible(jeuActuel.voleur);
                for (Jeu action : actions) {
                    stack.push(new MinimaxNode(action, profondeur, !tour_du_police));
                }
            }
        }

        // Calculer la meilleure valeur à partir des résultats mémorisés
        for (Map.Entry<Jeu, int[]> entry : memo.entrySet()) {
            Jeu jeu = entry.getKey();
            int[] valeur = entry.getValue();
            if (meilleurValeur[0] < valeur[0] || (meilleurValeur[0] == valeur[0] && meilleurValeur[1] > valeur[1])) {
                meilleurValeur = valeur;
            }
        }

        return meilleurValeur;
    }

    public void affiche_frame(String titre) {
        new Conception(titre, this);
    }

    @Override
    public Jeu clone() {
        try {
            return (Jeu) super.clone();
        } catch (Exception e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

    private static class MinimaxNode {
        Jeu jeu;
        int profondeur;
        boolean tour_du_police;

        MinimaxNode(Jeu jeu, int profondeur, boolean tour_du_police) {
            this.jeu = jeu;
            this.profondeur = profondeur;
            this.tour_du_police = tour_du_police;
        }
    }

    // afficher l'etat du jeu en chaine de caractere
    public void afficher_jeu() {
        System.out.println("=========================================");
        System.out.println("position du police 1 (" + police.get(0).getPosition().getX() / Terrain.echelle + ","
                + police.get(0).getPosition().getY() / Terrain.echelle + ")");
        System.out.println("position du police 2 (" + police.get(1).getPosition().getX() / Terrain.echelle + ","
                + police.get(1).getPosition().getY() / Terrain.echelle + ")");
        System.out.println("position du police 3 (" + police.get(2).getPosition().getX() / Terrain.echelle + ","
                + police.get(2).getPosition().getY() / Terrain.echelle + ")");
        System.out.println(
                "position du voleur (" + voleur.getPosition().getX() / Terrain.echelle + ","
                        + voleur.getPosition().getY() / Terrain.echelle + ")");
        System.out.println("=========================================");
    }
}