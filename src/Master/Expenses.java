package Master;

public class Expenses {
    private String month;
    private String utilityBills;
    private String booksCost;

    public Expenses(String month, String utilityBills, String booksCost) {
        this.month = month;
        this.utilityBills = utilityBills;
        this.booksCost = booksCost;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getUtilityBills() {
        return utilityBills;
    }

    public void setUtilityBills(String utilityBills) {
        this.utilityBills = utilityBills;
    }

    public String getBooksCost() {
        return booksCost;
    }

    public void setBooksCost(String booksCost) {
        this.booksCost = booksCost;
    }
}
