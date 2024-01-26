package jeu;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe permettant le chargement de la partie
 */
public class Chargement {

    /**
     * Constructeur de la classe Chargement
     * @param nomFichier le nom du fichier à charger
     * @return le jeu chargé depuis le fichier
     * @throws FichierIncorrectException si le fichier est incorrect
     * @throws Exception                 si une exception est levée lors de la lecture du fichier
     */
    public static Jeu chargerJeu(String nomFichier) throws Exception {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(nomFichier));
            String ligne = bf.readLine();
            int l = 0;
            char car;
            boolean[][] murs = new boolean[nbLignes(nomFichier)][nbCol(nomFichier)];
            Perso p = null;
            ArrayList<Element> lCaisses = new ArrayList<>();
            ArrayList<Element> lDepots = new ArrayList<>();
            Labyrinthe lab;

            while (ligne != null) {
                int col = 0;
                while (col < ligne.length()) {
                    car = ligne.charAt(col);
                    switch (car) {
                        case Labyrinthe.PJ:
                            p = new Perso(l, col);
                            break;
                        case Labyrinthe.MUR:
                            murs[l][col] = true;
                            break;
                        case Labyrinthe.CAISSE:
                            lCaisses.add(new Caisse(l, col));
                            break;
                        case Labyrinthe.DEPOT:
                            lDepots.add(new Depot(l, col));
                            break;
                        case Labyrinthe.VIDE:
                            break;
                        default:
                            throw new FichierIncorrectException("caractere inconnu " + car);
                    }
                    col++;
                }
                l++;
                ligne = bf.readLine();
            }
            bf.close();
            if (lCaisses.size() == 0) {
                throw new FichierIncorrectException("caisses inconnues");
            }
            if (p == null) {
                throw new FichierIncorrectException("personnage inconnu");
            }
            if (lCaisses.size() != lDepots.size()) {
                throw new FichierIncorrectException("Caisses(" + lCaisses.size() + ") Depots(" + lDepots.size() + ")");
            }
            lab = new Labyrinthe(murs);
            return new Jeu(p, new ListeElements(lCaisses), new ListeElements(lDepots), lab);
        } catch (FileNotFoundException e1) {
            System.out.println(e1.getMessage());
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
        }
        return null;
    }

    /**
     * Retourne le nombre de colonnes du labyrinthe contenu dans le fichier spécifié.
     *
     * @param nomFichier le nom du fichier contenant le labyrinthe
     * @return le nombre de colonnes du labyrinthe
     * @throws Exception si le fichier n'existe pas ou la lecture de ce dernier est impossible
     */
    private static int nbCol(String nomFichier) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(nomFichier));
        String ligne = bf.readLine();
        int maxCol = 0;
        while (ligne != null) {
            int col = ligne.length();
            if (col > maxCol) {
                maxCol = col;
            }
            ligne = bf.readLine();
        }
        bf.close();
        return maxCol;
    }

    /**
     * Retourne le nombre de lignes du labyrinthe contenu dans le fichier spécifié.
     *
     * @param nomFichier le nom du fichier contenant le labyrinthe
     * @return le nombre de lignes du labyrinthe
     * @throws Exception si le fichier n'existe pas ou la lecture de ce dernier est impossible
     */
    private static int nbLignes(String nomFichier) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(nomFichier));
        String ligne;
        int nbfois = 0;
        while ((ligne = bf.readLine()) != null) {
            nbfois++;
        }
        return nbfois;
    }

}