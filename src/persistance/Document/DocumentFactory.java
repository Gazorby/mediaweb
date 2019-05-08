package persistance.Document;

import mediatheque.Document;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DocumentFactory {

    /**
     * Document getter
     * @param name, name of the document
     * @param type, type of the document
     * @return Return a document with given name and type
     */
    public Document getDocument(String name, @NotNull Type type, int id) {
        switch (type) {
            case CD:
                return getCd(name, id);
            case DVD:
                return getDvd(name, id);
            default:
                return getLivre(name, id);
        }
    }

    /**
     * DVD getter
     * @param name, name of the DVD
     * @param id
     * @return, a DVD object with the corresponding name
     */
    @NotNull
    @Contract("_, _ -> new")
    private Document getDvd(String name, int id) {
        return new Dvd(name,id);
    }

    /**
     * CD getter
     * @param name, name of the CD
     * @param id
     * @return, a CD object with the corresponding name
     */
    @NotNull
    @Contract("_, _ -> new")
    private Document getCd(String name, int id) {
        return new Cd(name,id);
    }

    /**
     * Livre getter
     * @param name, name of the Livre
     * @param id
     * @return, a Livre object with the corresponding name
     */
    @NotNull
    @Contract("_, _ -> new")
    private Document getLivre(String name, int id) {
        return new Livre(name,id);
    }

}
