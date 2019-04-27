package persistance.Document;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public abstract class ADocument implements Document {

    String name;

    ADocument(String name) {
        this.name = name;
    }

    @Override
    public void emprunter(Utilisateur utilisateur) throws EmpruntException {

    }

    @Override
    public void retour() {

    }

    /**
     * Return the representation of the document
     * @return an Object representing the document
     */
    @Override
    public abstract Object[] affiche();
}
