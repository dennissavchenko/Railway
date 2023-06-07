package RailroadCars;

import Exceptions.TooHighTemperature;
import Interfaces.UsesElectricity;

public class RefrigeratedRailroadCar extends BasicRailroadFreightCar implements UsesElectricity {

    private double temperature; // degrees Celsius
    private final double maxTemperature; // degrees Celsius

    public RefrigeratedRailroadCar(String shipper, double netWeight, double grossWeight, double width, double height, double length, int door, int loadingMechanism, double temperature, double maxTemperature) {
        super(shipper, netWeight, grossWeight, width, height, length, door, loadingMechanism);
        super.needElectricity = true;
        this.temperature = temperature;
        this.maxTemperature = maxTemperature;
    }

    public RefrigeratedRailroadCar() {
        super.needElectricity = true;
        this.temperature = 4;
        this.maxTemperature = 10;
    }

    public void increaseTemperature(double degrees) throws TooHighTemperature {
        if(temperature + degrees > maxTemperature) {
            System.out.println("Temperature was increased on " + (maxTemperature - temperature) + " degrees!");
            throw new TooHighTemperature();
        }
        else {
            temperature += degrees;
            System.out.println("Temperature was increased on " + degrees + " degrees!");
        }
    }

    public void decreaseTemperature(double degrees) {
        temperature -= degrees;
        System.out.println("Temperature was decreased on " + degrees + " degrees!");
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. It is frozen freight in here!");
    }

    @Override
    public void load() {
        System.out.println("Frozen freight was loaded!");
    }

    @Override
    public void openDoors() {
        System.out.println("Doors of the refrigerated railroad car were open!");
    }

    @Override
    public void closeDoors() {
        System.out.println("Doors of the refrigerated railroad car were closed!");
    }

    @Override
    public String toString() {
        return "It is refrigerated railroad car. " + super.toString() + " Current temperature in here is " + temperature + " °C, maximum temperature is " + maxTemperature + " °C.";
    }

    @Override
    public void turnOffElectricity() {
        System.out.println("Electricity in refrigerated railroad car was turned off!");
    }

    @Override
    public void turnOnElectricity() {
        System.out.println("Electricity in refrigerated railroad car was turned on!");
    }

    @Override
    public void printElectricityUsage() {
        System.out.println("Electricity is being used for lightning and fridges");
    }
}
