import Exceptions.MaximumLoad;
import Exceptions.MaximumNumberOfElectricCars;
import Exceptions.MaximumNumberOfRailroadCars;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StationsLogistics.generateStationsConnections(100);
        Locomotives.generateLocomotives(25);
        Locomotives.launchLocomotives();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press '1' to go to stations connections settings or any other key to go to locomotives settings");
            if(scanner.next().equals("1")) {
                System.out.println("Stations connections settings: ");
                System.out.println("1 - Print station connections");
                System.out.println("2 - Print stations");
                System.out.println("3 - Add station");
                System.out.println("4 - Add connection");
                System.out.println("5 - Delete connection");
                System.out.println("Any other key - exit");
                switch (scanner.next()) {
                    case "1" -> StationsLogistics.printStationsConnections();
                    case "2" -> System.out.println(StationsLogistics.getStationsList());
                    case "3" -> {
                        StationsLogistics.addStation();
                        StationsLogistics.printStationsConnections();
                        System.out.println(StationsLogistics.getStationsList());
                    }
                    case "4" -> {
                        StationsLogistics.addConnection();
                        StationsLogistics.printStationsConnections();
                        System.out.println(StationsLogistics.getStationsList());
                    }
                    case "5" -> {
                        StationsLogistics.deleteConnection();
                        StationsLogistics.printStationsConnections();
                        System.out.println(StationsLogistics.getStationsList());
                    }
                    default -> {}
                }
            } else {
                System.out.println("Locomotives settings: ");
                System.out.println("1 - Print all locomotives");
                System.out.println("2 - Print locomotive by its id");
                System.out.println("3 - Add locomotive");
                System.out.println("4 - Delete locomotive");
                System.out.println("5 - Locomotive railroad car set settings");
                System.out.println("Any other key - exit");
                switch (scanner.next()) {
                    case "1" -> System.out.println(Locomotives.getLocomotives());
                    case "2" -> {
                        System.out.print("Enter id of the locomotive you want to see information about: ");
                        int id = scanner.nextInt();
                        int index = Locomotives.indexOfId(id);
                        if(index != -1) System.out.println(Locomotives.getLocomotives().get(index) + "\n");
                        else System.out.println("Locomotive was not found!");
                    }
                    case "3" -> Locomotives.addLocomotive();
                    case "4" -> Locomotives.deleteLocomotive();
                    case "5" -> {
                        System.out.println("Locomotive railroad car set settings: ");
                        System.out.println("1 - Add railroad car");
                        System.out.println("2 - Delete railroad car");
                        System.out.println("Any other key - exit");
                        switch (scanner.next()) {
                            case "1" -> Locomotive.addCarFromMenu();
                            case "2" -> Locomotive.deleteRailroadCarFromMenu();
                            default -> {}
                        }
                    }
                    default -> {}
                }
            }
        }
    }
}