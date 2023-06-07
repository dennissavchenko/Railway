package RailroadCars;

import Enumerate.BREAKS;
import Enumerate.COUPLING;
import Interfaces.HasParameters;

public class HeavyRailroadFreightCar extends RailroadCar implements HasParameters {

    private final double width;

    private final double height;

    private final double length;

    private BREAKS breaks;

    private COUPLING coupling;

    public HeavyRailroadFreightCar(String shipper, double netWeight, double grossWeight, double width, double height, double length, int breaks, int coupling) {
        super(shipper, netWeight, grossWeight);
        this.height = height;
        this.length = length;
        this.width = width;
        this.breaks = BREAKS.values()[breaks - 1];
        this.coupling = COUPLING.values()[coupling - 1];
    }

    public HeavyRailroadFreightCar() {
        super.netWeight = (int)(Math.random() * 21 + 20); // net weight of the car can be 20-40 tonnes
        super.grossWeight = (int)(Math.random() * 31 + 100); // gross weight of the car can be 100-130 tonnes
        this.height = (int)(Math.random() * 4 + 5); // height can be 5-8 metres
        this.width = (int)(Math.random() * 4 + 5); // width can be 5-8 meters
        this.length = (int)(Math.random() * 11 + 25); // length can be 25-35 meters
        this.breaks = BREAKS.values()[(int)(Math.random() * BREAKS.values().length)];
        this.coupling = COUPLING.values()[(int)(Math.random() * COUPLING.values().length)];
    }

    public void changeBreaks(BREAKS breaks) {
        System.out.println("You have successfully changed breaks from " + this.breaks + " to " + breaks + ".");
        this.breaks = breaks;
    }

    public void changeCoupling(COUPLING coupling) {
        System.out.println("You have successfully changed coupling from " + this.coupling + " to " + coupling + ".");
        this.coupling = coupling;
    }

    public void engageBrakes() {
        System.out.println("Brakes of the heavy railroad car were engaged!");
    }
    public void releaseBrakes() {
        System.out.println("Breaks of the heavy railroad car were released!");
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. It is heavy freight in here!");
    }

    @Override
    public void load() {
        System.out.println("Heavy freight was loaded!");
    }

    @Override
    public String toString() {
        return "It is heavy railroad freight car. " + super.toString() + " Its width is " + width + ", height is " + height + ", length - " + length + ". It uses " + breaks + " and " + coupling + ".";
    }

    @Override
    public double getCarArea() {
        return length * width;
    }

    @Override
    public double getCarVolume() {
        return width * height * length;
    }

}
