package jeu;

/**
 * Classe gérant l'exception ActionInconnueException
 */
public class ActionInconnueException extends Exception {

    /**
     * @param message Le message renvoyé par l'exception.
     * @throws ActionInconnueException Une exception qui se déclenche lorsque l'action effectuée par le joueur est inconnue
     */
    public ActionInconnueException(String message) {
        super(message);
    }

}
