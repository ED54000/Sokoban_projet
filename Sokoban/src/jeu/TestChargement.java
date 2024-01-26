package jeu;

import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestChargement {

    /**
     * Test de la méthode chargerJeu pour un fichier dont le contenu permet de jouer à Sokoban
     * @throws Exception si il y a un quelconque problème lié à la lecture du fichier
     */
    @Test
    public void test_chargerFichieCorrect() throws Exception {
        Jeu j = Chargement.chargerJeu("laby/laby_test.txt");
        String labyCorrecte =
                "    #####          \n" +
                        "    #   #          \n" +
                        "    #$  #          \n" +
                        "  ###  $##         \n" +
                        "  #  $ $ #         \n" +
                        "### # ## #   ######\n" +
                        "#   # ## #####  ..#\n" +
                        "# $  $          ..#\n" +
                        "##### ### #@##  ..#\n" +
                        "    #     #########\n" +
                        "    #######        \n";
        assertEquals(labyCorrecte, j.jeuToString(), "le labyrinthe chargé est incorrect");
    }

    /**
     * Test de la méthode chargerJeu avec un fichier ne contenant pas de joueur (@)
     * @throws Exception si il y a un quelconque problème lié à la lecture du fichier
     */
    @Test
    public void test_chargerFichierSansJoueur() throws Exception {
        String errMessage = "";
        try {
            Jeu j = Chargement.chargerJeu("laby/laby_sansJoueur.txt");
        }
        catch (FichierIncorrectException e1) {
            errMessage = e1.getMessage();
        }
        assertEquals("personnage inconnu", errMessage, "Message incorrect");
    }

    /**
     * Test de la méthode chargerJeu avec un fichier sans caisses ($)
     * @throws Exception si il y a un quelconque problème lié à la lecture du fichier
     */
    @Test
    public void test_chargerFichierSansCaisses() throws Exception {
        String errMessage = "";
        try {
            Jeu j = Chargement.chargerJeu("laby/laby_sansCaisses.txt");
        }
        catch (FichierIncorrectException e1) {
            errMessage = e1.getMessage();
        }
        assertEquals("caisses inconnues", errMessage, "Message incorrect");
    }

    /**
     * Test de la méthode chargerJeu avec un fichier contenant plus de caisses que de dépôts ($ > .)
     * @throws Exception si il y a un quelconque problème lié à la lecture du fichier
     */
    @Test
    public void test_chargerFichierPlusDeCaisses() throws Exception {
        String errMessage = "";
        try {
            Jeu j = Chargement.chargerJeu("laby/laby_plusCaisses.txt");
        }
        catch (FichierIncorrectException e1) {
            errMessage = e1.getMessage();
        }
        assertEquals("Caisses(2) Depots(1)", errMessage, "Message incorrect");
    }

    /**
     * Test de la méthode chargerJeu avec un fichier contenant plus de dépôts que de caisses (. > $)
     * @throws Exception si il y a un quelconque problème lié à la lecture du fichier
     */
    @Test
    public void test_chargerFichierPlusDeDepots() throws Exception {
        String errMessage = "";
        try {
            Jeu j = Chargement.chargerJeu("laby/laby_plusDepots.txt");
        }
        catch (FichierIncorrectException e1) {
            errMessage = e1.getMessage();
        }
        assertEquals("Caisses(1) Depots(2)", errMessage, "Message incorrect");
    }

    /**
     * Test de la méthode chargerJeu avec un fichier contenant un caratère inconnu (&)
     * @throws Exception si il y a un quelconque problème lié à la lecture du fichier
     */
    @Test
    public void test_chargerFichierCharInconnu() throws Exception {
        String errMessage = "";
        try {
            Jeu j = Chargement.chargerJeu("laby/laby_charInconnu.txt");
        }
        catch (FichierIncorrectException e1) {
            errMessage = e1.getMessage();
        }
        assertEquals("caractere inconnu &", errMessage, "Message incorrect");
    }

}





