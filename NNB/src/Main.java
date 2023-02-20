import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        network test = new network(new int[][]{{2,2},{2,1}});
        test.save_network();
        test.load_network("network.txt");

    }
}