import com.sun.tools.jconsole.JConsoleContext;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Arrays;
/*
for activation functions
"r" - relu
"s" - sigmoid
"t" - step
*/
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

    public network(int[][] nodes,char[] activation)
    {
        net = new layer[nodes.length];
        structure=nodes;
        for(int i=0;i< nodes.length;i++)
        {
            if(activation[i]=='r')
            {
                net[i]= new relu_layer(nodes[i][0],nodes[i][1],activation[i]);
            }
            else if(activation[i]=='s')
            {
                net[i]= new sigmoid_layer(nodes[i][0],nodes[i][1],activation[i]);
            }
            else if(activation[i]=='t')
            {
                net[i]= new step_layer(nodes[i][0],nodes[i][1],activation[i]);
            }

        }
    }

    public network(layer[] a,int[][] b)
    {
        net=a;
        structure=b;
    }
    // this is for when loading a network from a file
    public network(){}
    public vector out(vector input)
    {
        vector output= input;
        int num_layer= net.length;

        for(int i=0;i<num_layer;i++)
        {
             output =new vector(net[i].compute(output));
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


    public void network_construct(int[][] nodes)
    {
        net = new layer[nodes.length];
        structure = nodes;
        // each layer is an array containing the number of inputs and outputs [2,3] 2 inputs and 3 outputs
        for(int i =0;i<nodes.length;i++)
        {
            net[i]= new layer(nodes[i][0],nodes[i][1],'a');
        }
    }


    //bias save done
    public void save_network(String filename)
    {

        String snetwork="";

        for(int[] i:structure)
        {
            snetwork+=+i[0]+","+i[1]+",";
        }
        snetwork=snetwork.substring(0, snetwork.length() - 1);
        snetwork+="\n";
        for(layer a:net)
        {
            snetwork+=a.serialize()+"\n"+a.serialize_bias()+"\n"+a.a+"\n";

        }
        try {
            File saved_network = new File(filename);
            if (saved_network.createNewFile()) {
                System.out.println("File created: " + saved_network.getName());
            }

        }
        catch (IOException e)
        {
            System.out.println("Oppsie daisy");
        }

        try
        {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(snetwork);
            myWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("No");
        }

    }
//bias load done
    public void load_network(String file)
    {
        try
        {
            File network = new File(file);
            Scanner reader = new Scanner(network);
            String string_structure=reader.nextLine();
            String[] stringarr_struct=string_structure.split(",");
            int[][] int_struct= new int[stringarr_struct.length/2][2];

            for(int i=0;i<(stringarr_struct.length/2);i++)
            {
                int_struct[i][0] = Integer.parseInt(stringarr_struct[i*2]);
                int_struct[i][1] = Integer.parseInt(stringarr_struct[(i*2)+1]);
            }


            structure=int_struct;



            // we need int_struct

            //int struct holds the structure of the network in the data type which can be passed in the class
            // this gives the structure of the network but the weights are populated by random values
            network_construct(int_struct);

            for(int i=0;i< int_struct.length;i++)
            {
                // reads the next layer from the file
                String slayer=reader.nextLine();

                String[] string_arr=slayer.split(",");// splits on comma
                String[] string_bias = reader.nextLine().split(",");
                char activation = reader.nextLine().charAt(0);
                if(activation=='r')
                {
                    net[i]= new relu_layer(int_struct[i][0],int_struct[i][1],activation);
                }
                else if(activation=='s')
                {
                    net[i]= new sigmoid_layer(int_struct[i][0],int_struct[i][1],activation);
                }
                else if(activation=='t')
                {
                    net[i]= new step_layer(int_struct[i][0],int_struct[i][1],activation);
                }
                layer current_layer = net[i];
//  -------------------------------------------------------------------------
                for(int k=0;k<current_layer.bias.v.length;k++)
                {
                    current_layer.bias.v[k]= Double.parseDouble(string_bias[k]);
                }
                //---------------------------------------------------------------
                net[i].layers = deserialize(string_arr,int_struct[i]);


            }

        }
        catch (IOException e)
        {
            System.out.println("denied");
        }
    }


    public void add_network(network learning)
    {
        // the structure of the networks have to be equal but because the learning network is defined as such
        // this will not be a concern
        for(int i =0;i< net.length;i++)//for each layer
        {
            for(int j=0;j<net[i].layers.length;j++)// for each vector in the layer
            {
                net[i].layers[j]= vector.add(net[i].layers[j],learning.net[i].layers[j]);
            }
        }
    }

    public void epoch(vector[][] training_data)
    {
        network nudge = Learning.delta(this,training_data);
        //System.out.println(Arrays.toString(nudge.net[0].layers[0].v));
        //System.out.println(Arrays.deepToString(structure));
        add_network(nudge);
        nudge.save_network("nudges.txt");
        save_network("network.txt");
    }
    public void wipe()
    {
        for(layer i:net)
        {
            i.set_zero();
        }
    }


    public static vector[] deserialize(String[] sarr,int[] size)
    {
        // this should give us the vector for a layer
        // size[inputs,outputs]

        vector[] lay = new vector[size[1]];//set the size of the vector to the number of outputs
        for (int i = 0; i < size[1] ; i++) // for each output
        {
            double[] darr = new double[size[0]];// set the size to the number of inputs

            for(int j=0;j<size[0];j++)
            {
                int index=(i*size[0])+j;
                darr[j]=Double.parseDouble(sarr[index]);
            }
            lay[i]= new vector(darr);
        }
        return lay;
    }



}
