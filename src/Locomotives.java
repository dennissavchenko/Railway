import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Locomotives {

    protected static boolean projectLaunched = false;

    private static final List<Locomotive> locomotives = new ArrayList<>();

    public static void addLocomotive() {
        if(StationsLogistics.isGenerated) {
            System.out.println("If you want to add your own locomotive - press '1', if you want to do it randomly - press any other key.");
            Scanner scanner = new Scanner(System.in);
            Locomotive newLocomotive;
            if(scanner.nextLine().equals("1")) newLocomotive = newByUser();
            else newLocomotive = new Locomotive();
            newLocomotive.start();
            System.out.println(newLocomotive);
            locomotives.add(newLocomotive);
        } else {
            System.out.println("Stations' logistics was not created!");
        }
    }

    public static void checkLocomotive(int deletedStationId) {
        for(Locomotive locomotive : locomotives) {
            if(locomotive.getHome() == deletedStationId) locomotive.setHome(StationsLogistics.getStationsList().get((int)(Math.random() * StationsLogistics.getStationsList().size())).getId());
            if(locomotive.getDestination() == deletedStationId) locomotive.setDestination(StationsLogistics.getStationsList().get((int)(Math.random() * StationsLogistics.getStationsList().size())).getId());
            if(locomotive.getSource() == deletedStationId) locomotive.setSource(StationsLogistics.getStationsList().get((int)(Math.random() * StationsLogistics.getStationsList().size())).getId());
        }
    }

    public static List<Locomotive> getLocomotives() {
        locomotives.sort((l1, l2) -> Double.compare(l2.getRoutePercentage(), l1.getRoutePercentage()));
        return locomotives;
    }

    public static void deleteLocomotive() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id of the locomotive you want to delete: ");
        int id = scanner.nextInt();
        try {
            locomotives.get(indexOfId(id)).interrupt();
            locomotives.remove(indexOfId(id));
            System.out.println("Locomotive was deleted!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Locomotive was not found!");
        }
    }

    public static void launchLocomotives() {
        Thread writeFile = new Thread(() -> {
            int sec = 0;
            try {
                FileWriter eraser = new FileWriter("src/AppState.txt");
                eraser.write("");
                while (true) {
                    FileWriter writer = new FileWriter("src/AppState.txt", true);
                    writer.write("\n ==== " + sec + " seconds from the start ========================================================================== \n");
                    writer.write(Locomotives.getLocomotives().toString().replaceAll("[\\[\\]]", "") + "\n");
                    writer.close();
                    sec += 5;
                    Thread.sleep(5000);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException ignored) {}
        });
        writeFile.start();
        for (Locomotive locomotive : locomotives) locomotive.start();
        projectLaunched = true;
    }

    public static int indexOfId(int id) {
        boolean isFound = false;
        int index = 0;
        for (int i = 0; i < locomotives.size() && !isFound; i++) {
            if(locomotives.get(i).getId() == id) {
                index = i;
                isFound = true;
            }
        }
        if(isFound) return index;
        else return -1;
    }
    private static Locomotive newByUser() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name of the locomotive: ");
        String name = scanner.nextLine();

        System.out.print("Enter number of home station: ");
        int home = scanner.nextInt();
        while(StationsLogistics.indexOfId(home) == -1) {
            System.out.println("Station does not exist!");
            System.out.print("Try again! Enter number of home station: ");
            home = scanner.nextInt();
        }

        System.out.print("Enter number of source station: ");
        int source = scanner.nextInt();
        while(StationsLogistics.indexOfId(source) == -1) {
            System.out.println("Station does not exist!");
            System.out.print("Try again! Enter number of source station: ");
            source = scanner.nextInt();
        }

        System.out.print("Enter number of destination station: ");
        int destination = scanner.nextInt();
        while(StationsLogistics.indexOfId(destination) == -1) {
            System.out.println("Station does not exist!");
            System.out.print("Try again! Enter number of destination station: ");
            destination = scanner.nextInt();
        }
        while (destination == source) {
            System.out.println("Destination and source stations cannot be the same stations!");
            System.out.print("Try again! Enter number of destination station: ");
            destination = scanner.nextInt();
        }

        System.out.print("Enter speed of the locomotive: ");
        double speed = scanner.nextDouble();

        System.out.print("Enter maximum number of railroad cars that can be attached to the locomotive: ");
        int maxCars = scanner.nextInt();

        System.out.print("Enter maximum number of tones that can be transported by the locomotive: ");
        int maxLoad = scanner.nextInt();

        System.out.print("Enter maximum number of railroad cars which require connection to electricity that can be attached to the locomotive: ");
        int maxElectricityCars = scanner.nextInt();

        return new Locomotive(name, home, source, destination, speed, maxCars, maxLoad, maxElectricityCars);

    }
    public static void generateLocomotives(int number) {
        if(StationsLogistics.isGenerated) {
            for (int i = 0; i < number; i++) locomotives.add(new Locomotive());
        } else {
            System.out.println("Stations' logistics was not created!");
        }
    }

}
