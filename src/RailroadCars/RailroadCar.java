package RailroadCars;

abstract public class RailroadCar {
    protected int id;
    private static int INITIAL_ONE = 1;
    protected final String shipper;
    protected double netWeight;
    protected double grossWeight;
    protected boolean needElectricity = false;

    public RailroadCar() {
        this.id += INITIAL_ONE++;
        this.shipper = "WorldTrans";
        this.netWeight = (int)(Math.random() * 31 + 10); //
        this.grossWeight = (int)(Math.random() * 100 + 150); //
    }

    public RailroadCar(String shipper, double netWeight, double grossWeight) {
        this.id += INITIAL_ONE++;
        this.shipper = shipper;
        this.netWeight = netWeight;

        this.grossWeight = grossWeight;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public boolean needElectricity() {
        return needElectricity;
    }

    public abstract void printSecurityInformation();

    public abstract void load();

    @Override
    public String toString() {
        return "Its id is " + id + ". Shipper is " + shipper + ". Net weight is " + netWeight + " tonnes, gross weight is " + grossWeight + " tonnes.";
    }
}
