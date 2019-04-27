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
    public Document getDocument(String name, @NotNull Type type) {
        switch (type) {
            case CD:
                return getCd(name);
            case DVD:
                return getDvd(name);
            default:
                return getLivre(name);
        }
    }

    /**
     * DVD getter
     * @param name, name of the DVD
     * @return, a DVD object with the corresponding name
     */
    @NotNull
    @Contract("_ -> new")
    private Document getDvd(String name) {
        return new Dvd(name);
    }

    /**
     * CD getter
     * @param name, name of the CD
     * @return, a CD object with the corresponding name
     */
    @NotNull
    @Contract("_ -> new")
    private Document getCd(String name) {
        return new Cd(name);
    }

    /**
     * Livre getter
     * @param name, name of the Livre
     * @return, a Livre object with the corresponding name
     */
    @NotNull
    @Contract("_ -> new")
    private Document getLivre(String name) {
        return new Livre(name);
    }

}
