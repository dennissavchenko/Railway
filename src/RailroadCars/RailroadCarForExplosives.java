package RailroadCars;

import Enumerate.EXPLOSIVE;

public class RailroadCarForExplosives extends HeavyRailroadFreightCar{

    private EXPLOSIVE explosive;

    private int numberOfExplosive;

    public RailroadCarForExplosives(String shipper, double netWeight, double grossWeight, double width, double height, double length, int breaks, int coupling, int explosive, int numberOfExplosive) {
        super(shipper, netWeight, grossWeight, width, height, length, breaks, coupling);
        this.explosive = EXPLOSIVE.values()[explosive - 1];
        this.numberOfExplosive = numberOfExplosive;
    }

    public RailroadCarForExplosives() {
        this.explosive = EXPLOSIVE.values()[(int)(Math.random() * EXPLOSIVE.values().length)];
        this.numberOfExplosive = (int)(Math.random() * 11 + 5); // 5-15 explosives
    }

    public void addExplosive() {
        numberOfExplosive++;
        System.out.println("New " + explosive + " was added!");
    }

    public void deleteExplosive() {
        if(numberOfExplosive - 1 < 0) {
            System.out.println("Railroad car is already empty!");
        }
        else {
            numberOfExplosive--;
            System.out.println(explosive + " was deleted!");
        }
    }

    public void changeExplosives(EXPLOSIVE explosive) {
        System.out.println("Explosive was changed from " + this.explosive + " to " + explosive + ".");
        this.explosive = explosive;
    }

    @Override
    public void engageBrakes() {
        System.out.println("Breaks of the railroad car for explosives were engaged!");
    }

    @Override
    public void releaseBrakes() {
        System.out.println("Breaks of the railroad car for explosives were released!");
    }

    @Override
    public String toString() {
        return "It is railroad car for explosive materials. " + super.toString() + " Current number of " + explosive + " is " + numberOfExplosive + " items.";
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. It is explosive materials in here!");
    }

    @Override
    public void load() {
        System.out.println("Explosives materials was loaded!");
    }
}
