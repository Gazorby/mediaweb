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
}
