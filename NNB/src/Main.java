import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        network xor = new network(new int[][]{{2,2},{2,1}});
        xor.save_network("network.txt");

//0.11673482140340918
//0.11673482140340918




    }

    public static void training(network x)
    {
        vector a = new vector(new double[]{0,0});
        vector a_ans = new vector(new double[]{0});
        vector b = new vector(new double[]{1,0});
        vector b_ans =new vector(new double[]{1});
        vector c = new vector(new double[]{0,1});
        vector c_ans = new vector(new double[]{1});
        vector d = new vector(new double[]{1,1});
        vector d_ans = new vector(new double[]{0});
        vector[][] data = new vector[][]{{a,a_ans},{b,b_ans},{c,c_ans},{d,d_ans}};
        x.epoch(data);


    }
    public static void test(network x)
    {
        vector a = new vector(new double[]{0,0});
        vector b = new vector(new double[]{1,0});
        vector c = new vector(new double[]{0,1});
        vector d = new vector(new double[]{1,1});

        System.out.println(Arrays.toString(x.out(a).v));
        System.out.println(Arrays.toString(x.out(b).v));
        System.out.println(Arrays.toString(x.out(c).v));
        System.out.println(Arrays.toString(x.out(d).v));
    }
}