package jeu;

import java.util.ArrayList;

/**
 * Classe représentant une liste d'éléments jouables tel que des caisses ou dépots
 */
public class ListeElements {

    /* Attributs */
    private ArrayList<Element> tab;

    /**
     * Constructeur de la classe ListeElements.
     *
     * @param liste la liste d'éléments
     */
    public ListeElements(ArrayList<Element> liste) {
        this.tab = liste;
    }

    /**
     * Getter pour l'attribut tab.
     *
     * @return la liste d'éléments
     */
    public ArrayList<Element> getTab() {
        return this.tab;
    }

    /**
     * Méthode qui retourne l'élément à la position (x, y) de la liste d'éléments.
     *
     * @param x la position en abscisse de l'élément recherché
     * @param y la position en ordonnée de l'élément recherché
     * @return l'élément à la position (x, y) de la liste d'éléments, null s'il n'existe pas
     */
    public Element getElement(int x, int y) {
        for (Element elemCourant : this.tab) {
            if ((elemCourant.getX() == x) && (elemCourant.getY() == y)) {
                return elemCourant;
            }
        }
        return null;
    }
}