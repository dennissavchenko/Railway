import java.util.*;

public class StationsLogistics {

    protected static boolean isGenerated = false;

    protected static int size = 0;

    private static final int maxConnections = 10; // value which is used for random generating (at the end of creating connections graph, station can have more than that).

    private static final int maxDistance = 7; // km

    private static final int minDistance = 5; // km

    private static final List<List<Double>> stationsConnections = new ArrayList<>();

    private static final List<Station> stationsList = new ArrayList<>();

    private static final List<List<Boolean>> traffic = new ArrayList<>();

    private static void generateConnection(int i) {
        int numberOfConnections = size - i - 1 >= maxConnections ? (int) ((Math.random() * maxConnections) + 1) : (int) ((Math.random() * (size - i - 1)) + 1);
        for (int j = 0; j < numberOfConnections; j++) {
            int index = (int) ((Math.random() * (size - i - 1)) + i + 1);
            double value = (int) ((Math.random() * ((maxDistance - minDistance) + 1)) + minDistance);
            stationsConnections.get(i).set(index, value);
        }
    }

    static public void generateStationsConnections(int sizeByUser) {

        size = sizeByUser;

        for (int i = 0; i < size; i++) {
            stationsList.add(new Station());
        }

        for (int i = 0; i < size; i++) {
            List<Double> list = new ArrayList<>();
            for (int j = 0; j < size; j++) list.add(.0);
            stationsConnections.add(list);
        }

        for (int i = 0; i < size - 1; i++) generateConnection(i);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                stationsConnections.get(j).set(i, stationsConnections.get(i).get(j));
            }
        }

        isGenerated = true;

        for (int i = 0; i < size; i++) {
            List<Boolean> list = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                list.add(true);
            }
            traffic.add(list);
        }

    }

    public static List<Station> getStationsList() {
        return stationsList;
    }

    public static List<List<Boolean>> getTraffic() {
        return traffic;
    }

    public static List<List<Double>> getStationsConnections() {
        return stationsConnections;
    }

    public static double getFullDistance(List<Integer> route) {
        double distance = 0;
        for (int i = 0; i < route.size() - 1; i++) distance += stationsConnections.get(indexOfId(route.get(i))).get(indexOfId(route.get(i + 1)));
        return distance;
    }

    private static boolean stationExists(int id) {
        boolean isFound = false;
        for (int i = 0; i < stationsList.size() && !isFound; i++) {
            if(stationsList.get(i).id == id) isFound = true;
        }
        return isFound;
    }

    static public void printStationsConnections() {
        System.out.println();
        System.out.print("        ");
        for (Station station : stationsList) System.out.print(station.id >= 99 ? station.id + "   " : (station.id >= 9 ? station.id + "    " : station.id + "     "));
        System.out.println();
        System.out.println();
        int i = 0;
        for (List<Double> list : stationsConnections) {
            System.out.print(stationsList.get(i).id >= 100 ? stationsList.get(i++).id : (stationsList.get(i).id >= 10 ? stationsList.get(i++).id + " " : stationsList.get(i++).id + "  "));
            for (Double value : list) {
                if (value < 10) System.out.print("   " + value);
                else System.out.print("  " + value);
            }
            System.out.println();
        }
        System.out.println();
    }

    static public List<Integer> getRoute(int sourceStation, int destinationStation) {
        Set<Integer> marked = new HashSet<>();
        List<Integer> route = new ArrayList<>();
        dfs(stationsConnections, indexOfId(sourceStation), indexOfId(destinationStation), marked, route);
        return route;
    }

    private static boolean dfs(List<List<Double>> connections, int currentStation, int destinationStation, Set<Integer> marked, List<Integer> route) {

        marked.add(currentStation);

        if (currentStation == destinationStation) {
            route.add(stationsList.get(currentStation).id);
            return true;
        }

        for (int next = 0; next < connections.size(); next++) {
            if (!marked.contains(next) && connections.get(currentStation).get(next) > 0) {
                if (dfs(connections, next, destinationStation, marked, route)) {
                    route.add(0, stationsList.get(currentStation).id);
                    return true;
                }
            }
        }

        return false;

    }

    public static int indexOfId(int id) {
        boolean isFound = false;
        int index = 0;
        for(int j = 0; j < stationsList.size() && !isFound; j++) {
            if(stationsList.get(j).id == id) {
                index = j;
                isFound = true;
            }
        }
        if(isFound) return index;
        else return -1;
    }

    static public void addStation() {

        List<Boolean> line = new ArrayList<>();

        for (int i = 0; i < size - 1; i++) {
            traffic.get(i).add(true);
            line.add(true);
        }

        line.add(true);

        traffic.add(line);

        size++;

        if(size == 1) {

            List<Double> list = new ArrayList<>();
            list.add(.0);
            stationsConnections.add(list);
            stationsList.add(new Station());

        } else {

            for (int i = 0; i < size - 1; i++) stationsConnections.get(i).add(.0);

            List<Double> list = new ArrayList<>();

            for (int i = 0; i < size; i++) list.add(.0);
            stationsConnections.add(list);

            System.out.println("If you want to add your own connections for the new station - press '1', if you want to do it randomly - press any other key.");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("1")) newByUser();
            else newRandomStation();

        }

    }

    private static void newRandomStation() {

        stationsList.add(new Station());

        int numberOfConnections = size - 1 >= maxConnections ? (int) ((Math.random() * maxConnections) + 1) : (int) ((Math.random() * size + 1));
        for (int j = 0; j < numberOfConnections; j++) {
            int index = (int) (Math.random() * (size - 1));
            double value = (int) ((Math.random() * maxDistance) + 1);
            stationsConnections.get(size - 1).set(index, value);
        }

        for (int i = 0; i < size; i++) {
            stationsConnections.get(i).set(size - 1, stationsConnections.get(size - 1).get(i));
        }

    }

    private static void newByUser() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("How many connections are you going to add (maximum is " + (size - 1) + "): ");
        int connections = scanner.nextInt();
        while (connections >= size) {
            System.out.println("You can add up to " + (size - 1) + " stations!");
            System.out.print("Try again. How many connections are you going to add: ");
            connections = scanner.nextInt();
        }

        for (int i = 0; i < connections; i++) {

            System.out.println("You have stations with such id:");
            for (int j = 0; j < stationsList.size(); j++) System.out.print(stationsList.get(j).id + " ");
            System.out.println();
            System.out.print((i + 1) + ". Enter a id of the station you want to connect: ");
            int station = scanner.nextInt();
            while (!stationExists(station)) {
                System.out.println("Station does not exist!");
                System.out.print("Try again. Enter a id of the station you want to connect: ");
                station = scanner.nextInt();
            }
            System.out.print("Enter a distance between the stations: ");
            double distance = scanner.nextDouble();
            while (distance <= 0) {
                System.out.println("Distance between stations must be bigger than zero!");
                System.out.print("Try again. Enter a distance between the stations: ");
                distance = scanner.nextInt();
            }

            if(indexOfId(station) != -1) stationsConnections.get(size - 1).set(indexOfId(station), distance);
            else System.out.println("Station was not found!");

        }

        stationsList.add(new Station());

        for (int i = 0; i < size; i++) {
            stationsConnections.get(i).set(size - 1, stationsConnections.get(size - 1).get(i));
        }

    }

    private static void reconnection() {
        for (int i = 0; i < stationsConnections.size(); i++) {
            boolean doesNotHaveConnection = true;
            for (int j = 0; j < stationsConnections.get(i).size() && doesNotHaveConnection; j++) {
                if (stationsConnections.get(i).get(j) != 0) doesNotHaveConnection = false;
            }
            if(doesNotHaveConnection) {

                generateConnection(i);

                for (int y = 0; y < size; y++) {
                    for (int j = 0; j < size; j++) {
                        stationsConnections.get(j).set(y, stationsConnections.get(y).get(j));
                    }
                }

                System.out.println("Due to the deletion of the station or connection, logistics were disrupted. However, some connections have been reestablished, and logistics is now working properly again.");

            }
        }
    }

    public static void deleteStation() {

        if(size == 0) System.out.println("You do not have any stations to delete!");

        else {

            traffic.remove(traffic.size() - 1);
            for (int i = 0; i < traffic.size(); i++) {
                traffic.get(i).remove(traffic.size() - 1);
            }

            System.out.println("Here is id numbers of all existing stations: ");
            for (Station station : stationsList) System.out.print(station.id + " ");

            System.out.println();

            System.out.print("Enter id of the station you want to delete: ");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();

            while (indexOfId(id) == -1) {
                System.out.println("The station was not found!");
                System.out.println("Try again! Enter id of the station you want to delete: ");
                id = scanner.nextInt();
            }

            size--;
            int index = indexOfId(id);
            stationsList.remove(index);
            stationsConnections.remove(index);
            for (List<Double> list : stationsConnections) list.remove(index);
            reconnection();
            Locomotives.checkLocomotive(id);

        }
    }

    public static void deleteConnection() {

        if(size <= 1) System.out.println("You do not have any connections!");

        else {

            Scanner scanner = new Scanner(System.in);
            System.out.println("You have such connections between stations: ");
            printStationsConnections();
            System.out.println("Now enter id numbers of the stations you want to delete connection between!");

            System.out.print("Enter id of the first station: ");
            int first = scanner.nextInt();
            while (indexOfId(first) == -1) {
                System.out.println("Station was not found!");
                System.out.print("Try again. Enter id of the first station: ");
                first = scanner.nextInt();
            }

            System.out.print("Enter id of the second station: ");
            int second = scanner.nextInt();
            while (indexOfId(second) == -1) {
                System.out.println("Station was not found!");
                System.out.print("Try again. Enter id of the second station: ");
                second = scanner.nextInt();
            }

            stationsConnections.get(indexOfId(first)).set(indexOfId(second), .0);
            stationsConnections.get(indexOfId(second)).set(indexOfId(first), .0);

            reconnection();

        }
    }

    public static void addConnection() {

        if(size <= 1) System.out.println("You must have two or more stations to add new connection!");

        else {

            Scanner scanner = new Scanner(System.in);
            System.out.println("You have such connections between stations: ");
            printStationsConnections();
            System.out.println("Now enter id numbers of the stations you want to establish connection between!");

            System.out.print("Enter id of the first station: ");
            int first = scanner.nextInt();
            while (indexOfId(first) == -1) {
                System.out.println("Station was not found!");
                System.out.print("Try again. Enter id of the first station: ");
                first = scanner.nextInt();
            }

            System.out.print("Enter id of the second station: ");
            int second = scanner.nextInt();
            while (indexOfId(second) == -1 && second == first) {
                System.out.println("You cannot establish connection between the station because either it is the same station you have entered or it was not found!");
                System.out.print("Try again. Enter id of the second station: ");
                second = scanner.nextInt();
            }

            System.out.println("Enter the distance between these stations: ");
            double distance = scanner.nextDouble();
            while (distance <= 0) {
                System.out.println("Distance must be bigger than zero!");
                System.out.print("Try again! Enter the distance between these stations: ");
                distance = scanner.nextDouble();
            }

            stationsConnections.get(indexOfId(first)).set(indexOfId(second), distance);
            stationsConnections.get(indexOfId(second)).set(indexOfId(first), distance);

        }
    }
}
