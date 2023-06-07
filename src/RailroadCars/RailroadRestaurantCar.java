package RailroadCars;

import Enumerate.MENU;
import Interfaces.UsesElectricity;

public class RailroadRestaurantCar extends RailroadCar implements UsesElectricity {
    private MENU mainDish;

    private final int maxPassengerNumber;

    public RailroadRestaurantCar(String shipper, double netWeight, double grossWeight, int mainDish, int maxPassengerNumber) {
        super(shipper, netWeight, grossWeight);
        super.needElectricity = true;
        this.mainDish = MENU.values()[mainDish - 1];
        this.maxPassengerNumber = maxPassengerNumber;
    }

    public RailroadRestaurantCar() {
        super.needElectricity = true;
        super.netWeight = (int)(Math.random() * 36 + 35); // net weight of the car can be 35-70 tonnes
        super.grossWeight = (int)(Math.random() * 46 + 45); // gross weight of the car can be 45-90 tonnes
        this.mainDish = MENU.values()[(int)(Math.random() * MENU.values().length)];
        this.maxPassengerNumber = (int)(Math.random() * 21 + 20); // maximum passenger number is 20-40
    }

    public static void printMenu() {
        for (int i = 0; i < MENU.values().length; i++) {
            System.out.println((i + 1) + " - " + Enumerate.MENU.values()[i]);
        }
    }

    public void printMainDish() {
        System.out.println("Main dish today is " + mainDish + ".");
    }

    public void changeMainDish(Enumerate.MENU dish) {
        System.out.println("Main dish was changed from " + this.mainDish + " to " + dish + ".");
        this.mainDish = dish;
    }

    public void printMaxPassengerNumber() {
        System.out.println("Only " + maxPassengerNumber + " can fit in the restaurant!");
    }

    @Override
    public String toString() {
        return "It is railroad restaurant car. " + super.toString() + " Maximum number of passengers is " + maxPassengerNumber + ". Main dish in here is " + mainDish + ".";
    }

    @Override
    public void turnOffElectricity() {
        System.out.println("Electricity in railroad restaurant car was turned off!");
    }

    @Override
    public void turnOnElectricity() {
        System.out.println("Electricity in railroad restaurant car was turned on!");
    }

    @Override
    public void printElectricityUsage() {
        System.out.println("Electricity is being used for lightning, passengers' devices charging, water heating, baking and cooking.");
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. People eat in here!");
    }

    @Override
    public void load() {
        System.out.println("Food was loaded!");
    }
}
