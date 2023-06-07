package RailroadCars;

import Exceptions.RailroadCarIsFull;
import Interfaces.UsesElectricity;

public class RailroadPostOffice extends RailroadCar implements UsesElectricity {

    private final int maxNumberOfLetters;

    private int currentNumberOfLetters;

    public RailroadPostOffice(String shipper, double netWeight, double grossWeight, int maxNumberOfLetters, int currentNumberOfLetters) {
        super(shipper, netWeight, grossWeight);
        super.needElectricity = true;
        this.maxNumberOfLetters = maxNumberOfLetters;
        this.currentNumberOfLetters = currentNumberOfLetters;
    }

    public RailroadPostOffice() {
        super.needElectricity = true;
        super.netWeight = (int)(Math.random() * 26 + 20); // net weight of the car can be 20-45 tonnes
        super.grossWeight = (int)(Math.random() * 61 + 30); // gross weight of the car can be 30-90 tonnes
        this.maxNumberOfLetters = (int)(Math.random() * 4001 + 500); // railroad car can have 500-4500 letters
        this.currentNumberOfLetters = (int)(Math.random() * maxNumberOfLetters + 1);
    }

    public void addLetter() throws RailroadCarIsFull {
        if(currentNumberOfLetters + 1 > maxNumberOfLetters) throw new RailroadCarIsFull();
        else {
            currentNumberOfLetters++;
            System.out.println("New letter was added!");
        }
    }

    public void printFullness() {
        System.out.println(currentNumberOfLetters + " out of " + maxNumberOfLetters + " letters in this car!");
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. It is important letters in here!");
    }

    @Override
    public void load() {
        System.out.println("Mails are loaded!");
    }

    @Override
    public String toString() {
        return "It is railroad post office. " + super.toString() + " Maximum number of letters is " + maxNumberOfLetters + ", current number of letters is " + currentNumberOfLetters + ".";
    }

    @Override
    public void turnOffElectricity() {
        System.out.println("Electricity in railroad post office was turned off!");
    }

    @Override
    public void turnOnElectricity() {
        System.out.println("Electricity in railroad post office was turned on!");
    }

    @Override
    public void printElectricityUsage() {
        System.out.println("Electricity is being used for lighting and PC usage.");
    }

}
