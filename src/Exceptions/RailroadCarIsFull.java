package Exceptions;

public class RailroadCarIsFull extends Exception {
    @Override
    public String getMessage() {
        return "Railroad car is full!";
    }
}
