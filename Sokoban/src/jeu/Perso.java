package jeu;

/**
 * Classe représentant le gardien que le joueur peut déplacer
 */
public class Perso extends Element {
    /**
     * @param px La position en x du personnage
     * @param py La position en y du personnage
     */
    public Perso(int px, int py) {
        super(px, py);
    }

    /**
     * Constructeur vide qui initialise le personnage à la coordonnée (0,0)
     */
    public Perso() {
        super(0, 0);
    }

}
