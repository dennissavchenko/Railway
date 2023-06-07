import Enumerate.*;
import Exceptions.MaximumLoad;
import Exceptions.MaximumNumberOfElectricCars;
import Exceptions.MaximumNumberOfRailroadCars;
import Exceptions.RailroadHazard;
import RailroadCars.*;
import java.util.*;

public class Locomotive extends Thread {

    private int id;

    private static int INITIAL_ONE = 1;

    private final String name;

    private int home;

    private int source;

    private int destination;

    private double speed; // km / h

    private final int maxCars;

    private final double maxLoad; // in tonnes

    private final int maxElectricityCars;

    private int currentNumberOfCars = 0;

    private double currentLoad = 0;

    private int currentNumberOfElectronicCars = 0;

    private final List<RailroadCar> railroadCars = new ArrayList<>();

    private int currentStationIndex = 0;

    private List<Integer> currentRoute = new ArrayList<>();

    protected boolean direction = true;

    private double fullDistance = 0;

    private int currentStationId = 0;

    private int nextStationId = 0;

    private double stationDistance = 0;

    private double passedWayStation = 0;

    private double passedWayFull = 0;

    public Locomotive() {
        this.id += INITIAL_ONE++;
        this.name = "Locomotive" + id;
        this.home = StationsLogistics.getStationsList().get((int)(Math.random() * StationsLogistics.size)).id;
        this.source = StationsLogistics.getStationsList().get((int)(Math.random() * StationsLogistics.size)).id;
        this.destination = StationsLogistics.getStationsList().get((int)(Math.random() * StationsLogistics.size)).id;
        while (this.destination == this.source) this.destination = StationsLogistics.getStationsList().get((int)(Math.random() * StationsLogistics.size)).id;
        this.speed = 100;
        this.maxCars = (int)(Math.random() * 6 + 5);
        this.maxLoad = Math.random() * 501 + 700; // 700-1200 tonnes
        this.maxElectricityCars = (int)(Math.random() * 3 + 2);
        int generationNum = (int)(Math.random() * (maxCars - 2) + 3);
        boolean throwsException = false;
        for (int i = 0; i < generationNum && !throwsException; i++) {
            try {
                addRandomCar();
            } catch (MaximumNumberOfRailroadCars | MaximumLoad | MaximumNumberOfElectricCars e) {
                throwsException = true;
            }
        }
    }

    public Locomotive(String name, int home, int source, int destination, double speed, int maxCars, double maxLoad, int maxElectricityCars) {
        this.id += INITIAL_ONE++;
        this.name = name;
        this.home = home;
        this.source = source;
        this.destination = destination;
        this.speed = speed;
        this.maxCars = maxCars;
        this.maxLoad = maxLoad;
        this.maxElectricityCars = maxElectricityCars;
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many railroad cars you want to add: ");
        int number = scanner.nextInt();
        while (number < 0 || number > maxCars) {
            System.out.println("Number of cars cannot be bigger than maximum car number or smaller than zero!");
            System.out.print("Try again! How many railroad cars you want to add: ");
            number = scanner.nextInt();
        }
        boolean throwsException = false;
        for (int i = 0; i < number && !throwsException; i++) {
                System.out.print("Enter data for the " + (i + 1) + " railroad car: ");
            try {
                addRailroadCar();
            } catch (MaximumLoad e) {
                System.out.println("Load weight limit was exceeded!");
            } catch (MaximumNumberOfRailroadCars e) {
                System.out.println("You cannot add more cars");
            } catch (MaximumNumberOfElectricCars e) {
                System.out.println("Number of railroad cars that require electricity was exceeded!");
            }
        }
    }

    public void addRandomCar() throws MaximumNumberOfRailroadCars, MaximumLoad, MaximumNumberOfElectricCars {
        switch ((int)(Math.random() * 12 + 1)) {
            case 1 -> addCheckedCar(new PassengerRailroadCar());
            case 2 -> addCheckedCar(new RailroadPostOffice());
            case 3 -> addCheckedCar(new RailroadBaggageAndMailCar());
            case 4 -> addCheckedCar(new RailroadRestaurantCar());
            case 5 -> addCheckedCar(new BasicRailroadFreightCar());
            case 6 -> addCheckedCar(new HeavyRailroadFreightCar());
            case 7 -> addCheckedCar(new RefrigeratedRailroadCar());
            case 8 -> addCheckedCar(new RailroadCarForLiquidMaterials());
            case 9 -> addCheckedCar(new RailroadCarForGaseousMaterials());
            case 10 -> addCheckedCar(new RailroadCarForExplosives());
            case 11 -> addCheckedCar(new RailroadCarForToxicMaterials());
            case 12 -> addCheckedCar(new RailroadCarForLiquidToxicMaterials());
        }
    }

