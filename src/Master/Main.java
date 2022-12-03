package Master;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
	    ArrayList<Admin> admins = new ArrayList<>();
        System.out.println("-------------------------------------");
        System.out.println("Welcome To Library Management System");
        System.out.println("-------------------------------------");

        System.out.println("\nInitialize Admin Account");

        System.out.print("Enter Name: ");
        String adminName = sc.nextLine();
        System.out.print("Enter Age: ");
        int adminAge = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Email: ");
        String adminEmail = sc.nextLine();
        System.out.print("Enter Password: ");
        String adminPassword = sc.nextLine();
        admins.add(new Admin(adminName, adminAge, adminEmail, adminPassword));

        System.out.println("\nAdmin Account Successfully Created");
    }
}
