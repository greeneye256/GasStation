package simedia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Pump pump = new Pump();
	    boolean isRefuelComplete = false;


        System.out.println("Welcome to our gas station!\n");



	    while (!isRefuelComplete){
            Scanner sc = new Scanner(System.in);

            String choice;

            System.out.println("1. Refuel");
            System.out.println("2. Pay\n");

            System.out.print("Choose your action: ");

            choice = sc.nextLine();
            while (!choice.matches("[12]")){
                System.out.print("\nPlease enter a valid number. Must be 1 or 2: ");
                choice = sc.nextLine();
            }
            switch (choice){
                case "1":
                    pump.addFuel(inputFuel());
                    System.out.println();
                    break;
                case "2":
                    payForFuel(pump.getTotalPrice());
                    isRefuelComplete = true;
                    break;
            }
        }
    }

    private static double inputFuel(){
        Scanner sc = new Scanner(System.in);
        double amountOfFuel;
        System.out.print("\nEnter the amount of fuel you want: ");
        boolean isFuelValid;

        do {
            try {
                amountOfFuel = sc.nextDouble();
            } catch (Exception e) {
                amountOfFuel = 0;
                sc.next();
            }

            if (amountOfFuel <= 0) {
                System.out.print("\nInvalid number input! Enter the amount of fuel you want: ");
                isFuelValid = false;
            }
            else isFuelValid = true;

        } while (!isFuelValid);
        return amountOfFuel;
    }
    private static void payForFuel(double price){
        BigDecimal fuelPrice = new BigDecimal(price);
        fuelPrice = fuelPrice.setScale(2, RoundingMode.HALF_UP);
        Scanner sc = new Scanner(System.in);
        boolean firstPayment = true;
        while (!(fuelPrice.equals(new BigDecimal("0.00")))){
            BigDecimal money = new BigDecimal("0.00");

            if (firstPayment){
                System.out.printf("You have to pay %s%s", fuelPrice,"$.\n");
                System.out.print("Enter the amount of money to pay for the fuel: ");
            }
            else {
                System.out.printf("Not enough money. You have to pay more than that. Pay another%s%s",fuelPrice,"$.\n");
                System.out.print("Enter the amount of money to pay for the fuel: ");
            }

            boolean isMoneyValid;
            do {
                try {
                    money = sc.nextBigDecimal();
                } catch (Exception e) {
                    money = BigDecimal.valueOf(0.00);
                    sc.next();
                }
                if (money.compareTo(new BigDecimal("0.00")) < 0) {
                    System.out.print("Wrong value !Enter the amount of money to pay for the fuel: ");
                    isMoneyValid = false;
                }
                else isMoneyValid = true;
            } while (!isMoneyValid);

            firstPayment = false;

            if (money.equals(fuelPrice)){
                System.out.println("Thank you very much for your payment. Have a nice day.");
                return;
            }
            if (money.compareTo(fuelPrice)>0){
                System.out.println("Here is your rest: " + (money.subtract(fuelPrice)) + ". Thank you very much for your payment. Have a nice day.");
                return;
            }
            if (money.compareTo(fuelPrice)<0){
                fuelPrice = fuelPrice.subtract(money);
            }
        }


    }

}
