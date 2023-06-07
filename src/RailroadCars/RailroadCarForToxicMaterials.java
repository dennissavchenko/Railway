package RailroadCars;

import Enumerate.TOXICS;
import Exceptions.RailroadCarIsFull;

public class RailroadCarForToxicMaterials extends HeavyRailroadFreightCar{

    private TOXICS toxics;

    private double amountOfToxics;

    public RailroadCarForToxicMaterials(String shipper, double netWeight, double grossWeight, double width, double height, double length, int breaks, int coupling, int toxics, double amountOfToxics) {
        super(shipper, netWeight, grossWeight, width, height, length, breaks, coupling);
        this.toxics = TOXICS.values()[toxics - 1];
        this.amountOfToxics = amountOfToxics;
    }

    public RailroadCarForToxicMaterials() {
        this.toxics = TOXICS.values()[(int)(Math.random() * TOXICS.values().length)];
        this.amountOfToxics = (int)(Math.random() * 11 + 5); // 5-15 cubic meters of toxic materials
    }

    public void addToxicMaterials(double amount) throws RailroadCarIsFull {
        if(amountOfToxics + amount > getCarVolume()) {
            System.out.println((getCarVolume() - amountOfToxics) + "cubic meters of " + toxics + " were added!");
            throw new RailroadCarIsFull();
        }
        else {
            amountOfToxics += amount;
            System.out.println(amount + " cubic meters of " + toxics + " were added!");
        }
    }

    public void unloadToxicMaterials(double amount) {
        if(amountOfToxics - amount < 0) {
            System.out.println(amountOfToxics + " cubic meters of " + toxics + " were unload!");
            amountOfToxics = 0;
        }
        else {
            amountOfToxics -= amount;
            System.out.println(amount + " cubic meters of " + toxics + " were unload!");
        }
    }

    public void changeToxics(TOXICS toxics) {
        System.out.println("Gas was changed from " + this.toxics + " to " + toxics + ".");
        this.toxics = toxics;
    }

    @Override
    public void engageBrakes() {
        System.out.println("Breaks of the railroad car for toxic materials were engaged!");
    }

    @Override
    public void releaseBrakes() {
        System.out.println("Breaks of the railroad car for toxic materials were released!");
    }

    @Override
    public String toString() {
        return "It is railroad car for toxic materials. " + super.toString() + " Current amount of " + toxics + " is " + amountOfToxics + " cubic meters";
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. It is toxic materials in here!");
    }

    @Override
    public void load() {
        System.out.println("Toxic materials was loaded!");
    }
}
