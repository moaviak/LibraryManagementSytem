package Master;

import java.util.ArrayList;

public class Admin extends Person implements Login{
    private String email;
    private String password;

    public Admin() {
        super("Admin", 0);
        email = "root";
        password = "root";
    }

    public Admin(String name, int age, String email, String password) {
        super(name, age);
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean verifyLogin(String email, String password) {
        return (email.equalsIgnoreCase(getEmail()) && password.equals(getPassword()));
    }

    @Override
    public void viewBooks(ArrayList<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            System.out.println("--------Book " + (i+1) + "--------");
            System.out.println(books.get(i));
            System.out.println("Supplied By: " + books.get(i).getSuppliedBy());
            if (books.get(i).isIssued()) {
                System.out.println("Issued By: " + books.get(i).getIssuedBy());
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                "\nAge: " + getAge() +
                "\nEmail: " + getEmail();
    }
}
