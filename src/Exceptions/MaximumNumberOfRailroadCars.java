package Exceptions;

public class MaximumNumberOfRailroadCars extends Exception{
    @Override
    public String getMessage() {
        return "Car cannot be added because the locomotive already has maximum number cars!";
    }
}
