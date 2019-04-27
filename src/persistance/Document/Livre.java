package persistance.Document;

public class Livre extends ADocument {
    Livre(String name) {
        super(name);
    }

    @Override
    public Object[] affiche() {
        String[] tmp = new String[1];
        tmp[0] = name;
        return tmp;
    }
}
