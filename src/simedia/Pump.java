package simedia;

public class Pump {

    private final double pricePerLiterOfFuel = 12;
    private double totalFuel = 0;
    private double totalPrice = 0;

    void addFuel(double litersOfFuel){
        this.totalFuel+= litersOfFuel;
        this.totalPrice = totalFuel * pricePerLiterOfFuel;
        System.out.printf("\nTotal fuel = %.02f",totalFuel);
        System.out.printf(".\nYou have to pay: %.02f%s", totalPrice,"$.");
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalFuel() {
        return totalFuel;
    }
}
