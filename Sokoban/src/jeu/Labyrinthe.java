package jeu;

/**
 * Classe représentant le labyrinthe du jeu.
 **/
public class Labyrinthe {
    /**
    Tableau à deux dimensions représentant les murs du labyrinthe.
    */
    private boolean[][] murs;

    /**
     * Constante représentant un mur dans le labyrinthe.
     */
    public final static char MUR = '#';

    /**
     * Constante représentant une caisse dans le labyrinthe.
     */
    public final static char CAISSE = '$';

    /**
     * Constante représentant le personnage joueur dans le labyrinthe.
     */
    public final static char PJ = '@';

    /**
     * Constante représentant un dépôt dans le labyrinthe.
     */
    public final static char DEPOT = '.';

    /**
     * Constante représentant une case vide dans le labyrinthe.
     */
    public final static char VIDE = ' ';

    /**
     * Constructeur de la classe Labyrinthe.
     *
     * @param m Tableau à deux dimensions représentant les murs du labyrinthe.
     */
    public Labyrinthe(boolean[][] m) {
        this.murs = m;
    }

    /**
     * Retourne le tableau à deux dimensions représentant les murs du labyrinthe.
     *
     * @return Le tableau à deux dimensions représentant les murs du labyrinthe.
     */
    public boolean[][] getMurs() {
        return this.murs;
    }

    /**
     * Vérifie si la case aux coordonnées (x, y) est un mur ou non.
     *
     * @param x Coordonnée x de la case à vérifier.
     * @param y Coordonnée y de la case à vérifier.
     * @return Un booléen représentant si la case est un mur ou non.
     */
    public boolean etreMur(int x, int y) {
        return this.murs[x][y];
    }
}