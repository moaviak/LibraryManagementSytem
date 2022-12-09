package Master;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
	    ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<Expenses> expenses = new ArrayList<>();

        int[] bookIds = {101, 102, 103, 104, 105};
        String[] bookNames = {"Think and Grow Rich", "Go For It", "Alchemist", "War and Peace", "Rich Dad Poor Dad"};
        String[] bookAuthors = {"Napoleon Hill", "Kamran Rizvi", "Paulo Coelho", "Leo Tolstoy", "Robert Kiyoski"};
        Supplier initial = new Supplier("001", "Faizan", 19, "Gohar Publishers", "03123456789");

        for (int i=0; i< bookIds.length; i++) {
            books.add(new Book(bookIds[i], bookNames[i], bookAuthors[i], initial));
        }

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
                    System.out.println("\n----------Admin Portal----------");
                    System.out.print("1. To Log-in\n" +
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
                                            do {
                                                System.out.println("\n----------Your Profile----------");
                                                System.out.print("1. Change Your Profile\n" +
                                                        "2. View Your Profile\n" +
                                                        "0. Exit\n" +
                                                        "Enter: ");
                                                ch = Integer.parseInt(sc.nextLine());

                                                if (ch == 0) break;

                                                switch (ch) {
                                                    case 1:
                                                        do {
                                                            System.out.print("\n1. Change Name\n" +
                                                                    "2. Change Age\n" +
                                                                    "3. Change Email\n" +
                                                                    "4. Change Password\n" +
                                                                    "0. Exit\n" +
                                                                    "Enter: ");
                                                            ch = Integer.parseInt(sc.nextLine());

                                                            if (ch == 0) break;

                                                            switch (ch) {
                                                                case 1:
                                                                    System.out.print("\nEnter New Name: ");
                                                                    String name = sc.nextLine();
                                                                    logedAdmin.setName(name);
                                                                    break;
                                                                case 2:
                                                                    System.out.print("\nEnter New Age: ");
                                                                    int age = Integer.parseInt(sc.nextLine());
                                                                    logedAdmin.setAge(age);
                                                                    break;
                                                                case 3:
                                                                    System.out.print("\nEnter New Email: ");
                                                                    String email = sc.nextLine();
                                                                    logedAdmin.setEmail(email);
                                                                    break;
                                                                case 4:
                                                                    System.out.print("\nEnter New Password: ");
                                                                    String password = sc.nextLine();
                                                                    logedAdmin.setPassword(password);
                                                                    break;
                                                                default:
                                                                    System.out.println("\nInvalid Choice");
                                                            }
                                                        } while (true);
                                                        break;
                                                    case 2:
                                                        System.out.println(logedAdmin);
                                                        break;
                                                    default:
                                                        System.out.println("\nInvalid Choice");
                                                        break;
                                                }
                                            } while (true);
                                            break;
                                    }
                                } while (true);
                            }
                            break;
                        default:
                            System.out.println("\nInvalid Choice");
                    }
                    break;
                case 2:
                    System.out.println("\n----------Student Portal----------");
                    System.out.print("1. To Log-in\n" +
                            "2. To Sign-up\n" +
                            "Enter: ");
                    ch = Integer.parseInt(sc.nextLine());

                    boolean logged = false;
                    Student loggedStudent = null;
                    switch (ch) {
                        case 1:
                            if (students.isEmpty()) {
                                System.out.println("\nFirst Register Yourself!");
                                break;
                            }
                            System.out.println("\n----------Student LogIn----------");
                            do {
                                System.out.print("\nEnter Reg. No: ");
                                String regNo = sc.nextLine().toUpperCase();
                                System.out.print("Enter Password: ");
                                String password = sc.nextLine();
                                for (Student student : students) {
                                    if (student.verifyLogin(regNo, password)) {
                                        logged = true;
                                        loggedStudent = student;
                                        break;
                                    }
                                }
                                if (!logged) {
                                    System.out.println("Invalid Email or Password!");
                                    System.out.println("Press 1 to Try Again or Press 0 to Exit");
                                    if (Integer.parseInt(sc.nextLine()) != 1)
                                        break;
                                } else
                                    break;
                            } while (true);
                        case 2:
                            if (!logged) {
                                System.out.println("\n----------Student SignUp----------");
                                signUpStudent(students);
                                logged = true;
                                loggedStudent = students.get(students.size() - 1);
                                System.out.println("\nStudent Account Successfully Created");
                            }
                            if (logged) {
                                do {
                                    System.out.println("\n----------Student Portal----------");
                                    System.out.print("1. Issue Book\n" +
                                            "2. Return Book\n" +
                                            "3. View Available Books\n" +
                                            "4. View Your Issue List\n" +
                                            "5. Your Profile\n" +
                                            "0. Log-out\n" +
                                            "Enter: ");
                                    ch = Integer.parseInt(sc.nextLine());

                                    if (ch == 0) break;

                                    switch (ch) {
                                        case 1:
                                            if (books.isEmpty()) {
                                                System.out.print("\nNo Book Added Yet!");
                                                break;
                                            }
                                            System.out.println("\nAll Available Books");
                                            loggedStudent.viewBooks(books);

                                            System.out.print("\nEnter Book Id or Name: ");
                                            String bookSearch = sc.nextLine();
                                            Book book = findBook(books, bookSearch);
                                            if (book != null) {
                                                System.out.println("\n" + book);
                                                System.out.println("Enter 1 to Issue or 0 to cancel");
                                                if (Integer.parseInt(sc.nextLine()) == 1) {
                                                    loggedStudent.issueBook(book);
                                                    System.out.println("Book Successfully Issued to you");
                                                }
                                            } else {
                                                System.out.println("\nNo Book Found");
                                            }
                                            break;
                                        case 2:
                                            if (loggedStudent.getIssuedBooks().isEmpty()) {
                                                System.out.println("\nNo Book Issued to you");
                                                break;
                                            }
                                            System.out.println();
                                            loggedStudent.viewIssuedBooks();
                                            System.out.print("\nEnter Book Id or Name: ");
                                            bookSearch = sc.nextLine();
                                            book = findBook(loggedStudent.getIssuedBooks(), bookSearch);
                                            if (book != null) {
                                                System.out.println("\n" + book);
                                                System.out.println("Enter 1 to Return or 0 to cancel");
                                                if (Integer.parseInt(sc.nextLine()) == 1) {
                                                    loggedStudent.returnBook(book);
                                                    System.out.println("Book Successfully Returned");
                                                }
                                            } else {
                                                System.out.println("\nNo Book Found");
                                            }
                                            break;
                                        case 3:
                                            if (books.isEmpty()) {
                                                System.out.print("\nNo Book Added Yet!");
                                                break;
                                            }
                                            System.out.println("\nAll Available Books");
                                            loggedStudent.viewBooks(books);
                                            break;
                                        case 4:
                                            if (loggedStudent.getIssuedBooks().isEmpty()) {
                                                System.out.println("\nNo Book Issued to you");
                                                break;
                                            }
                                            System.out.println("\nAll Books Issued To you");
                                            loggedStudent.viewIssuedBooks();
                                            break;
                                        case 5:
                                            do {
                                                System.out.println("\n----------Your Profile----------");
                                                System.out.print("1. To Change Your Profile\n" +
                                                        "2. To View Your Profile\n" +
                                                        "0. To Exit\n" +
                                                        "Enter: ");
                                                ch = Integer.parseInt(sc.nextLine());

                                                if (ch == 0) break;

                                                switch (ch) {
                                                    case 1:
                                                        do {
                                                            System.out.print("\n1. Change Name\n" +
                                                                    "2. Change Age\n" +
                                                                    "3. Change Gender\n" +
                                                                    "4. Change Password\n" +
                                                                    "0. Exit\n" +
                                                                    "Enter: ");
                                                            ch = Integer.parseInt(sc.nextLine());

                                                            if (ch == 0) break;

                                                            switch (ch) {
                                                                case 1:
                                                                    System.out.print("\nEnter New Name: ");
                                                                    String name = sc.nextLine();
                                                                    loggedStudent.setName(name);
                                                                    break;
                                                                case 2:
                                                                    System.out.print("\nEnter New Age: ");
                                                                    int age = Integer.parseInt(sc.nextLine());
                                                                    loggedStudent.setAge(age);
                                                                    break;
                                                                case 3:
                                                                    System.out.print("\nEnter New Gender: ");
                                                                    String gender = sc.nextLine();
                                                                    loggedStudent.setGender(gender);
                                                                    break;
                                                                case 4:
                                                                    System.out.print("\nEnter New Password: ");
                                                                    String password = sc.nextLine();
                                                                    loggedStudent.setPassword(password);
                                                                    break;
                                                                default:
                                                                    System.out.println("\nInvalid Choice");
                                                            }
                                                        } while (true);
                                                        break;
                                                    case 2:
                                                        System.out.println("Reg. No: " + loggedStudent.getRegNo() +
                                                                "\nName: " + loggedStudent.getName() +
                                                                "\nAge: " + loggedStudent.getAge() +
                                                                "\nGender: " + loggedStudent.getGender());
                                                        break;
                                                    default:
                                                        System.out.println("\nInvalid Choice");
                                                        break;
                                                }
                                            } while (true);
                                            break;
                                    }
                                } while (true);
                            }
                            break;
                        default:
                            System.out.println("\nInvalid Choice");
                    }
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid Choice");
            }

        } while (!exit);
    }

    private static void signUpStudent(ArrayList<Student> students) {
        System.out.print("Enter Registration Number: ");
        String regNo = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        students.add(new Student(name, age, regNo, password, gender));
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
