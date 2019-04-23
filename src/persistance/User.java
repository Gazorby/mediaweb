package persistance;

import mediatheque.Utilisateur;

public class User implements Utilisateur {

    private boolean isBibliothecaire;

    public User(    boolean isBibliothecaire) {

        this.isBibliothecaire = isBibliothecaire;
    }

    @Override
    public boolean isBibliothecaire() {
        return isBibliothecaire;
    }

}
