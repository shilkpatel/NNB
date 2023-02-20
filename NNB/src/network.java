import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Arrays;
public class network
{
    public layer[] net;
    public int[][] structure;
    // NEED TO ADD FUNCTIONALITY TO SPECIFY ACTIVATION OF EACH LAYER
    public network(int[][] nodes)
    {
        net = new layer[nodes.length];
        structure = nodes;
        // each layer is an array containing the number of inputs and outputs [2,3] 2 inputs and 3 outputs
        for(int i =0;i<nodes.length;i++)
        {
            net[i]= new layer(nodes[i][0],nodes[i][1],'a');
        }
    }

    public network(layer[] a,int[][] b)
    {
        net=a;
        structure=b;
    }


    public vector out(vector input)
    {
        vector output= new vector(3);
        int num_layer= net.length;

        for(int i=0;i<num_layer;i++)
        {
             output =new vector(net[i].compute(input));
        }
        return output;
    }

    public static double MSE(vector current_output,vector output)
    {

        double mean_square_error = 0;
        for(int i=0;i<current_output.v.length;i++)
        {
            mean_square_error += (current_output.geti(i)-output.geti(i))*(current_output.geti(i)-output.geti(i));
        }
        return mean_square_error;

    }

    public void save_network()
    {

        String snetwork="";

        for(int[] i:structure)
        {
            snetwork+=Arrays.toString(i)+",";
        }
        snetwork=snetwork.substring(0, snetwork.length() - 1);
        snetwork+="\n";
        for(layer a:net)
        {
            snetwork+=a.serialize()+"\n"+a.a+"\n";

        }
        try {
            File saved_network = new File("network.txt");
            if (saved_network.createNewFile()) {
                System.out.println("File created: " + saved_network.getName());
            } else {
                System.out.println("File already exists.");
            }

        }
        catch (IOException e)
        {
            System.out.println("Oppsie daisy");
        }

        try
        {
            FileWriter myWriter = new FileWriter("network.txt");
            myWriter.write(snetwork);
            myWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("No");
        }

    }

    public void load_network(String file)
    {
        try
        {
            File network = new File(file);
            Scanner reader = new Scanner(network);
            String string_structure=reader.nextLine();
            String[] stringarr_struct=string_structure.split(",");
            String[][] struct= new String[stringarr_struct.length][2];
            int[][] int_struct= new int[stringarr_struct.length][2];
            for(int i =0;i<stringarr_struct.length;i++)
            {
                 stringarr_struct[i]=stringarr_struct[i].substring(1,stringarr_struct.length - 1);
                 struct[i]=stringarr_struct[i].split(",");
                 int_struct[i][0]=Integer.parseInt(struct[i][0]);

            }

            for(int i=0;i< int_struct.length;i++)
            {
                String slayer=reader.nextLine();
                String[] string_arr=slayer.split(",");

            }

        }
        catch (IOException e)
        {
            System.out.println("denied");
        }
    }



}
