package RailroadCars;

public class RailroadBaggageAndMailCar extends RailroadCar {

    private final int numberOfBags;

    private final int numberOfLetters;

    public RailroadBaggageAndMailCar(String shipper, double netWeight, double grossWeight, int numberOfBags, int numberOfLetters) {
        super(shipper, netWeight, grossWeight);
        this.numberOfBags = numberOfBags;
        this.numberOfLetters = numberOfLetters;
    }

    public RailroadBaggageAndMailCar() {
        super.netWeight = (int)(Math.random() * 31 + 25); // net weight of the car can be 25-55 tonnes
        super.grossWeight = (int)(Math.random() * 46 + 45); // gross weight of the car can be 45-90 tonnes
        this.numberOfBags = (int)(Math.random() * 146 + 5); // railroad car can have 5-150 bags
        this.numberOfLetters = (int)(Math.random() * 9501 + 500); // railroad car can have 500-10000
    }

    public void printNumberOfBags() {
        System.out.println("Number of bags is " + numberOfBags + ".");
    }

    public void printNumberOfLetters() {
        System.out.println("Number of letters is " + numberOfLetters + ".");
    }

    @Override
    public String toString() {
        return "It is railroad baggage and mail car. " + super.toString() + " Number of bags is " + numberOfBags + ", number of letters is " + numberOfLetters + ".";
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. It is baggage and mails in here!");
    }

    @Override
    public void load() {
        System.out.println("Baggage and mails were loaded!");
    }

}
