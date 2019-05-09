package persistance.Document;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

import javax.print.Doc;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class ADocument implements Document {

    String name;
    Type type;
    int id;
    Utilisateur user;

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Utilisateur getUser() {
        return user;
    }


    ADocument(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void emprunter(Utilisateur utilisateur) throws EmpruntException {

        if (user != null) {
            throw new EmpruntException();
        }

        else if (user == utilisateur)

        this.user = utilisateur;
        update();

    }

    @Override
    public void retour() {
        this.user = null;
        update();
    }


    public int getId() {
        return id;
    }

    private void update() {
        Mediatheque.getInstance().nouveauDocument(id, this);
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
