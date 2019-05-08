package persistance;

import mediatheque.Utilisateur;

public class Bibliothecaire implements Utilisateur {

    private boolean isBibliothecaire;

    public Bibliothecaire(boolean isBibliothecaire) {
        this.isBibliothecaire = isBibliothecaire;
    }

    @Override
    public boolean isBibliothecaire() {
        return isBibliothecaire;
    }

}
