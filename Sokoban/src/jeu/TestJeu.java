package jeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestJeu {
    Jeu j;
    Perso perso;

    @BeforeEach
    void créationJeu() {
        Labyrinthe laby = new Labyrinthe(new boolean[][]{
                {true, true, true, true, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, true, true, true, true}
        });
        perso = new Perso(1, 1);
        // Création d'une liste de caisse
        ArrayList<Element> Lcaisses = new ArrayList<>();
        Lcaisses.add(new Caisse(2, 2));
        ListeElements ListCaisse = new ListeElements(Lcaisses);

        // Création d'une liste de dépôts
        ArrayList<Element> Ldepots = new ArrayList<>();
        Ldepots.add(new Depot(3, 3));
        ListeElements ListDepots = new ListeElements(Ldepots);
        j = new Jeu(perso, ListCaisse, ListDepots, laby);
    }


    @Test
    public void testJeuToString() {
        // Initialisation des valeurs avec @BeforeEach

        // Vérification de la chaîne de caractères retournée
        String attendu = "#####\n" +
                "#@  #\n" +
                "# $ #\n" +
                "#  .#\n" +
                "#####\n";
        assertEquals(attendu, j.jeuToString(), "Les murs ou autres éléments (caisse, perso, dépôt) ne sont pas bien positionnés ");
    }

    @Test
    public void test_GetCharPerso() {
        //Initalisation des valeurs avec @BeforeEach
        //Test
        assertEquals('@', j.getChar(1, 1), "Il devrait y avoir le personnage");
    }

    @Test
    public void test_GetCharMur() {
        //Initalisation des valeurs avec @BeforeEach
        //Test
        assertEquals('#', j.getChar(0, 0), "Il devrait y avoir un mur");

    }

    @Test
    public void test_GetCharCaisse() {
        //Initalisation des valeurs avec @BeforeEach
        //Test
        assertEquals('$', j.getChar(2, 2), "Il devrait y avoir une caisse");
    }

    @Test
    public void test_GetCharDepot() {
        //Initalisation des valeurs avec @BeforeEach
        //Test
        assertEquals('.', j.getChar(3, 3), "Il devrait y avoir un dépot");
    }

    @Test
    public void test_GetCharCaseVide() {
        //Initalisation des valeurs avec @BeforeEach
        //Test
        assertEquals(' ', j.getChar(1, 2), "Il devrait y avoir une case vide");
    }

    @Test
    public void test_GetSuivant_Haut() {
        //Initialisation des valeurs
        int[] tab = {1, 1};
        //Test
        int[] tab2 = Jeu.getSuivant(tab[0], tab[1], Jeu.HAUT);
        int[] tabTest = {0, 1};
        //Comparaison
        assertArrayEquals(tabTest, tab2, "La coordonnée x devrait avoir diminuée");
    }

    @Test
    public void test_GetSuivant_Bas() {
        //Initialisation des valeurs
        int[] tab = {1, 1};
        //Test
        int[] tab2 = Jeu.getSuivant(tab[0], tab[1], Jeu.BAS);
        int[] tabTest = {2, 1};
        //Comparaison
        assertArrayEquals(tabTest, tab2, "La coordonnée x devrait avoir augmentée");
    }

    @Test
    public void test_GetSuivant_Gauche() {
        //Initialisation des valeurs
        int[] tab = {1, 1};
        //Test
        int[] tab2 = Jeu.getSuivant(tab[0], tab[1], Jeu.GAUCHE);
        int[] tabTest = {1, 0};
        //Comparaison
        assertArrayEquals(tabTest, tab2, "La coordonnée y devrait avoir diminuée");
    }

    @Test
    public void test_GetSuivant_Droite() {
        //Initialisation des valeurs
        int[] tab = {1, 1};
        //Test
        int[] tab2 = Jeu.getSuivant(tab[0], tab[1], Jeu.DROITE);
        //Comparaison
        assertArrayEquals(new int[]{1, 2}, tab2, "La coordonnée y devrait avoir augmentée");
    }

    @Test
    public void test_DeplacerPersoOK() { //Test du déplacement du personnage sans caisse ni dépot
        //Création du labyrinthe
        Labyrinthe labyrinthe = new Labyrinthe(new boolean[][]{
                {true, true, true, true, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, true, true, true, true}
        });
        perso = new Perso(2, 2);
        // Création d'une liste de caisse
        ArrayList<Element> Lc = new ArrayList<>();

        ListeElements LCaisse = new ListeElements(Lc);

        // Création d'une liste de dépôts
        ArrayList<Element> Ldepots = new ArrayList<>();

        ListeElements ListDepots = new ListeElements(Ldepots);
        j = new Jeu(perso, LCaisse, ListDepots, labyrinthe);
        //  Tests
        j.deplacerPerso(Jeu.BAS);
        assertEquals(3, perso.getX(), "Le personnage devrait se déplacer sur l'axe x");
        assertEquals(2, perso.getY(), "Le personnage ne devrait pas se déplacer sur l'axe y");

        j.deplacerPerso(Jeu.GAUCHE);
        assertEquals(3, perso.getX(), "Le personnage ne devrait pas se déplacer sur l'axe x");
        assertEquals(1, perso.getY(), "Le personnage devrait se déplacer sur l'axe y");

        j.deplacerPerso(Jeu.DROITE);
        assertEquals(3, perso.getX(), "Le personnage ne devrait pas se déplacer sur l'axe x");
        assertEquals(2, perso.getY(), "Le personnage devrait se déplacer sur l'axe y");

        j.deplacerPerso(Jeu.HAUT);
        assertEquals(2, perso.getX(), "Le personnage devrait se déplacer sur l'axe x");
        assertEquals(2, perso.getY(), "Le personnage ne devrait pas se déplacer sur l'axe y");
// Teste s'il ne se déplace pas s'il y a un mur
        j.deplacerPerso(Jeu.BAS);
        assertEquals(3, perso.getX(), "Le personnage devrait se déplacer sur l'axe x");
        assertEquals(2, perso.getY(), "Le personnage ne devrait pas se déplacer sur l'axe y");

        j.deplacerPerso(Jeu.BAS);
        assertEquals(3, perso.getX(), "Le personnage pas devrait pas se déplacer sur l'axe x");
        assertEquals(2, perso.getY(), "Le personnage ne devrait pas se déplacer sur l'axe y");

    }

    @Test
    public void test_DeplacerPersoDepot() { //Test si le personnage se déplace sur le dépot
        //Création du labyrinthe
        Labyrinthe labyrinthe = new Labyrinthe(new boolean[][]{
                {true, true, true, true, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, true, true, true, true}
        });
        perso = new Perso(2, 2);
        // Création d'une liste de caisse
        ArrayList<Element> Lc = new ArrayList<>();

        ListeElements LCaisse = new ListeElements(Lc);

        // Création d'une liste de dépôts
        ArrayList<Element> Ldepots = new ArrayList<>();
        Ldepots.add(new Depot(3, 2));
        ListeElements ListDepots = new ListeElements(Ldepots);
        j = new Jeu(perso, LCaisse, ListDepots, labyrinthe);
        //  Tests
        j.deplacerPerso(Jeu.BAS);
        assertEquals(3, perso.getX(), "Le personnage devrait se déplacer sur l'axe x");
        assertEquals(2, perso.getY(), "Le personnage ne devrait pas se déplacer sur l'axe y");
    }


    @Test
    public void test_DeplacerPerso_Une_Caisse() { //Test si le personnage pousse la caisse puis si le mur bloque la caisse
        //Création du labyrinthe
        Labyrinthe labyrinthe = new Labyrinthe(new boolean[][]{
                {true, true, true, true, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, true, true, true, true}
        });
        perso = new Perso(1, 2);
        // Création d'une liste de caisse
        ArrayList<Element> Lc = new ArrayList<>();
        Caisse c = new Caisse(2, 2);
        Lc.add(c);
        ListeElements LCaisse = new ListeElements(Lc);

        // Création d'une liste de dépôts
        ArrayList<Element> Ldepots = new ArrayList<>();

        ListeElements ListDepots = new ListeElements(Ldepots);
        j = new Jeu(perso, LCaisse, ListDepots, labyrinthe);
        //  Test
        //Teste si la caisse et poussée
        j.deplacerPerso(Jeu.BAS);
        assertEquals(2, perso.getX(), "Le personnage devrait se déplacer sur l'axe x");
        assertEquals(2, perso.getY(), "Le personnage ne devrait pas se déplacer sur l'axe y");
        assertEquals(3, c.getX(), "La caisse devrait descendre");
        assertEquals(2, c.getY(), "La caisse ne devrait pas translater sur l'axe y");

        //Teste si la caisse est bloquée par le mur
        j.deplacerPerso(Jeu.BAS);
        assertEquals(2, perso.getX(), "Le personnage ne devrait pas se déplacer sur l'axe x");
        assertEquals(2, perso.getY(), "Le personnage ne devrait pas se déplacer sur l'axe y");
        assertEquals(3, c.getX(), "La caisse ne devrait pas descendre");
        assertEquals(2, c.getY(), "La caisse ne devrait pas translater sur l'axe y");
    }


    @Test
    public void test_DeplacerPerso_Deux_Caisses() { //Test si le personnage pousse la caisse puis si le mur bloque la caisse
        //Création du labyrinthe
        Labyrinthe labyrinthe = new Labyrinthe(new boolean[][]{
                {true, true, true, true, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, true, true, true, true}
        });
        perso = new Perso(1, 2);
        // Création d'une liste de caisse
        ArrayList<Element> Lc = new ArrayList<>();
        Caisse c1 = new Caisse(2, 2);
        Caisse c2 = new Caisse(3, 2);
        Lc.add(c1);
        Lc.add(c2);
        ListeElements LCaisse = new ListeElements(Lc);

        // Création d'une liste de dépôts
        ArrayList<Element> Ldepots = new ArrayList<>();

        ListeElements ListDepots = new ListeElements(Ldepots);
        j = new Jeu(perso, LCaisse, ListDepots, labyrinthe);
        //  Test
        //Teste si la caisse et poussée
        j.deplacerPerso(Jeu.BAS);
        assertEquals(1, perso.getX(), "Le personnage  ne devrait  pas se déplacer sur l'axe x");
        assertEquals(2, perso.getY(), "Le personnage ne devrait pas se déplacer sur l'axe y");
        assertEquals(2, c1.getX(), "La caisse ne devrait pas descendre");
        assertEquals(2, c1.getY(), "La caisse ne devrait pas translater sur l'axe y");
    }


    @Test
    public void test_pasEtreFini() {
        //Initialisation des valeurs faite avec @BeforeEach

        //Test
        boolean Fini = j.etreFini();
        // Comparaison
        assertEquals(false, Fini, "Le jeu ne devrait pas être terminé");
    }

    @Test
    public void test_EtreFini() {
        //Initialisation des valeurs faite avec @BeforeEach
        // Déplacement pour finir la partie
        j.deplacerPerso(Jeu.DROITE);
        j.deplacerPerso(Jeu.BAS);
        j.deplacerPerso(Jeu.GAUCHE);
        j.deplacerPerso(Jeu.BAS);
        j.deplacerPerso(Jeu.DROITE);
        j.deplacerPerso(Jeu.DROITE);
        //Test
        boolean Fini = j.etreFini();
        // Comparaison
        assertEquals(true, Fini, "Le jeu devrais être terminé");
    }
}