import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        network xor = new network(new int[][]{{2,6},{6,1}},new char[]{'s','s'});
        //network xor = new network();
        //xor.load_network("network.txt");
        test(xor);
        for(int i=0;i<100000;i++)
        {
            //System.out.println(i);
            training(xor);
        }



        test(xor);
        //xor.save_network("network.txt");

//0.11673482140340918
//0.11673482140340918
    }

    public static void training(network x)
    {
        Random rand = new Random();
        vector a = new vector(new double[]{0,0});
        vector a_ans = new vector(new double[]{0});
        vector b = new vector(new double[]{1,0});
        vector b_ans =new vector(new double[]{1});
        vector c = new vector(new double[]{0,1});
        vector c_ans = new vector(new double[]{1});
        vector d = new vector(new double[]{1,1});
        vector d_ans = new vector(new double[]{0});
        vector[][] data  = new vector[4][];
        for(int i=0;i<4;i++)
        {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
            switch (randomNum)
            {
                case 0:
                    data[i]=new vector[]{a,a_ans};
                    break;
                case 1:
                    data[i]=new vector[]{b,b_ans};
                    break;
                case 2:
                    data[i]=new vector[]{c,c_ans};
                    break;
                case 3:
                    data[i]=new vector[]{d,d_ans};

            }
        }
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