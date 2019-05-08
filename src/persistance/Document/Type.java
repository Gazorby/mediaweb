package persistance.Document;

public enum Type {
    CD("cd"),
    DVD("dvd"),
    LIVRE("livre");

    private String name;

    Type(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Type toType(String t) {
        if (t.equals("dvd"))
            return DVD;
        else if (t.equals("cd"))
            return CD;
        else if (t.equals("livre"))
            return LIVRE;
        return null;
    }
}
