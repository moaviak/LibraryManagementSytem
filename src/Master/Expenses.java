package Master;

public class Expenses {
    private String month;
    private int utilityBills;
    private int booksCost;
    private int adminSalary;

    public Expenses(String month, int utilityBills, int booksCost, int adminSalary) {
        setMonth(month);
        setUtilityBills(utilityBills);
        setBooksCost(booksCost);
        setAdminSalary(adminSalary);
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getUtilityBills() {
        return utilityBills;
    }

    public void setUtilityBills(int utilityBills) {
        this.utilityBills = utilityBills;
    }

    public int getBooksCost() {
        return booksCost;
    }

    public void setBooksCost(int booksCost) {
        this.booksCost = booksCost;
    }

    public int getAdminSalary() {
        return adminSalary;
    }

    public void setAdminSalary(int adminSalary) {
        this.adminSalary = adminSalary;
    }

    public int getTotalCost() {
        return utilityBills + booksCost + adminSalary;
    }

    public void generateTranscript() {
        System.out.println("Month: " + getMonth() +
                "\nUtility Bills: " + getUtilityBills() +
                "\nBooks Cost: " + getBooksCost() +
                "\nAdmin Salary: " + getAdminSalary() +
                "\nTotal Bill: " + getTotalCost());
    }

    @Override
    public String toString() {
        return "Month: " + month + "; Total Bill: " + getTotalCost();
    }
}
