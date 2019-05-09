package persistance;
import mediatheque.Utilisateur;
import java.util.List;

public class User implements Utilisateur {

    private boolean isBibliothecaire;
    private List<Integer> docEmpruntes;
    private String login;

    public User(String login, boolean isBibliothecaire, List<Integer> docEmpruntes) {
        this.isBibliothecaire = isBibliothecaire;
        this.docEmpruntes = docEmpruntes;
        this.login = login;
    }

    /**
     * is Bibliothecaire getter
     * @return True if user is bibliothecaire, False otherwise
     */
    @Override
    public boolean isBibliothecaire() {
        return isBibliothecaire;
    }

    /**
     * Return formatted string listing documents id borrowed by the user
     * If the user isn't bibliothecaire
     * @return String of borrowed documents ids
     */
    @Override
    public String toString () {
        if (! isBibliothecaire && docEmpruntes != null) {
            // Remove brackets of the string representation of the array [.., .., ] => .., ..,
            String res = docEmpruntes.toString().substring(1, docEmpruntes.toString().length() - 1) + ", " + login;
            return res;
        }
        else {
            return null;
        }
    }
}
