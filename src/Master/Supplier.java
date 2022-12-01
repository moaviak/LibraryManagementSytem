package Master;

import java.util.ArrayList;

public class Supplier extends Person{
    private String idNo;
    private String company;
    private String contact;

    public Supplier(String name, int age, String company, String contact, String idNo) {
        super(name, age);
        setCompany(company);
        setContact(contact);
        setIdNo(idNo);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    @Override
    public void viewBooks(ArrayList<Book> books) {
        int count = 1;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getSuppliedBy().equals(this)) {
                System.out.println("--------Book " + count + "--------");
                System.out.println(books.get(i));
                count++;
            }
        }
    }

    @Override
    public String toString() {
        return getIdNo() + " " + getName() + " " + getCompany();
    }
}