    public static void addCarFromMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id of the locomotive you want add railroad car to: ");
        int id = scanner.nextInt();
        if(Locomotives.indexOfId(id) != -1) {
            try {
                Locomotives.getLocomotives().get(Locomotives.indexOfId(id)).addRailroadCar();
            } catch (MaximumLoad e) {
                System.out.println("Load limit was exceeded!");
            } catch (MaximumNumberOfRailroadCars e) {
                System.out.println("Maximum number of railroad cars was exceeded!");
            } catch (MaximumNumberOfElectricCars e) {
                System.out.println("Maximum number of railroad cars which require electricity was exceeded!");
            }
        }
        else System.out.println("Locomotive was not found!");
    }

    private void addRailroadCar() throws MaximumLoad, MaximumNumberOfRailroadCars, MaximumNumberOfElectricCars {
        System.out.println("If you want to add a random railroad car press - '1', otherwise press any other key!");
        Scanner scanner = new Scanner(System.in);
        if(scanner.nextLine().equals("1")) addRandomCar();
        else {
            System.out.println("You need to choose what type of railroad car you want to add!");
            System.out.println("1 - Passenger railroad car");
            System.out.println("2 - Railroad post office");
            System.out.println("3 - Railroad baggage and mail car");
            System.out.println("4 - Railroad restaurant car");
            System.out.println("5 - Basic railroad freight car");
            System.out.println("6 - Heavy railroad freight car");
            System.out.println("7 - Refrigerated railroad car");
            System.out.println("8 - Railroad car for liquid materials");
            System.out.println("9 - Railroad car for gaseous material");
            System.out.println("10 - Railroad car for explosives");
            System.out.println("11 - Railroad car for toxic materials");
            System.out.println("12 - Railroad car for liquid, toxic material");
            int type = scanner.nextInt();
            while (type < 1 || type > 12) {
                System.out.println("There is no such a car!");
                System.out.println("Try again! You need to choose what type of railroad car you want to add!");
                type = scanner.nextInt();
            }
            System.out.print("Provide a shipper for a new railroad car: ");
            String shipper = scanner.next();
            System.out.print("Provide a net weight for a new railroad car in tonnes: ");
            double netWeight = scanner.nextDouble();
            System.out.print("Provide a gross weight for a new railroad car in tonnes: ");
            double grossWeight = scanner.nextDouble();
            while (grossWeight < netWeight) {
                System.out.println("Gross weight cannot be smaller than net weight!");
                System.out.print("Try again! Provide a gross weight for a new railroad car: ");
                grossWeight = scanner.nextDouble();
            }
            switch (type) {
                case 1 -> {
                    System.out.print("Provide number of seats in the car: ");
                    int numberOfSeats = scanner.nextInt();
                    System.out.print("Provide number of passengers in the car: ");
                    int numberOfPassengers = scanner.nextInt();
                    while (numberOfPassengers > numberOfSeats) {
                        System.out.println("Number of passengers cannot be bigger than number of seats!");
                        System.out.print("Try again! Provide number of passengers in the car: ");
                        numberOfPassengers = scanner.nextInt();
                    }
                    addCheckedCar(new PassengerRailroadCar(shipper, netWeight, grossWeight, numberOfSeats, numberOfPassengers));
                }
                case 2 -> {
                    System.out.print("Provide maximum number of letters in the car: ");
                    int maxNumberOfLetters = scanner.nextInt();
                    System.out.print("Provide current number of letters in the car: ");
                    int currentNumberOfLetters = scanner.nextInt();
                    while (currentNumberOfLetters > maxNumberOfLetters) {
                        System.out.println("Current number of letters in the car cannot be bigger than maximum number of letters!");
                        System.out.print("Try again! Provide current number of letters in the car: ");
                        currentNumberOfLetters = scanner.nextInt();
                    }
                    addCheckedCar(new RailroadPostOffice(shipper, netWeight, grossWeight, maxNumberOfLetters, currentNumberOfLetters));
                }
                case 3 -> {
                    System.out.print("Provide number of bags in the car: ");
                    int numberOfBags = scanner.nextInt();
                    System.out.print("Provide number of letters in the car: ");
                    int numberOfLetters = scanner.nextInt();
                    addCheckedCar(new RailroadBaggageAndMailCar(shipper, netWeight, grossWeight, numberOfBags, numberOfLetters));
                }
                case 4 -> {
                    System.out.println("Choose the number of the main dish!");
                    RailroadRestaurantCar.printMenu();
                    int mainDish = scanner.nextInt();
                    while (mainDish < 1 || mainDish > MENU.values().length) {
                        System.out.println("Chosen dish number does not exist!");
                        System.out.print("Try again! Choose the number of the main dish!");
                        mainDish = scanner.nextInt();
                    }
                    System.out.print("Provide maximum passenger number that can fit inside the car: ");
                    int maxNumberOfPassenger = scanner.nextInt();
                    addCheckedCar(new RailroadRestaurantCar(shipper, netWeight, grossWeight, mainDish, maxNumberOfPassenger));
                }
                case 5 -> {
                    double[] parameters = askForParameters();
                    int[] basic = askForBasic();
                    addCheckedCar(new BasicRailroadFreightCar(shipper, netWeight, grossWeight, parameters[0], parameters[1], parameters[2], basic[0], basic[1]));
                }
                case 6 -> {
                    double[] parameters = askForParameters();
                    int[] heavy = askForHeavy();
                    addCheckedCar(new HeavyRailroadFreightCar(shipper, netWeight, grossWeight, parameters[0], parameters[1], parameters[2], heavy[0], heavy[1]));
                }
                case 7 -> {
                    double[] parameters = askForParameters();
                    int[] basic = askForBasic();
                    System.out.print("Provide maximum temperature inside the car: ");
                    double maxTemperature = scanner.nextDouble();
                    System.out.print("Provide current temperature inside the car: ");
                    double temperature = scanner.nextDouble();
                    while (temperature > maxTemperature) {
                        System.out.println("Current temperature cannot be bigger that maximum temperature!");
                        System.out.print("Try again! Provide current temperature inside the car: ");
                        temperature = scanner.nextDouble();
                    }
                    addCheckedCar(new RefrigeratedRailroadCar(shipper, netWeight, grossWeight, parameters[0], parameters[1], parameters[2], basic[0], basic[1], temperature, maxTemperature));
                }
                case 8 -> {
                    double[] parameters = askForParameters();
                    int[] basic = askForBasic();
                    System.out.print("Provide amount of liquid in cubic meters: ");
                    double amount = scanner.nextDouble();
                    while (amount > (parameters[0] * parameters[1] * parameters[2])) {
                        System.out.println("Amount of liquid does not fit!");
                        System.out.print("Try again! Provide amount of liquid in cubic meters: ");
                        amount = scanner.nextDouble();
                    }
                    System.out.println("Choose the number of type of liquid!");
                    for (int i = 0; i < LIQUID.values().length; i++) {
                        System.out.println((i + 1) + " - " + LIQUID.values()[i]);
                    }
                    int liquid = scanner.nextInt();
                    while (liquid < 1 || liquid > LIQUID.values().length) {
                        System.out.println("You have chosen wrong number!");
                        System.out.println("Try again! Choose the number of type of liquid!");
                        liquid = scanner.nextInt();
                    }
                    addCheckedCar(new RailroadCarForLiquidMaterials(shipper, netWeight, grossWeight, parameters[0], parameters[1], parameters[2], basic[0], basic[1], amount, liquid));
                }
                case 9 -> {
                    double[] parameters = askForParameters();
                    int[] basic = askForBasic();
                    System.out.print("Provide amount of gas in cubic meters: ");
                    double amount = scanner.nextDouble();
                    while (amount > (parameters[0] * parameters[1] * parameters[2])) {
                        System.out.println("Amount of gas does not fit!");
                        System.out.print("Try again! Provide amount of gas in cubic meters: ");
                        amount = scanner.nextDouble();
                    }
                    System.out.println("Choose the number of type of gas!");
                    for (int i = 0; i < GAS.values().length; i++) {
                        System.out.println((i + 1) + " - " + GAS.values()[i]);
                    }
                    int gas = scanner.nextInt();
                    while (gas < 1 || gas > GAS.values().length) {
                        System.out.println("You have chosen wrong number!");
                        System.out.println("Try again! Choose the number of type of gas!");
                        gas = scanner.nextInt();
                    }
                    addCheckedCar(new RailroadCarForGaseousMaterials(shipper, netWeight, grossWeight, parameters[0], parameters[1], parameters[2], basic[0], basic[1], amount, gas));
                }
                case 10 -> {
                    double[] parameters = askForParameters();
                    int[] heavy = askForHeavy();
                    System.out.print("Provide number of explosives: ");
                    int number = scanner.nextInt();
                    System.out.println("Choose the number of type of explosives!");
                    for (int i = 0; i < EXPLOSIVE.values().length; i++) {
                        System.out.println((i + 1) + " - " + EXPLOSIVE.values()[i]);
                    }
                    int explosives = scanner.nextInt();
                    while (explosives < 1 || explosives > EXPLOSIVE.values().length) {
                        System.out.println("You have chosen wrong number!");
                        System.out.println("Try again! Choose the number of type of explosives!");
                        explosives = scanner.nextInt();
                    }
                    addCheckedCar(new RailroadCarForExplosives(shipper, netWeight, grossWeight, parameters[0], parameters[1], parameters[2], heavy[0], heavy[1], explosives, number));
                }
                case 11 -> {
                    double[] parameters = askForParameters();
                    int[] heavy = askForHeavy();
                    System.out.print("Provide amount of toxic materials in cubic meters: ");
                    double amount = scanner.nextDouble();
                    while (amount > (parameters[0] * parameters[1] * parameters[2])) {
                        System.out.println("Amount of toxic materials does not fit!");
                        System.out.print("Try again! Provide amount of toxic materials in cubic meters: ");
                        amount = scanner.nextDouble();
                    }
                    System.out.println("Choose the number of type of toxic materials!");
                    for (int i = 0; i < TOXICS.values().length; i++) {
                        System.out.println((i + 1) + " - " + TOXICS.values()[i]);
                    }
                    int toxics = scanner.nextInt();
                    while (toxics < 1 || toxics > TOXICS.values().length) {
                        System.out.println("You have chosen wrong number!");
                        System.out.println("Try again! Choose the number of type of toxic materials!");
                        toxics = scanner.nextInt();
                    }
                    addCheckedCar(new RailroadCarForToxicMaterials(shipper, netWeight, grossWeight, parameters[0], parameters[1], parameters[2], heavy[0], heavy[1], toxics, amount));
                }
                case 12 -> {
                    double[] parameters = askForParameters();
                    int[] heavy = askForHeavy();
                    System.out.print("Provide amount of toxic liquid in cubic meters: ");
                    double amount = scanner.nextDouble();
                    while (amount > (parameters[0] * parameters[1] * parameters[2])) {
                        System.out.println("Amount of toxic liquid does not fit!");
                        System.out.print("Try again! Provide amount of toxic liquid in cubic meters: ");
                        amount = scanner.nextDouble();
                    }
                    System.out.println("Choose the number of type of toxic liquid!");
                    for (int i = 0; i < TOXIC_LIQUID.values().length; i++) {
                        System.out.println((i + 1) + " - " + TOXIC_LIQUID.values()[i]);
                    }
                    int liquid = scanner.nextInt();
                    while (liquid < 1 || liquid > TOXIC_LIQUID.values().length) {
                        System.out.println("You have chosen wrong number!");
                        System.out.println("Try again! Choose the number of type of toxic liquid!");
                        liquid = scanner.nextInt();
                    }
                    addCheckedCar(new RailroadCarForLiquidToxicMaterials(shipper, netWeight, grossWeight, parameters[0], parameters[1], parameters[2], heavy[0], heavy[1], amount, liquid));
                }
            }
        }
    }

    public List<RailroadCar> getRailroadCars() {
        return railroadCars;
    }

    public int getMaxCars() {
        return maxCars;
    }

    private void addCheckedCar(RailroadCar railroadCar) throws MaximumLoad, MaximumNumberOfRailroadCars, MaximumNumberOfElectricCars {
        if(currentLoad + railroadCar.getGrossWeight() > maxLoad) throw new MaximumLoad();
        else if(currentNumberOfCars + 1 > maxCars) throw new MaximumNumberOfRailroadCars();
        else if(currentNumberOfElectronicCars + 1 > maxElectricityCars && railroadCar.needElectricity()) throw  new MaximumNumberOfElectricCars();
        else {
            railroadCars.add(railroadCar);
            currentLoad += railroadCar.getGrossWeight();
            currentNumberOfCars++;
            if(railroadCar.needElectricity()) currentNumberOfElectronicCars++;
        }
    }

    private double[] askForParameters() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Provide width of the railroad car: ");
        double width = scanner.nextDouble();
        System.out.print("Provide height of the railroad car: ");
        double height = scanner.nextDouble();
        System.out.print("Provide length of the railroad car: ");
        double length = scanner.nextDouble();

        return new double[]{width, height, length};
    }

    private int[] askForBasic() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide number of type of the doors!");
        for (int i = 0; i < DOOR.values().length; i++) {
            System.out.println((i + 1) + " - " + DOOR.values()[i]);
        }
        int doorType = scanner.nextInt();
        while (doorType < 1 || doorType > DOOR.values().length) {
            System.out.println("Chosen number does not exist!");
            System.out.print("Try again! Provide number of type of the doors: ");
            doorType = scanner.nextInt();
        }
        System.out.println("Provide number of type of the loading mechanism!");
        for (int i = 0; i < MECHANISM.values().length; i++) {
            System.out.println((i + 1) + " - " + MECHANISM.values()[i]);
        }
        int mechanismType = scanner.nextInt();
        while (mechanismType < 1 || mechanismType > MECHANISM.values().length) {
            System.out.println("Chosen number does not exist!");
            System.out.print("Try again! Provide number of type of the loading mechanism: ");
            mechanismType = scanner.nextInt();
        }
        return new int[]{doorType, mechanismType};
    }

    private int[] askForHeavy() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide number of type of the breaks!");
        for (int i = 0; i < BREAKS.values().length; i++) {
            System.out.println((i + 1) + " - " + BREAKS.values()[i]);
        }
        int breaksType = scanner.nextInt();
        while (breaksType < 1 || breaksType > BREAKS.values().length) {
            System.out.println("Chosen number does not exist!");
            System.out.print("Try again! Provide number of type of the breaks: ");
            breaksType = scanner.nextInt();
        }
        System.out.println("Provide number of type of the coupling mechanism!");
        for (int i = 0; i < COUPLING.values().length; i++) {
            System.out.println((i + 1) + " - " + COUPLING.values()[i]);
        }
        int couplingMechanism = scanner.nextInt();
        while (couplingMechanism < 1 || couplingMechanism > COUPLING.values().length) {
            System.out.println("Chosen number does not exist!");
            System.out.print("Try again! Provide number of type of the coupling mechanism: ");
            couplingMechanism = scanner.nextInt();
        }
        return new int[]{breaksType, couplingMechanism};
    }

    public void run() {
        Thread speedChange = new Thread(() -> {
            synchronized (StationsLogistics.getTraffic().get(currentStationId).get(nextStationId)) {
                while (!StationsLogistics.getTraffic().get(currentStationId).get(nextStationId)) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                }
                StationsLogistics.getTraffic().get(currentStationId).set(nextStationId, false);
            }
            boolean moving = true;
            while (moving) {
                try {
                    this.passedWayStation += speed / 3600;
                    this.passedWayFull += speed / 3600;
                    if(this.passedWayStation >= this.stationDistance) moving = false;
                    Thread.sleep(1000);
                    this.speed += this.speed * 0.03 * (Math.random() > 0.5 ? 1 : -1);
                    if (this.speed > 200) throw new RailroadHazard(this.name);
                } catch (InterruptedException ignored) {} catch (RailroadHazard e) {
                    System.out.println(e.getMessage());
                    speed = 100;
                }
            }
            StationsLogistics.getTraffic().get(currentStationId).set(nextStationId, true);
        });
        Thread move = new Thread(() -> {
            currentRoute = null;
            while (true) {
                try {
                    if (currentRoute == null) {
                        if (this.direction) {
                            currentRoute = StationsLogistics.getRoute(source, destination);
                            this.direction = false;
                        } else {
                            currentRoute = StationsLogistics.getRoute(destination, source);
                            this.direction = true;
                        }
                        fullDistance = StationsLogistics.getFullDistance(currentRoute);
                        this.currentStationIndex = 0;
                        this.passedWayFull = 0;
                    }
                    int nextStationIndex = this.currentStationIndex + 1;
                    this.currentStationId = StationsLogistics.indexOfId(this.currentRoute.get(this.currentStationIndex));
                    this.passedWayStation = 0;
                    this.nextStationId = StationsLogistics.indexOfId(this.currentRoute.get(nextStationIndex));
                    this.stationDistance = StationsLogistics.getStationsConnections().get(currentStationId).get(nextStationId);
                    speedChange.run();
                    if (nextStationIndex + 1 >= this.currentRoute.size()) {
                        Thread.sleep(30000);
                        this.currentRoute = null;
                        this.currentStationIndex = -1;
                    } else {
                        Thread.sleep(2000);
                        this.currentStationIndex = nextStationIndex;
                    }
                } catch (InterruptedException ignored) {}
            }
        });
        move.start();
    }


    public void setDestination(int destination) {
        this.destination = destination;
    }

    public static void deleteRailroadCarFromMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id of locomotive you want to edit: ");
        int id = scanner.nextInt();
        if(Locomotives.indexOfId(id) != -1) {
            System.out.println(Locomotives.getLocomotives().get(Locomotives.indexOfId(id)));
            System.out.print("Enter number (in a locomotive list) of railroad car you want to delete: ");
            int number = scanner.nextInt();
            while (number <= 0 || number > Locomotives.getLocomotives().get(Locomotives.indexOfId(id)).getRailroadCars().size()) {
                System.out.println("There is no railroad car with such a number in the list");
                System.out.print("Try again. Enter number (in a locomotive list) of railroad car you want to delete: ");
                number = scanner.nextInt();
            }

            Locomotives.getLocomotives().get(Locomotives.indexOfId(id)).getRailroadCars().remove(number - 1);

            System.out.println(Locomotives.getLocomotives().get(Locomotives.indexOfId(id)));
        }
        else System.out.println("Locomotive was not found");
    }



    public void setHome(int home) {
        this.home = home;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getHome() {
        return home;
    }

    @Override
    public long getId() {
        return id;
    }

    public int getDestination() {
        return destination;
    }

    public int getSource() {
        return source;
    }

    public double getRoutePercentage() {
        return passedWayFull / fullDistance;
    }

    public int getCurrentStationId() {
        return StationsLogistics.getStationsList().get(currentStationIndex).id;
    }

    public void setCurrentRoute(List<Integer> currentRoute) {
        this.currentRoute = currentRoute;
    }

    public double getPassedWayFull() {
        return passedWayFull;
    }

    public double getPassedWayStation() {
        return passedWayStation;
    }

    public List<Integer> getCurrentRoute() {
        return currentRoute;
    }

    public void setFullDistance(double fullDistance) {
        this.fullDistance = fullDistance;
    }

    public void setCurrentStationIndex(int currentStationIndex) {
        this.currentStationIndex = currentStationIndex;
    }

    @Override
    public String toString() {
        railroadCars.sort((r1, r2) -> Double.compare(r2.getGrossWeight(), r1.getGrossWeight()));
        String string = "\n It is a Locomotive. Its id is " + id + ". Name is " + name + ". Maximum amount of cars is " + maxCars + ", home station is " + home + ", source station is " + source + ", destination station is " + destination + ", speed is " + String.format("%.2f", speed) + ", maximum number of railroad cars is " + maxCars + ", maximum load is " + String.format("%.2f", maxLoad) + " tonnes, maximum railroad cars which require electricity is " + maxElectricityCars;
        for (int i = 0; i < railroadCars.size(); i++) string += "\n   " + (i + 1) + ". " + railroadCars.get(i);
        string += "\n % of the distance completed between the starting and destination railway stations: " + (int)(passedWayFull / fullDistance * 100) + "%";
        string += "\n % of the distance completed between the nearest railway stations on your route: " + (int)(passedWayStation / stationDistance * 100) + "%";
        return string;
    }
}

