package Master;

import java.util.ArrayList;

public class Student extends Person implements Login{
    private String regNo;
    private String password;
    private String gender;
    private ArrayList<Book> issuedBooks;

    public Student(String name, int age, String regNo, String password, String gender) {
        super(name, age);
        setRegNo(regNo);
        setGender(gender);
        setPassword(password);
        issuedBooks = new ArrayList<>();
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void getIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        for (int i = 0; i < issuedBooks.size(); i++) {
            System.out.println("-----Issued Book " + i+1 + "-----");
            System.out.println(issuedBooks.get(i));
        }
    }

    public void issueBook(Book book) {
        issuedBooks.add(book);
        book.setIssued(true);
        book.setIssuedBy(this);
    }

    public boolean returnBook(Book book) {
        if (issuedBooks.contains(book)) {
            issuedBooks.remove(book);
            book.setIssued(false);
            book.setIssuedBy(null);
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyLogin(String regNo, String password) {
        return (regNo.equalsIgnoreCase(getRegNo()) && password.equals(getPassword()));
    }

    @Override
    public void viewBooks(ArrayList<Book> books) {
        int count = 1;
        for (int i = 0; i < books.size(); i++) {
            if (!books.get(i).isIssued()) {
                System.out.println("--------Book " + count + "--------");
                System.out.println(books.get(i));
                count++;
            }
        }
    }

    @Override
    public String toString() {
        return getRegNo() + " " + getName();
    }
}
