package jeu;

/**
 * La classe Element représente un élément du jeu, caractérisé par sa position dans le labyrinthe.
 **/
public class Element {
    /*Attributs*/
    private int x, y; /*La position dans le labyrinthe x représente les colones et y les lignes*/

    /**
     * Constructeur de la classe Element.
     *
     * @param px la coordonnée x de l'élément dans le labyrinthe
     * @param py la coordonnée y de l'élément dans le labyrinthe
     */
    public Element(int px, int py) {
        if (px >= 0 && py >= 0) {
            this.x = px;
            this.y = py;
        }
    }

    /**
     * Retourne la coordonnée y de l'élément.
     *
     * @return la coordonnée y de l'élément
     */
    public int getY() {
        return y;
    }

    /**
     * Retourne la coordonnée x de l'élément.
     *
     * @return la coordonnée x de l'élément
     */
    public int getX() {
        return x;
    }

    /**
     * Met à jour la coordonnée x de l'élément.
     *
     * @param nx la nouvelle coordonnée x de l'élément
     **/
    public void setX(int nx) {
        this.x = nx;
    }

    /**
     * Met à jour la coordonnée y de l'élément.
     *
     * @param ny la nouvelle coordonnée y de l'élément
     */
    public void setY(int ny) {
        this.y = ny;
    }

    /**
     * Vérifie si l'objet passé en paramètre est égal à l'élément.
     *
     * @param o l'objet à comparer à l'élément
     * @return true si l'objet est égal à l'élément, false sinon
     */
    public boolean equals(Object o) {
        if (o instanceof Element) {
            Element e = (Element) o;
             if (e.getX() == this.x && e.getY() == this.y) {
                return true;
            }
        }
        return false;
    }
}
