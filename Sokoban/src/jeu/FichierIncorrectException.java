package jeu;

/**
 * Classe gérant l'exception FichierIncorrectException
 */
public class FichierIncorrectException extends Exception {
    /**
     * @param message Le message renvoyé par l'exception.
     * @throws FichierIncorrectException Une exception qui se déclenche lorsque l'action effectuée par le joueur est inconnue
     */
    public FichierIncorrectException(String message) {
        super(message);
    }

}
