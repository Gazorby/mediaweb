package persistance.Document;

import mediatheque.Utilisateur;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DocumentFactory {

    /**
     * Document getter
     * @param name, name of the document
     * @param type, type of the document
     * @param user
     * @return Return a document with given name and type
     */
    public ADocument getDocument(String name, @NotNull Type type, int id, Utilisateur user) {
        switch (type) {
            case CD:
                return getCd(name, id, user);
            case DVD:
                return getDvd(name, id, user);
            default:
                return getLivre(name, id, user);
        }
    }

    /**
     * DVD getter
     * @param name, name of the DVD
     * @param id
     * @param user
     * @return, a DVD object with the corresponding name
     */
    @NotNull
    @Contract("_, _, _ -> new")
    private ADocument getDvd(String name, int id, Utilisateur user) {
        return new Dvd(name,id);
    }

    /**
     * CD getter
     * @param name, name of the CD
     * @param id
     * @param user
     * @return, a CD object with the corresponding name
     */
    @NotNull
    @Contract("_, _, _ -> new")
    private ADocument getCd(String name, int id, Utilisateur user) {
        return new Cd(name,id);
    }

    /**
     * Livre getter
     * @param name, name of the Livre
     * @param id
     * @param user
     * @return, a Livre object with the corresponding name
     */
    @NotNull
    @Contract("_, _, _ -> new")
    private ADocument getLivre(String name, int id, Utilisateur user) {
        return new Livre(name,id);
    }

}
