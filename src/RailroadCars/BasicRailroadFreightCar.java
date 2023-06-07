package RailroadCars;
import Enumerate.DOOR;
import Enumerate.MECHANISM;
import Interfaces.HasParameters;

public class BasicRailroadFreightCar extends RailroadCar implements HasParameters {

    private final double width;

    private final double height;

    private final double length;

    private DOOR door;

    private MECHANISM loadingMechanism;

    public BasicRailroadFreightCar(String shipper, double netWeight, double grossWeight, double width, double height, double length, int door, int loadingMechanism) {
        super(shipper, netWeight, grossWeight);
        this.height = height;
        this.length = length;
        this.width = width;
        this.door = DOOR.values()[door - 1];
        this.loadingMechanism = MECHANISM.values()[loadingMechanism - 1];
    }

    public BasicRailroadFreightCar() {
        super.netWeight = (int)(Math.random() * 21 + 10); // net weight of the car can be 10-30 tonnes
        super.grossWeight = (int)(Math.random() * 41 + 60); // gross weight of the car can be 60-100 tonnes
        this.height = (int)(Math.random() * 3 + 2); // height can be 2-4 metres
        this.width = (int)(Math.random() * 3 + 2); // width can be 2-4 meters
        this.length = (int)(Math.random() * 6 + 15); // length can be 15-20 meters
        this.door = DOOR.values()[(int) (Math.random() * DOOR.values().length)];
        this.loadingMechanism = MECHANISM.values()[(int) (Math.random() * MECHANISM.values().length)];
    }

    public void changeLoadingMechanism(MECHANISM loadingMechanism) {
        System.out.println("You have successfully changed loading mechanism from " + this.loadingMechanism + " to " + loadingMechanism + ".");
        this.loadingMechanism = loadingMechanism;
    }

    public void changeDoors(DOOR door) {
        System.out.println("You have successfully changed doors from " + this.door + " to " + door + ".");
        this.door = door;
    }

    public void openDoors() {
        System.out.println("Doors of the basic railroad car were opened!");
    }

    public void closeDoors() {
        System.out.println("Doors of the basic railroad car were closed!");
    }

    @Override
    public void printSecurityInformation() {
        System.out.println("Be careful. It is basic freight in here!");
    }

    @Override
    public void load() {
        System.out.println("Basic freight was loaded!");
    }

    @Override
    public String toString() {
        return "It is basic railroad freight car. " + super.toString() + " Its width is " + width + ", height is " + height + ", length - " + length + ". It uses " + door + " and " + loadingMechanism + ".";
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
