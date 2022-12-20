package Master;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<Expenses> expenses = new ArrayList<>();

        ArrayList<Book> books = new ArrayList<>();

        suppliers.add(new Supplier("001", "Faizan", 19, "Gohar Publishers", "03123456789"));
        suppliers.add(new Supplier("102", "Raja Wail", 22, "Insider's Gate", "0369696969"));

        books.add(new Book(101, "Think and Grow Rich", "Napoleon Hill", suppliers.get(0)));
        books.add(new Book(102, "Go For It", "Kamran Rizvi", suppliers.get(0)));
        books.add(new Book(103, "Alchemist", "Paulo Coelho", suppliers.get(1)));
        books.add(new Book(104, "War and Peace", "Leo Tolstoy", suppliers.get(0)));
        books.add(new Book(105,"Rich Dad Poor Dad", "Robert Kiyoski", suppliers.get(1)));

        students.add(new Student("Tanveer Ahmed", 21, "SP22-BCS-015", "12345", "Male"));
        students.add(new Student("M. Khuzaifa Awan", 21, "SP22-BCS-020", "12345", "Male"));
        students.add(new Student("Muhammad Awais", 20, "SP22-BCS-033", "12345", "Male"));

        Admin admin = new Admin("Muhammad Moavia", 20, "admin@admin.com", "root");

        expenses.add(new Expenses("January", 50000, 35000, 45000));
        expenses.add(new Expenses("Feburay", 65000, 10000, 45000));
        expenses.add(new Expenses("March", 40000, 20000, 40000));

        System.out.println("-------------------------------------");
        System.out.println("Welcome To Library Management System");
        System.out.println("-------------------------------------");

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
                    boolean loged = false;
                    do {
                        System.out.println("\n----------Admin LogIn----------");
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Password: ");
                        String password = sc.nextLine();
                        if (admin.verifyLogin(email, password)) {
                            loged = true;
                            break;
                        } else {
                            System.out.println("Invalid Email or Password!");
                            System.out.println("Press 1 to Try Again or Press 0 to Exit");
                            if (Integer.parseInt(sc.nextLine()) != 1)
                                break;
                        }
                    } while (true);
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
                                                admin.viewBooks(books);
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
                                                for (Expenses expense : expenses) {
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
                                                            admin.setName(name);
                                                            break;
                                                        case 2:
                                                            System.out.print("\nEnter New Age: ");
                                                            int age = Integer.parseInt(sc.nextLine());
                                                            admin.setAge(age);
                                                            break;
                                                        case 3:
                                                            System.out.print("\nEnter New Email: ");
                                                            String email = sc.nextLine();
                                                            admin.setEmail(email);
                                                            break;
                                                        case 4:
                                                            System.out.print("\nEnter New Password: ");
                                                            String password = sc.nextLine();
                                                            admin.setPassword(password);
                                                            break;
                                                        default:
                                                            System.out.println("\nInvalid Choice");
                                                    }
                                                } while (true);
                                                break;
                                            case 2:
                                                System.out.println(admin);
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
        for (Expenses expense : expenses) {
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

}
