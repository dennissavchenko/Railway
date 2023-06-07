import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Station {

    protected int id;

    private static int INITIAL_ONE = 1;

    protected String name;

    public Station() {
        id = INITIAL_ONE++;
        this.name = "Station" + id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

}
