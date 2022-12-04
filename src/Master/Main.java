package Master;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
	    ArrayList<Admin> admins = new ArrayList<>();

        System.out.println("-------------------------------------");
        System.out.println("Welcome To Library Management System");
        System.out.println("-------------------------------------");

        System.out.println("\nInitialize Admin Account");
        signUpAdmin(admins);
        System.out.println("\nAdmin Account Successfully Created");

        boolean exit = false;
        do {
            System.out.println("\n----------Main Menu----------");

            System.out.print("1. Admin Portal\n" +
                    "2. Student Portal\n" +
                    "0. To Exit\n" +
                    "Enter: ");
            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1:
                    ArrayList<Supplier> suppliers = new ArrayList<>();
                    ArrayList<Expenses> expenses = new ArrayList<>();

                    System.out.println("\n----------Admin Portal----------");
                    System.out.print("1. To Login\n" +
                            "2. To Sign-up\n" +
                            "Enter: ");
                    ch = Integer.parseInt(sc.nextLine());

                    boolean loged = false;
                    Admin logedAdmin = null;

                    switch (ch) {
                        case 1:
                            do {
                                System.out.println("\n----------Admin LogIn----------");
                                System.out.print("Enter Email: ");
                                String email = sc.nextLine();
                                System.out.print("Enter Password: ");
                                String password = sc.nextLine();
                                for (Admin admin : admins) {
                                    if (admin.verifyLogin(email, password)) {
                                        logedAdmin = admin;
                                        loged = true;
                                    }
                                }
                                if (!loged) {
                                    System.out.println("Invalid Email or Password!");
                                    System.out.println("Press 1 to Try Again or Press 0 to Exit");
                                    if (Integer.parseInt(sc.nextLine()) != 1)
                                        break;
                                } else
                                    break;
                            } while (true);
                        case 2:
                            if (!loged) {
                                System.out.println("\n----------Admin SignUp----------");
                                signUpAdmin(admins);
                                logedAdmin = admins.get(admins.size()-1);
                                loged = true;
                                System.out.println("\nAdmin Successfully Created");
                            }
                            if (loged) {
                                do {
                                    System.out.println("\n----------Admin Portal----------");
                                    System.out.println("1. Book Menu\n" +
                                            "2. Supplier Menu\n" +
                                            "3. Expenses Menu\n" +
                                            "4. Your Profile\n" +
                                            "0. To Log-out");
                                    System.out.print("Enter: ");

                                    ch = Integer.parseInt(sc.nextLine());
                                    if (ch == 0) break;

                                    switch (ch) {
                                        case 1:
                                            do {
                                                System.out.println("\n----------Book Menu----------");
                                                System.out.print("1. Add a Book\n" +
                                                        "2. Search Book\n" +
                                                        "3. View All Books\n" +
                                                        "0. To Exit\n" +
                                                        "Enter: ");
                                                ch = Integer.parseInt(sc.nextLine());
                                                if (ch == 0) break;

                                                switch (ch) {
                                                    case 1:
                                                        if (suppliers.isEmpty()) {
                                                            System.out.println("First Add Supplier");
                                                            break;
                                                        }
                                                        System.out.print("\nEnter Book Id: ");
                                                        int bookId = Integer.parseInt(sc.nextLine());
                                                        System.out.print("Enter Book Name: ");
                                                        String bookName = sc.nextLine();
                                                        System.out.print("Enter Book Author: ");
                                                        String bookAuthor = sc.nextLine();
                                                        do {
                                                            System.out.print("Enter Book Supplier Id: ");
                                                            String supplierId = sc.nextLine();
                                                            Supplier supplier = findSupplier(suppliers, supplierId);

                                                            if (supplier != null) {
                                                                books.add(new Book(bookId, bookName, bookAuthor, supplier));
                                                                System.out.println("Book Successfully Added!");
                                                                break;
                                                            } else {
                                                                System.out.println("Invalid Supplier Id");
                                                                System.out.println("\nEnter 1 to Try Again or 0 to Exit");
                                                                if (Integer.parseInt(sc.nextLine()) != 1)
                                                                    break;
                                                            }
                                                        } while (true);
                                                        break;
                                                    case 2:
                                                        if (books.isEmpty()) {
                                                            System.out.println("\nNo Book Added Yet!");
                                                            break;
                                                        }
                                                        System.out.print("\nEnter Book Id or Name to Search: ");
                                                        String bookSearch = sc.nextLine();
                                                        Book book = findBook(books, bookSearch);
                                                        if (book != null) {
                                                            System.out.println("\n" + book);
                                                            if (book.isIssued()) {
                                                                System.out.println("Issued By: " + book.getIssuedBy());
                                                            }
                                                        } else {
                                                            System.out.println("\nNo Book Found");
                                                        }
                                                        break;
                                                    case 3:
                                                        if (books.isEmpty()) {
                                                            System.out.println("\nNo Book Added Yet!");
                                                            break;
                                                        }
                                                        logedAdmin.viewBooks(books);
                                                        break;
                                                }
                                            } while (true);
                                            break;

                                        case 2:
                                            do {
                                                System.out.println("\n----------Supplier Menu----------");
                                                System.out.print("1. Add new Supplier\n" +
                                                        "2. View Suppliers\n" +
                                                        "3. Search Supplier\n" +
                                                        "0. Exit\n" +
                                                        "Enter: ");
                                                ch = Integer.parseInt(sc.nextLine());

                                                if (ch == 0) break;

                                                switch (ch) {
                                                    case 1:
                                                        System.out.print("\nEnter Supplier Id: ");
                                                        String supplierId = sc.nextLine();
                                                        System.out.print("Enter Supplier Name: ");
                                                        String supplierName = sc.nextLine();
                                                        System.out.print("Enter Supplier Age: ");
                                                        int supplierAge = Integer.parseInt(sc.nextLine());
                                                        System.out.print("Enter Supplier Company Name: ");
                                                        String supplierCompany = sc.nextLine();
                                                        System.out.print("Enter Supplier Contact Number: ");
                                                        String supplierContact = sc.nextLine();

                                                        suppliers.add(new Supplier(supplierId, supplierName, supplierAge,
                                                                supplierCompany, supplierContact));
                                                        System.out.println("\nSupplier Successfully Added");
                                                        break;
                                                    case 2:
                                                        if (suppliers.isEmpty()) {
                                                            System.out.println("\nNo Supplier Added Yet!");
                                                            break;
                                                        }
                                                        System.out.println();
                                                        int i = 1;
                                                        for (Supplier supplier : suppliers) {
                                                            System.out.println(i + ". " + supplier);
                                                            i++;
                                                        }
                                                        break;
                                                    case 3:
                                                        if (suppliers.isEmpty()) {
                                                            System.out.println("\nNo Supplier Added Yet!");
                                                            break;
                                                        }
                                                        System.out.print("\nEnter Supplier Id to Search: ");
                                                        supplierId = sc.nextLine();
                                                        Supplier supplier = findSupplier(suppliers, supplierId);
                                                        if (supplier != null) {
                                                            System.out.println("Supplier Id: " + supplier.getIdNo() +
                                                                    "\nSupplier Name: " + supplier.getName() +
                                                                    "\nSupplier Age: " + supplier.getAge() +
                                                                    "\nSupplier Company: " + supplier.getCompany() +
                                                                    "\nSupplier Contact: " + supplier.getContact());
                                                            System.out.println("\n----------Books Supplied----------");
                                                            supplier.viewBooks(books);
                                                        } else {
                                                            System.out.println("\nNo Supplier Found!");
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println("\nInvalid Choice");
                                                }
                                            } while (true);
                                            break;
                                        case 3:
                                            do {
                                                System.out.println("\n----------Expenses Menu----------");
                                                System.out.print("1. Add new Expense Record\n" +
                                                        "2. View Previous Expenses\n" +
                                                        "3. Search for Particular Month Expenses\n" +
                                                        "0. Exit\n" +
                                                        "Enter: ");
                                                ch = Integer.parseInt(sc.nextLine());

                                                if (ch == 0) break;

                                                switch (ch) {
                                                    case 1:
                                                        System.out.print("\nEnter Month: ");
                                                        String month = sc.nextLine();
                                                        System.out.print("Enter Utility Bills: ");
                                                        int utilityBills = Integer.parseInt(sc.nextLine());
                                                        System.out.print("Enter Books Cost: ");
                                                        int booksCost = Integer.parseInt(sc.nextLine());
                                                        System.out.print("Enter Admin Salary: ");
                                                        int adminSalary = Integer.parseInt(sc.nextLine());

                                                        expenses.add(new Expenses(month, utilityBills, booksCost, adminSalary));
                                                        System.out.println("\nExpenses Successfully Added");
                                                        break;
                                                    case 2:
                                                        if (expenses.isEmpty()) {
                                                            System.out.println("\nNo Record Added Yet!");
                                                            break;
                                                        }
                                                        System.out.println();
                                                        for(Expenses expense : expenses) {
                                                            System.out.println(expense);
                                                        }
                                                        break;
                                                    case 3:
                                                        if (expenses.isEmpty()) {
                                                            System.out.println("\nNo Record Added Yet!");
                                                            break;
                                                        }
                                                        System.out.print("\nEnter Month Name: ");
                                                        month = sc.nextLine();
                                                        Expenses expenses1 = findExpenses(expenses, month);
                                                        if (expenses1 != null) {
                                                            System.out.println();
                                                            expenses1.generateTranscript();
                                                        } else {
                                                            System.out.println("\nNo Record Found!");
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println("\nInvalid Choice!");
                                                }
                                            } while (true);
                                            break;
                                        case 4:
                                            // Admin Profile TBW
                                    }
                                } while (true);
                            }
                    }
                    break;
                case 2:
                    // Student Portal TBW
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid Choice");
            }

        } while (!exit);
    }

    private static Expenses findExpenses(ArrayList<Expenses> expenses, String month) {
        for(Expenses expense: expenses) {
            if (expense.getMonth().equalsIgnoreCase(month)) {
                return expense;
            }
        }
        return null;
    }

    private static Book findBook(ArrayList<Book> books, String text) {
        for (Book book : books) {
            if (String.valueOf(book.getId()).equals(text) || book.getName().equalsIgnoreCase(text)) {
                return book;
            }
        }
        return null;
    }

    private static Supplier findSupplier(ArrayList<Supplier> suppliers, String supplierId) {
        for (Supplier supplier : suppliers) {
            if (supplier.getIdNo().equals(supplierId)) {
                return supplier;
            }
        }
        return null;
    }

    private static void signUpAdmin(ArrayList<Admin> admins) {
        System.out.print("Enter Name: ");
        String adminName = sc.nextLine();
        System.out.print("Enter Age: ");
        int adminAge = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Email: ");
        String adminEmail = sc.nextLine();
        System.out.print("Enter Password: ");
        String adminPassword = sc.nextLine();
        admins.add(new Admin(adminName, adminAge, adminEmail, adminPassword));
    }
}
