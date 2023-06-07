package RailroadCars;

import Enumerate.TOXIC_LIQUID;
import Exceptions.RailroadCarIsFull;

public class RailroadCarForLiquidToxicMaterials extends HeavyRailroadFreightCar {

    private double amountOfLiquid; // cubic meters

    private TOXIC_LIQUID liquidName;

    public RailroadCarForLiquidToxicMaterials(String shipper, double netWeight, double grossWeight, double width, double height, double length, int breaks, int coupling, double amountOfLiquid, int liquidName) {
        super(shipper, netWeight, grossWeight, width, height, length, breaks, coupling);
        this.amountOfLiquid = amountOfLiquid;
        this.liquidName = TOXIC_LIQUID.values()[liquidName - 1];
    }

    public RailroadCarForLiquidToxicMaterials() {
        amountOfLiquid = (int)(Math.random() * (getCarVolume() - 4) + 5);
        this.liquidName = TOXIC_LIQUID.values()[(int)(Math.random() * TOXIC_LIQUID.values().length)];
    }

    public void addLiquid(double amount) throws RailroadCarIsFull {
        if(amountOfLiquid + amount > getCarVolume()) {
            System.out.println((getCarVolume() - amountOfLiquid) + "cubic meters of " + liquidName + " was added!");
            throw new RailroadCarIsFull();
        }
        else {
            amountOfLiquid += amount;
            System.out.println(amount + " cubic meters of " + liquidName + " were added!");
        }
    }

    public void drainLiquid(double amount) {
        if(amountOfLiquid - amount < 0) {
            System.out.println(amountOfLiquid + " cubic meters of " + liquidName + " were drained!");
            amountOfLiquid = 0;
        }
        else {
            amountOfLiquid -= amount;
            System.out.println(amount + " cubic meters of " + liquidName + " were drained!");
        }
    }

    public void changeLiquid(TOXIC_LIQUID liquid) {
        System.out.println("Toxic liquid was changed from " + this.liquidName + " to " + liquid + ".");
        this.liquidName = liquid;
    }

    @Override
    public void engageBrakes() {
        System.out.println("Breaks of the railroad car for toxic liquid materials were engaged!");
    }

    @Override
    public void releaseBrakes() {
        System.out.println("Breaks of the railroad car for toxic liquid materials were released!");
    }

    @Override
    public String toString() {
        return "It is railroad car for toxic, liquid materials. " + super.toString() + " Current amount of " + liquidName + " is " + amountOfLiquid + " cubic meters.";
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. It is toxic, liquid in here!");
    }

    @Override
    public void load() {
        System.out.println("Toxic liquid was loaded!");
    }
}
