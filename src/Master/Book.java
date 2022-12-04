package Master;

public class Book {
    private int id;
    private String name;
    private String author;
    private boolean issued;
    private Student issuedBy;
    private Supplier suppliedBy;

    public Book(int id, String name, String author, Supplier suppliedBy) {
        setId(id);
        setName(name);
        setAuthor(author);
        setSuppliedBy(suppliedBy);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public Student getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(Student issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Supplier getSuppliedBy() {
        return suppliedBy;
    }

    public void setSuppliedBy(Supplier suppliedBy) {
        this.suppliedBy = suppliedBy;
    }

    @Override
    public String toString() {
        return "Book Id: " + id + "\n" +
                "Book Name: " + name + "\n" +
                "Book Author: " + author;
    }
}
