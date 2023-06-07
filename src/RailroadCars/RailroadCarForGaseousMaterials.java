package RailroadCars;

import Enumerate.GAS;
import Exceptions.RailroadCarIsFull;

public class RailroadCarForGaseousMaterials extends BasicRailroadFreightCar{

    private double amountOfGas; // cubic meters

    private GAS gasName;

    public RailroadCarForGaseousMaterials(String shipper, double netWeight, double grossWeight, double width, double height, double length, int door, int loadingMechanism, double amountOfGas, int gasName) {
        super(shipper, netWeight, grossWeight, width, height, length, door, loadingMechanism);
        this.amountOfGas = amountOfGas;
        this.gasName = GAS.values()[gasName - 1];
    }

    public RailroadCarForGaseousMaterials() {
        amountOfGas = (int)(Math.random() * (getCarVolume() - 4) + 5);
        this.gasName = GAS.values()[(int)(Math.random() * GAS.values().length)];
    }

    public void addGas(double amount) throws RailroadCarIsFull {
        if(amountOfGas + amount > getCarVolume()) {
            System.out.println((getCarVolume() - amountOfGas) + "cubic meters of " + gasName + " was added!");
            throw new RailroadCarIsFull();
        }
        else {
            amountOfGas += amount;
            System.out.println(amount + " cubic meters of " + gasName + " were added!");
        }
    }

    public void drainGas(double amount) {
        if(amountOfGas - amount < 0) {
            System.out.println(amountOfGas + " cubic meters of " + gasName + " were drained!");
            amountOfGas = 0;
        }
        else {
            amountOfGas -= amount;
            System.out.println(amount + " cubic meters of " + gasName + " were drained!");
        }
    }

    public void changeGas(GAS gas) {
        System.out.println("Gas was changed from " + this.gasName + " to " + gas + ".");
        this.gasName = gas;
    }

    @Override
    public void openDoors() {
        System.out.println("Doors of the railroad car for gaseous materials were open!");
    }

    @Override
    public void closeDoors() {
        System.out.println("Doors of the railroad car for gaseous materials were closed!");
    }

    @Override
    public String toString() {
        return "It is railroad car for gaseous materials. " + super.toString() + " Current amount of " + gasName + " is " + amountOfGas + " cubic meters.";
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. It is gas in here!");
    }

    @Override
    public void load() {
        System.out.println("Gas was loaded!");
    }
}
