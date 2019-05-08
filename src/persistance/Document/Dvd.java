package persistance.Document;

public class Dvd extends ADocument {

    Dvd(String name, int id) {
        super(name,id);
        this.type=Type.DVD;
    }


}
