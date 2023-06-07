package RailroadCars;

import Exceptions.RailroadCarIsFull;
import Interfaces.UsesElectricity;

public class PassengerRailroadCar extends RailroadCar implements UsesElectricity {

    private final int numberOfSeats;

    private int numberOfPassengers;

    public PassengerRailroadCar(String shipper, double netWeight, double grossWeight, int numberOfSeats, int numberOfPassengers) {
        super(shipper, netWeight, grossWeight);
        super.needElectricity = true;
        this.numberOfSeats = numberOfSeats;
        this.numberOfPassengers = numberOfPassengers;
    }

    public PassengerRailroadCar() {
        super.needElectricity = true;
        super.netWeight = (int)(Math.random() * 26 + 20); // net weight of the car can be 20-45 tonnes
        super.grossWeight = (int)(Math.random() * 46 + 45); // gross weight of the car can be 45-90 tonnes
        this.numberOfSeats = (int)(Math.random() * 41 + 5); // railroad car can have 5-45 seats
        this.numberOfPassengers = (int)(Math.random() * numberOfSeats + 1);
    }

    public void addPassenger() throws RailroadCarIsFull {
        if(numberOfPassengers + 1 > numberOfSeats) throw new RailroadCarIsFull();
        else {
            numberOfPassengers++;
            System.out.println("New passenger was added!");
        }
    }

    public void printFullness() {
        System.out.println(numberOfPassengers + " out of " + numberOfSeats + " people in this car!");
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. It is people is here!");
    }

    @Override
    public void load() {
        System.out.println("People are loaded!");
    }

    @Override
    public String toString() {
        return "It is passenger railroad car. " + super.toString() + " Number of seats is " + numberOfSeats + ", number of passengers is " + numberOfPassengers + ".";
    }

    @Override
    public void turnOffElectricity() {
        System.out.println("Electricity in passenger railroad car was turned off!");
    }

    @Override
    public void turnOnElectricity() {
        System.out.println("Electricity in passenger railroad car was turned on!");
    }

    @Override
    public void printElectricityUsage() {
        System.out.println("Electricity is being used for lightning, passengers' devices charging and water heating.");
    }

}
