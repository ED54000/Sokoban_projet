package jeu;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe permettant de jouer au jeu dans la console
 */
public class MainJeu {

    public static void main(String[] args) throws Exception{

        Jeu j = Chargement.chargerJeu("laby/laby_simple.txt");
        System.out.println(j.jeuToString());
        int score = 0;
        String input;
        int[] coordSuivantes = new int[2];
        Scanner sc = new Scanner(System.in);
        while (!j.etreFini()) {

            // Liste permettant stocker les différentes actions possibles selon l'état actuel du jeu
            ArrayList<String> actionsPossibles = new ArrayList<String>();
            String[] directions = {Jeu.HAUT, Jeu.BAS, Jeu.GAUCHE, Jeu.DROITE};
            for (String dir : directions) {
                int[] coordSuiv1 = j.getSuivant(j.getPerso().getX(), j.getPerso().getY(), dir);
                if (!j.getLaby().etreMur(coordSuiv1[0], coordSuiv1[1])) { // Teste si il n'y a pas de mur
                    if (j.getChar(coordSuiv1[0], coordSuiv1[1]) != Labyrinthe.CAISSE) { // Teste si il n'y a pas de caisse
                        actionsPossibles.add(dir);
                    } else { // Lorsqu'il n'y a pas de mur, mais il y a une caisse
                        int[] coordSuiv2 = j.getSuivant(coordSuiv1[0], coordSuiv1[1], dir);
                        if (!j.getLaby().etreMur(coordSuiv2[0], coordSuiv2[1])) { // Teste si il n'y a pas de mur
                            if (j.getChar(coordSuiv2[0], coordSuiv2[1]) != Labyrinthe.CAISSE) { // Teste si il n'y a pas de caisse après
                                Caisse c = (Caisse) (j.getCaisses().getElement(coordSuiv1[0], coordSuiv1[1]));
                                actionsPossibles.add(dir);
                            }
                        }
                    }
                }
            }
            // Affiche les actions disponibles au joueur
            System.out.println("Actions possibles : \n");
            for (String poss : actionsPossibles)
                System.out.println("- " + poss +"\n");
            System.out.println("Action : ");

            input = sc.nextLine();

            switch (input) {
                case "HAUT" :
                    j.deplacerPerso(Jeu.HAUT);
                    break;
                case "BAS" :
                    j.deplacerPerso(Jeu.BAS);
                    break;
                case "DROITE" :
                    j.deplacerPerso(Jeu.DROITE);
                    break;
                case "GAUCHE" :
                    j.deplacerPerso(Jeu.GAUCHE);
                    break;
                default:
                    break;
            }
            System.out.println("Vous avez joué " + ++score + " coups.");
            System.out.println(j.jeuToString());
        }
        System.out.println("Vous avez gagné en : " + score + " coups.");
    }
}