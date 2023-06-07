package RailroadCars;

import Enumerate.LIQUID;
import Exceptions.RailroadCarIsFull;

public class RailroadCarForLiquidMaterials extends BasicRailroadFreightCar {

    private double amountOfLiquid; // cubic meters

    private LIQUID liquidName;

    public RailroadCarForLiquidMaterials(String shipper, double netWeight, double grossWeight, double width, double height, double length, int door, int loadingMechanism, double amountOfLiquid, int liquidName) {
        super(shipper, netWeight, grossWeight, width, height, length, door, loadingMechanism);
        this.amountOfLiquid = amountOfLiquid;
        this.liquidName = LIQUID.values()[liquidName - 1];
    }

    public RailroadCarForLiquidMaterials() {
        amountOfLiquid = (int)(Math.random() * (getCarVolume() - 4) + 5);
        this.liquidName = LIQUID.values()[(int)(Math.random() * LIQUID.values().length)];
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

    public void changeLiquid(LIQUID liquid) {
        System.out.println("Liquid was changed from " + this.liquidName + " to " + liquid + ".");
        this.liquidName = liquid;
    }

    @Override
    public void openDoors() {
        System.out.println("Doors of the railroad car for liquid materials were open!");
    }

    @Override
    public void closeDoors() {
        System.out.println("Doors of the railroad car for liquid materials were closed!");
    }

    @Override
    public String toString() {
        return "It is railroad car for liquid materials. " + super.toString() + " Current amount of " + liquidName + " is " + amountOfLiquid + " cubic meters.";
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. It is liquid in here!");
    }

    @Override
    public void load() {
        System.out.println("Liquid was loaded!");
    }

}
