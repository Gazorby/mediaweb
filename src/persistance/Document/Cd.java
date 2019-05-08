package persistance.Document;

public class Cd extends ADocument {

    Cd(String name, int id) {
        super(name, id);
        this.type=Type.CD;
        this.id =id;
    }
}
