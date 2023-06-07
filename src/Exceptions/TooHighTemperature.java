package Exceptions;

public class TooHighTemperature extends Exception{
    @Override
    public String getMessage() {
        return "The temperature inside cannot be so high!";
    }
}
