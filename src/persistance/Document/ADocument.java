package persistance.Document;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public abstract class ADocument implements Document {

    String name;
    Type type;
    int id;

    ADocument(String name, int id) {
        this.name = name;
        this.id = id;
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
    public Object[] affiche() {
        String[] tmp = new String[3];
        tmp[0] = name;
        tmp[1] = type.toString();
        tmp[2] = Integer.toString(id);
        return tmp;
    }
}
