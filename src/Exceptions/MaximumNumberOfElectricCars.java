package Exceptions;

public class MaximumNumberOfElectricCars extends Exception{
    @Override
    public String getMessage() {
        return "Car cannot be added because the locomotive already has maximum number of railroad cars which require electricity!";
    }
}
