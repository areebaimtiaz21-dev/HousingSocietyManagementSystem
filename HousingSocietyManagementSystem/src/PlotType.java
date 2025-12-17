public enum PlotType {
    RES_5_MARLA(4_000_000, "PKR"),
    RES_10_MARLA(7_500_000, "PKR"),
    RES_1_KANAL(14_000_000, "PKR"),
    COMM_SHOP(3_000_000, "PKR"),
    COMM_OFFICE(5_000_000, "PKR"),
    PARKING(200_000, "PKR"); 
    long price;
    String currency;
    PlotType(long price, String currency) {
        this.price = price;
        this.currency = currency;
    }
}