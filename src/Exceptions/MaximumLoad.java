package Exceptions;

public class MaximumLoad extends Exception{
    @Override
    public String getMessage() {
        return "Car cannot be added because it is too heavy!";
    }
}
