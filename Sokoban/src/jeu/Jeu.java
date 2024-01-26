package jeu;

import java.util.Iterator;

/**
 * Classe permettant le bon fonctionnement de la partie
 */
public class Jeu {
    /*Attributs*/

    private Labyrinthe laby;
    private Perso perso;
    private ListeElements caisses, depots;
    /**
     * Constante permettant un déplacement vers le haut
     */
    public final static String HAUT="Haut";

    /**
     * Constante permettant un déplacement vers le bas
     */
    public final static String BAS="Bas";

    /**
     * Constante permettant un déplacement vers la gauche
     */
    public final static String GAUCHE="Gauche";
    /**
     * Constante permettant un déplacement vers la droite
     */
    public final static String DROITE="Droite";

    /*Constructeur*/

    /**
     * Constructeur de la classe Jeu.
     * @param p Le personnage jouable
     * @param lc La liste d'élément contenant les caisses
     * @param ld La liste d'élément contenant les dépots
     * @param lab Le labyrinthe avec les murs et la position des différents éléments
     */
    public Jeu(Perso p , ListeElements lc, ListeElements ld, Labyrinthe lab){
        this.laby=lab;
        this.caisses=lc;
        this.depots=ld;
        this.perso=p;
    }

    /**
     * Affiche l'état courant du jeu dans la console
     * @return Le labyrinthe avec les différents éléments (murs, caisses, dépots, personnage)
     */
    public String jeuToString(){
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < this.laby.getMurs().length; i++) {
            for (int j = 0; j < this.laby.getMurs()[i].length; j++) {
                sb.append(getChar(i,j));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Cette méthode retourne le caractère à la position (x,y)
     * - $ pour une caisse,
     * - @ pour le personnage,
     * - . pour un dépot,
     * - ' ' pour un vide,
     * - # pour un mur
     * @param x La coordonnée des lignes
     * @param y La coordonée des colones
     * @return Le caractère à la position (x,y)
     */
    public char getChar(int x, int y) {
        char c = Labyrinthe.VIDE;
        if (laby.getMurs()[x][y]) {
            c = Labyrinthe.MUR;
        }
        else if (perso.getX() == x && perso.getY() == y) {
            c = Labyrinthe.PJ;
        }
        else {
            if (this.caisses.getElement(x, y) != null) {
                c = Labyrinthe.CAISSE;
            }
            else if (this.depots.getElement(x, y) != null){
                c = Labyrinthe.DEPOT;
            }
        }
        return c;
    }

    /**
     * Retourne les coordonnées suivantes en fonction de l'action du joueur
     * @param x La coordonnée x actuelle du personnage
     * @param y La coordonnée y actuelle du personnage
     * @param action L'action de déplacement jouée par le joueur
     * @return La prochaine position du personnage en fonction de l'action donnée
     */
    public static int[] getSuivant(int x, int y,String action){
        int[] coord = {x, y};
        switch (action){
            case Jeu.HAUT:
                coord[0]--;
                break;
            case Jeu.BAS:
                coord[0]++;
                break;
            case Jeu.GAUCHE:
                coord[1]--;
                break;
            case Jeu.DROITE:
                coord[1]++;
                break;
        }
        return coord;
    }

    /**
     * Cette méthode déplace le personnage en fonction de l'action donnée et des éléments externes.
     * Le personnage se déplace si :
     *  - il n'y a pas de murs sur sa prochaine case
     *  - la case suivante est vide ou un dépot
     *  - la case suivante a une caisse mais n'a ni mur ni une autre caisse derrière,
     *    dans ce cas il pousse la caisse et prend sa place
     * @param action L'action de déplacement jouée par le joueur
     */
    public void deplacerPerso(String action) {
        try {
            int[] coordSuiv1 = getSuivant(perso.getX(), perso.getY(), action); // Initialise les coordonnées suivantes du Perso
            if ( (perso.getX() == coordSuiv1[0]) && (perso.getY() == coordSuiv1[1]) ) { //
                throw new ActionInconnueException("Action inconnue");
            }
            if ( !laby.etreMur(coordSuiv1[0],coordSuiv1[1]) ) { // Teste si il n'y a pas de mur
                if (getChar(coordSuiv1[0], coordSuiv1[1]) != Labyrinthe.CAISSE) { // Teste si il n'y a pas de caisse
                    perso.setX(coordSuiv1[0]);
                    perso.setY(coordSuiv1[1]);
                } else { // Lorsqu'il n'y a pas de mur, mais il y a une caisse
                    // Mouvement de la caisse
                    int[] coordSuiv2 = getSuivant(coordSuiv1[0], coordSuiv1[1], action);
                    if (!laby.etreMur(coordSuiv2[0], coordSuiv2[1])) { // Teste si il n'y a pas de mur
                        if (getChar(coordSuiv2[0], coordSuiv2[1]) != Labyrinthe.CAISSE) { // Teste si il n'y a pas de caisse après
                            Caisse c = (Caisse)(caisses.getElement(coordSuiv1[0], coordSuiv1[1])); // NULL
                            c.setX(coordSuiv2[0]);
                            c.setY(coordSuiv2[1]);
                            perso.setX(coordSuiv1[0]);
                            perso.setY(coordSuiv1[1]);
                        }
                    }
                }
            }
        }
        catch (ActionInconnueException e1) {
            e1.getMessage();
        }
    }

    /**
     * Méthode qui permet de savoir si le jeu est fini ou non 
     * @return true si tous les dépôts sont comblés par une caisse, false sinon
     */
    public boolean etreFini() {
        boolean pasFin =true;
        Iterator<Element> i1 = this.caisses.getTab().iterator();
        Iterator<Element> i2 = this.depots.getTab().iterator();
        while (i1.hasNext() && pasFin) {
            if (!( i1.next().equals(i2.next()) ) ) {
                pasFin = false;
            }
        }
        return pasFin;
    }

    public Labyrinthe getLaby() {
        return laby;
    }

    public Perso getPerso() {
        return perso;
    }

    public ListeElements getCaisses() {
        return caisses;
    }

    public ListeElements getDepots() {
        return depots;
    }
}