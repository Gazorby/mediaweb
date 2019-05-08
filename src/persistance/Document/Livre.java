package persistance.Document;

public class Livre extends ADocument {
    Livre(String name, int id) {
        super(name,id);
        this.type = Type.LIVRE;
    }
}
