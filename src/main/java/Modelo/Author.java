package Modelo;


public class Author {
    private String idAuthor;
    private String name;
    private int citedBy;

    public Author(String idAuthor, String name, int citedBy) {
        this.idAuthor = idAuthor;
        this.name = name;
        this.citedBy = citedBy;
    }

    public String getIdAuthor() {
        return idAuthor;
    }

    public String getName() {
        return name;
    }

    public int getCitedBy() {
        return citedBy;
    }
}
