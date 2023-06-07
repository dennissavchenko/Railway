import Enumerate.*;
import Exceptions.*;
import RailroadCars.*;

public class Presentation {
    public static void main(String[] args) {

        // RAILROAD CARS
        // PASSENGER RAILROAD CAR
        PassengerRailroadCar passengerRailroadCar = new PassengerRailroadCar();

        // toString
        System.out.println(passengerRailroadCar);

        // Parental methods
        passengerRailroadCar.load();
        passengerRailroadCar.printSecurityInformation();

        // Uses electricity methods
        passengerRailroadCar.printElectricityUsage();
        passengerRailroadCar.turnOffElectricity();
        passengerRailroadCar.turnOnElectricity();

        // Car's unique methods
        try { passengerRailroadCar.addPassenger(); } catch (RailroadCarIsFull e) { System.out.println("You are not able to add any more passengers because car is full!"); }
        passengerRailroadCar.printFullness();

        // Result
        System.out.println(passengerRailroadCar);

        System.out.println();

        // RAILROAD POST OFFICE
        RailroadPostOffice railroadPostOffice = new RailroadPostOffice();

        // toString
        System.out.println(railroadPostOffice);

        // Parental methods
        railroadPostOffice.load();
        railroadPostOffice.printSecurityInformation();

        // Uses electricity methods
        railroadPostOffice.printElectricityUsage();
        railroadPostOffice.turnOffElectricity();
        railroadPostOffice.turnOnElectricity();

        // Car's unique methods
        try { railroadPostOffice.addLetter(); } catch (RailroadCarIsFull e) { System.out.println("You are not able to add any more letters because car is full!"); }
        railroadPostOffice.printFullness();

        // Result
        System.out.println(railroadPostOffice);

        System.out.println();

        // RAILROAD BAGGAGE AND MAIL CAR
        RailroadBaggageAndMailCar railroadBaggageAndMailCar = new RailroadBaggageAndMailCar();

        // toString
        System.out.println(railroadBaggageAndMailCar);

        // Parental methods
        railroadBaggageAndMailCar.load();
        railroadBaggageAndMailCar.printSecurityInformation();

        // Car's unique methods
        railroadBaggageAndMailCar.printNumberOfBags();
        railroadBaggageAndMailCar.printNumberOfLetters();

        // Result
        System.out.println(railroadBaggageAndMailCar);

        System.out.println();

        // RAILROAD RESTAURANT CAR
        RailroadRestaurantCar railroadRestaurantCar = new RailroadRestaurantCar();

        // toString
        System.out.println(railroadRestaurantCar);

        // Parental methods
        railroadRestaurantCar.load();
        railroadRestaurantCar.printSecurityInformation();

        // Uses electricity methods
        railroadRestaurantCar.printElectricityUsage();
        railroadRestaurantCar.turnOffElectricity();
        railroadRestaurantCar.turnOnElectricity();

        // Car's unique methods
        railroadRestaurantCar.changeMainDish(MENU.Pancakes);
        railroadRestaurantCar.printMainDish();
        railroadRestaurantCar.printMaxPassengerNumber();

        // Result
        System.out.println(railroadRestaurantCar);

        System.out.println();

        // BASIC RAILROAD FREIGHT CAR
        BasicRailroadFreightCar basicRailroadFreightCar = new BasicRailroadFreightCar();

        // toString
        System.out.println(basicRailroadFreightCar);

        // Parental methods
        basicRailroadFreightCar.load();
        basicRailroadFreightCar.printSecurityInformation();

        // Has parameters method
        System.out.println("Area of the railroad car is " + basicRailroadFreightCar.getCarArea() + " m2.");
        System.out.println("Volume of the railroad car is " + basicRailroadFreightCar.getCarVolume() + " m3.");

        // Car's unique method
        basicRailroadFreightCar.changeDoors(DOOR.CombinationDoors);
        basicRailroadFreightCar.changeLoadingMechanism(MECHANISM.Crane);
        basicRailroadFreightCar.closeDoors();
        basicRailroadFreightCar.openDoors();

        // Result
        System.out.println(basicRailroadFreightCar);

        System.out.println();

        // HEAVY RAILROAD FREIGHT CAR
        HeavyRailroadFreightCar heavyRailroadFreightCar = new HeavyRailroadFreightCar();

        // toString
        System.out.println(heavyRailroadFreightCar);

        // Parental methods
        heavyRailroadFreightCar.load();
        heavyRailroadFreightCar.printSecurityInformation();

        // Has parameters method
        System.out.println("Area of the railroad car is " + basicRailroadFreightCar.getCarArea() + " m2.");
        System.out.println("Volume of the railroad car is " + basicRailroadFreightCar.getCarVolume() + " m3.");

        // Car's unique methods
        heavyRailroadFreightCar.changeBreaks(BREAKS.DynamicBrakes);
        heavyRailroadFreightCar.changeCoupling(COUPLING.TightLockCoupling);
        heavyRailroadFreightCar.engageBrakes();
        heavyRailroadFreightCar.releaseBrakes();

        // Result
        System.out.println(heavyRailroadFreightCar);

        System.out.println();

        // REFRIGERATED RAILROAD CAR
        RefrigeratedRailroadCar refrigeratedRailroadCar = new RefrigeratedRailroadCar();

        // toString
        System.out.println(refrigeratedRailroadCar);

        // Parental methods
        refrigeratedRailroadCar.load();
        refrigeratedRailroadCar.printSecurityInformation();
        refrigeratedRailroadCar.changeDoors(DOOR.CombinationDoors);
        refrigeratedRailroadCar.changeLoadingMechanism(MECHANISM.Crane);
        refrigeratedRailroadCar.closeDoors();
        refrigeratedRailroadCar.openDoors();

        // Uses electricity methods
        refrigeratedRailroadCar.printElectricityUsage();
        refrigeratedRailroadCar.turnOffElectricity();
        refrigeratedRailroadCar.turnOnElectricity();

        // Has parameters methods (parental methods)
        System.out.println("Area of the railroad car is " + basicRailroadFreightCar.getCarArea() + " m2.");
        System.out.println("Volume of the railroad car is " + basicRailroadFreightCar.getCarVolume() + " m3.");

        // Car's unique methods
        refrigeratedRailroadCar.decreaseTemperature(4);
        try { refrigeratedRailroadCar.increaseTemperature(2); } catch (TooHighTemperature e) { System.out.println(e.getMessage()); }

        // Result
        System.out.println(refrigeratedRailroadCar);

        System.out.println();

        // RAILROAD CAR FOR LIQUID MATERIALS
        RailroadCarForLiquidMaterials railroadCarForLiquidMaterials = new RailroadCarForLiquidMaterials();

        // toString
        System.out.println(railroadCarForLiquidMaterials);

        // Parental methods
        railroadCarForLiquidMaterials.load();
        railroadCarForLiquidMaterials.printSecurityInformation();
        railroadCarForLiquidMaterials.changeDoors(DOOR.CombinationDoors);
        railroadCarForLiquidMaterials.changeLoadingMechanism(MECHANISM.Crane);
        railroadCarForLiquidMaterials.closeDoors();
        railroadCarForLiquidMaterials.openDoors();

        // Has parameters methods (parental methods)
        System.out.println("Area of the railroad car is " + basicRailroadFreightCar.getCarArea() + " m2.");
        System.out.println("Volume of the railroad car is " + basicRailroadFreightCar.getCarVolume() + " m3.");

        // Car's unique methods
        try { railroadCarForLiquidMaterials.addLiquid(100); } catch (RailroadCarIsFull e) { System.out.println(e.getMessage()); }
        railroadCarForLiquidMaterials.drainLiquid(50);
        railroadCarForLiquidMaterials.changeLiquid(LIQUID.Diesel);

        // Result
        System.out.println(railroadCarForLiquidMaterials);

        System.out.println();

        // RAILROAD CAR FOR GASEOUS MATERIALS
        RailroadCarForGaseousMaterials railroadCarForGaseousMaterials = new RailroadCarForGaseousMaterials();

        // toString
        System.out.println(railroadCarForGaseousMaterials);

        // Parental methods
        railroadCarForGaseousMaterials.load();
        railroadCarForGaseousMaterials.printSecurityInformation();
        railroadCarForGaseousMaterials.changeDoors(DOOR.CombinationDoors);
        railroadCarForGaseousMaterials.changeLoadingMechanism(MECHANISM.Crane);
        railroadCarForGaseousMaterials.closeDoors();
        railroadCarForGaseousMaterials.openDoors();

        // Has parameters methods (parental methods)
        System.out.println("Area of the railroad car is " + basicRailroadFreightCar.getCarArea() + " m2.");
        System.out.println("Volume of the railroad car is " + basicRailroadFreightCar.getCarVolume() + " m3.");

        // Car's unique methods
        try { railroadCarForGaseousMaterials.addGas(50); } catch (RailroadCarIsFull e) { System.out.println(e.getMessage()); }
        railroadCarForGaseousMaterials.drainGas(30);
        railroadCarForGaseousMaterials.changeGas(GAS.Oxygen);

        // Result
        System.out.println(railroadCarForGaseousMaterials);

        System.out.println();

        // RAILROAD CAR FOR EXPLOSIVES
        RailroadCarForExplosives railroadCarForExplosives = new RailroadCarForExplosives();

        // toString
        System.out.println(railroadCarForExplosives);

        // Parental methods
        railroadCarForExplosives.load();
        railroadCarForExplosives.printSecurityInformation();
        railroadCarForExplosives.changeBreaks(BREAKS.AirBrakes);
        railroadCarForExplosives.changeCoupling(COUPLING.KnuckleCoupler);
        railroadCarForExplosives.engageBrakes();
        railroadCarForExplosives.releaseBrakes();

        // Has parameters methods (parental methods)
        System.out.println("Area of the railroad car is " + basicRailroadFreightCar.getCarArea() + " m2.");
        System.out.println("Volume of the railroad car is " + basicRailroadFreightCar.getCarVolume() + " m3.");

        // Car's unique methods
        railroadCarForExplosives.addExplosive();
        railroadCarForExplosives.deleteExplosive();
        railroadCarForExplosives.changeExplosives(EXPLOSIVE.TNT);

        // Result
        System.out.println(railroadCarForExplosives);

        System.out.println();

        // RAILROAD CAR FOR TOXIC MATERIALS
        RailroadCarForToxicMaterials railroadCarForToxicMaterials = new RailroadCarForToxicMaterials();

        // toString
        System.out.println(railroadCarForToxicMaterials);

        // Parental methods
        railroadCarForToxicMaterials.load();
        railroadCarForToxicMaterials.printSecurityInformation();
        railroadCarForToxicMaterials.changeBreaks(BREAKS.AirBrakes);
        railroadCarForToxicMaterials.changeCoupling(COUPLING.KnuckleCoupler);
        railroadCarForToxicMaterials.engageBrakes();
        railroadCarForToxicMaterials.releaseBrakes();

        // Has parameters methods (parental methods)
        System.out.println("Area of the railroad car is " + basicRailroadFreightCar.getCarArea() + " m2.");
        System.out.println("Volume of the railroad car is " + basicRailroadFreightCar.getCarVolume() + " m3.");

        // Car's unique methods
        try { railroadCarForToxicMaterials.addToxicMaterials(20); } catch (RailroadCarIsFull e) { System.out.println(e.getMessage()); }
        railroadCarForToxicMaterials.unloadToxicMaterials(10);
        railroadCarForToxicMaterials.changeToxics(TOXICS.Poisons);

        // Result
        System.out.println(railroadCarForToxicMaterials);

        System.out.println();

        // RAILROAD CAR FOR TOXIC LIQUID MATERIALS
        RailroadCarForLiquidToxicMaterials railroadCarForLiquidToxicMaterials = new RailroadCarForLiquidToxicMaterials();

        // toString
        System.out.println(railroadCarForLiquidToxicMaterials);

        // Parental methods
        railroadCarForLiquidToxicMaterials.load();
        railroadCarForLiquidToxicMaterials.printSecurityInformation();
        railroadCarForLiquidToxicMaterials.changeBreaks(BREAKS.AirBrakes);
        railroadCarForLiquidToxicMaterials.changeCoupling(COUPLING.KnuckleCoupler);
        railroadCarForLiquidToxicMaterials.engageBrakes();
        railroadCarForLiquidToxicMaterials.releaseBrakes();

        // Has parameters methods (parental methods)
        System.out.println("Area of the railroad car is " + basicRailroadFreightCar.getCarArea() + " m2.");
        System.out.println("Volume of the railroad car is " + basicRailroadFreightCar.getCarVolume() + " m3.");

        // Car's unique methods
        try { railroadCarForLiquidToxicMaterials.addLiquid(100); } catch (RailroadCarIsFull e) { System.out.println(e.getMessage()); }
        railroadCarForLiquidToxicMaterials.drainLiquid(50);
        railroadCarForLiquidToxicMaterials.changeLiquid(TOXIC_LIQUID.Chlorine);

        System.out.println(railroadCarForLiquidToxicMaterials);

        System.out.println();

        // STATIONS LOGISTICS

        // Generation of connections between n stations
        StationsLogistics.generateStationsConnections(10);

        // All railroad connections
        System.out.println("Railroad has " + StationsLogistics.size + " stations");
        System.out.println(StationsLogistics.getStationsList());
        StationsLogistics.printStationsConnections();

        // Stations
        StationsLogistics.addStation();
        System.out.println(StationsLogistics.getStationsList());
        StationsLogistics.printStationsConnections();
        StationsLogistics.deleteStation();
        System.out.println(StationsLogistics.getStationsList());
        StationsLogistics.printStationsConnections();

        // Connections
        StationsLogistics.addConnection();
        System.out.println(StationsLogistics.getStationsList());
        StationsLogistics.printStationsConnections();
        StationsLogistics.deleteConnection();
        System.out.println(StationsLogistics.getStationsList());
        StationsLogistics.printStationsConnections();

        // LOCOMOTIVE

        Locomotive locomotive = new Locomotive();

        // toString
        System.out.println(locomotive);

        // Adding a railroad car to locomotive
        Locomotive.addCarFromMenu();

        System.out.println();

        System.out.println(locomotive);

        // Deleting last railroad car
        Locomotive.deleteRailroadCarFromMenu();

        // LOCOMOTIVES

        // Returns list of all locomotives of the railroad
        System.out.println(Locomotives.getLocomotives().toString().replaceAll("[\\[\\]]", ""));

        // Generate n locomotives
        Locomotives.generateLocomotives(10);
        System.out.println(Locomotives.getLocomotives().toString().replaceAll("[\\[\\]]", ""));

        // Adding a new locomotive
        Locomotives.addLocomotive();
        System.out.println(Locomotives.getLocomotives().toString().replaceAll("[\\[\\]]", ""));

        // Deletion of a locomotive
        Locomotives.deleteLocomotive();
        System.out.println(Locomotives.getLocomotives().toString().replaceAll("[\\[\\]]", ""));

    }
}
