package Exceptions;

public class RailroadHazard extends Exception {

    String name;

    public RailroadHazard(String name) {
        this.name = name;
    }
    @Override
    public String getMessage() {
        return name + " exceeded the speed limit in 200 km/h. Its speed was set to 100 km/h";
    }

}
